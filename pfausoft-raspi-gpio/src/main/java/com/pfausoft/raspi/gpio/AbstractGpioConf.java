package com.pfausoft.raspi.gpio;

import com.pi4j.io.gpio.Pin;

public class AbstractGpioConf {

    protected String id;
    protected Pin pin;

    public AbstractGpioConf() {

    }

    public AbstractGpioConf(String id, Pin pin) {
	this.id = id;
	this.pin = pin;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setPin(Pin pin) {
	this.pin = pin;
    }

    public String getId() {
	return id;
    }

    public Pin getPin() {
	return pin;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractGpioConf other = (AbstractGpioConf) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
