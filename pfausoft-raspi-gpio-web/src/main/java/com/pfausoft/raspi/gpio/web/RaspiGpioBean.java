package com.pfausoft.raspi.gpio.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.jws.WebService;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.pfausoft.raspi.gpio.GpioConfiguration;
import com.pfausoft.raspi.gpio.in.GpioDigInputConf;
import com.pfausoft.raspi.gpio.in.GpioDigitalInputController;
import com.pfausoft.raspi.gpio.in.GpioDigitalInputControllerImpl;
import com.pfausoft.raspi.gpio.out.GpioDigOutConf;
import com.pfausoft.raspi.gpio.out.GpioDigitalOutputController;
import com.pfausoft.raspi.gpio.out.GpioDigitalOutputControllerImpl;
import com.pfausoft.raspi.gpio.web.service.RaspiWebService;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

@ManagedBean
@ApplicationScoped
@WebService(endpointInterface = "com.pfausoft.raspi.gpio.web.service.RaspiWebService")
public class RaspiGpioBean implements RaspiWebService {

    private static GpioDigitalOutputController gpioDigOutputController;
    private static GpioDigitalInputController gpioDigInputController;

    static {
	FileSystemXmlApplicationContext context = null;
	try {
	    context = new FileSystemXmlApplicationContext("classpath:**/gpioconfig.xml");
	    Object beanObj = context.getBean("gpioConfiguration");
	    if (beanObj == null || !(beanObj instanceof GpioConfiguration)) {

		throw new IllegalStateException("Failed to load gpio configuration");
	    }

	    GpioConfiguration gpioConfiguration = (GpioConfiguration) beanObj;

	    GpioController gpioController = GpioFactory.getInstance();

	    List<GpioDigOutConf> activeDigOutputGpios = gpioConfiguration.getGpioDigitalOutputDefaultSettings();
	    gpioDigOutputController = new GpioDigitalOutputControllerImpl(activeDigOutputGpios, gpioController);

	    List<GpioDigInputConf> activeDigInputGpios = gpioConfiguration.getGpioDigitalInputDefaultSettings();
	    gpioDigInputController = new GpioDigitalInputControllerImpl(activeDigInputGpios, gpioController);

	} finally {
	    if (context != null) {
		context.close();
	    }
	}
    }

    @PostConstruct
    public void init() {
	gpioDigInputController.registerListener("2", new GpioPinListenerDigital() {

	    @Override
	    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		System.out.println("Event registered on gpio 2: ");
		System.out.println("Pin: " + event.getPin().getName());
		System.out.println("Is High: " + event.getState().isHigh());
		System.out.println("Event type: " + event.getEventType().name());

	    }
	});
    }

    @Override
    public void pulseGpio0() {
	pulseGpio("0", 2000, false, false);
    }

    public void pulseGpio(String id, long durationMs, boolean high, boolean block) {
	gpioDigOutputController.pulseGpio(id, durationMs, high, block);
    }

    public void toggleGpio(String id) {
	gpioDigOutputController.toggleGpio(id);
    }

    public void setGpioState(String id, boolean high) {
	gpioDigOutputController.setGpioState(id, high);
    }

    public boolean getGpioHighState(String id) {
	return gpioDigOutputController.isGpioAtHighState(id);
    }

    @PreDestroy
    public void destroy() {
	gpioDigInputController.removeAllListeners("2");
    }

    // public static void main(String[] args) {
    // RaspiGpioBean rgb = new RaspiGpioBean();
    //
    // }

}
