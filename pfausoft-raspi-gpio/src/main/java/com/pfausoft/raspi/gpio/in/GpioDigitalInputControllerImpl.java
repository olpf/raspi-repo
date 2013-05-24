package com.pfausoft.raspi.gpio.in;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class GpioDigitalInputControllerImpl implements GpioDigitalInputController {

    private static Map<String, GpioPinDigitalInput> gpioMap;
    private final GpioController gpioController;

    public GpioDigitalInputControllerImpl(List<GpioDigInputConf> activeInputs, GpioController gpioController) {
	this.gpioController = gpioController;
	if (activeInputs != null) {
	    init(activeInputs);
	}
    }

    private void init(List<GpioDigInputConf> activeInputs) {

	gpioMap = new HashMap<>();

	for (GpioDigInputConf curGpioSetting : activeInputs) {
	    GpioPinDigitalInput curGpdo = gpioController.provisionDigitalInputPin(curGpioSetting.getPin(),
		    curGpioSetting.getPinPullResistance());
	    gpioMap.put(curGpioSetting.getId(), curGpdo);
	}

    }

    private GpioPinDigitalInput getGpioById(String id) {
	GpioPinDigitalInput gpdi = gpioMap.get(id);
	if (gpdi == null) {
	    throw new IllegalArgumentException("No gpio input with id '" + id + " registered.");
	}
	return gpdi;
    }

    @Override
    public void registerListener(String id, GpioPinListenerDigital listener) {
	GpioPinDigitalInput gpioInput = getGpioById(id);
	gpioInput.addListener(listener);
    }

    public void removeListener(String id, GpioPinListenerDigital listener) {
	GpioPinDigitalInput gpioInput = getGpioById(id);
	gpioInput.removeListener(listener);
    }

    public void removeAllListeners(String id) {
	GpioPinDigitalInput gpioInput = getGpioById(id);
	gpioInput.removeAllListeners();
    }

}
