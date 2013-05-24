package com.pfausoft.raspi.gpio.out;

import com.pfausoft.raspi.gpio.AbstractGpioConf;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

/**
 * The Class GpioDefaultSetting.
 */
public class GpioDigOutConf extends AbstractGpioConf {

    private PinState pinState;

    public GpioDigOutConf() {
	super();
    }

    public GpioDigOutConf(String id, PinState pinState, Pin pin) {
	super(id, pin);
	this.pinState = pinState;
    }

    public void setPinState(PinState pinState) {
	this.pinState = pinState;
    }

    public PinState getPinState() {
	return pinState;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("GpioDigOutConf [id=").append(super.id);
	sb.append(", pin=").append(pin);
	sb.append(", pinState=").append(pinState);
	sb.append("]");
	return sb.toString();
    }

}
