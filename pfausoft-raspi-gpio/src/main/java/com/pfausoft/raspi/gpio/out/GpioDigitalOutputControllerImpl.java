package com.pfausoft.raspi.gpio.out;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

/**
 * The Class GpioDigitalOutputImpl.
 */
public class GpioDigitalOutputControllerImpl implements GpioDigitalOutputController {

    private static Map<String, GpioPinDigitalOutput> gpioMap;
    private final GpioController gpioController;

    /**
     * Instantiates a new gpio digital output impl.
     * 
     * @param activeGpios the active gpios
     */
    public GpioDigitalOutputControllerImpl(List<GpioDigOutConf> activeGpios, GpioController gpioController) {
	this.gpioController = gpioController;
	if (activeGpios == null || activeGpios.isEmpty()) {
	    throw new IllegalArgumentException("No active gpios configured");

	}

	init(activeGpios);
    }

    @Override
    public void pulseGpio(String id, long durationMs, boolean high, boolean block) {
	GpioPinDigitalOutput gpdo = getGpioById(id);
	gpdo.pulse(durationMs, high ? PinState.HIGH : PinState.LOW, block);
    }

    @Override
    public void toggleGpio(String id) {
	GpioPinDigitalOutput gpdo = getGpioById(id);
	gpdo.toggle();
    }

    @Override
    public void setGpioState(String id, boolean high) {
	GpioPinDigitalOutput gpdo = getGpioById(id);
	gpdo.setState(high ? PinState.HIGH : PinState.LOW);
    }

    @Override
    public boolean isGpioAtHighState(String id) {
	GpioPinDigitalOutput gpdo = getGpioById(id);
	PinState state = gpdo.getState();
	return state == PinState.HIGH;
    }

    private void init(List<GpioDigOutConf> activeGpios) {

	gpioMap = new HashMap<>();

	for (GpioDigOutConf curGpioSetting : activeGpios) {
	    GpioPinDigitalOutput curGpdo = gpioController.provisionDigitalOutputPin(curGpioSetting.getPin(),
		    curGpioSetting.getPinState());
	    gpioMap.put(curGpioSetting.getId(), curGpdo);
	}

    }

    private GpioPinDigitalOutput getGpioById(String id) {
	GpioPinDigitalOutput gpdo = gpioMap.get(id);
	if (gpdo == null) {
	    throw new IllegalArgumentException("No gpio with id '" + id + " registered.");
	}
	return gpdo;
    }
}
