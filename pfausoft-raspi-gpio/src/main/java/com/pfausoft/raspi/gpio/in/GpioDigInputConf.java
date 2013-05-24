package com.pfausoft.raspi.gpio.in;

import com.pfausoft.raspi.gpio.AbstractGpioConf;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;

public class GpioDigInputConf extends AbstractGpioConf {

    private PinPullResistance pinPullResistance;

    public GpioDigInputConf() {
	super();
    }

    public GpioDigInputConf(String id, Pin pin, PinPullResistance pullResistance) {
	super(id, pin);
	this.pinPullResistance = pullResistance;
    }

    public PinPullResistance getPinPullResistance() {
	return pinPullResistance;
    }

    public void setPinPullResistance(PinPullResistance pinPullResistance) {
	this.pinPullResistance = pinPullResistance;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("GpioDigInputConf [id=").append(super.id);
	sb.append(", pin=").append(pin);
	sb.append(", pinPullResistance==").append(pinPullResistance);
	sb.append("]");
	return sb.toString();

    }

}
