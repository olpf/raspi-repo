package com.pfausoft.raspi.gpio;

import java.util.List;

import com.pfausoft.raspi.gpio.in.GpioDigInputConf;
import com.pfausoft.raspi.gpio.out.GpioDigOutConf;

public class GpioConfiguration {

    private List<GpioDigOutConf> gpioDigitalOutputDefaultSettings;

    private List<GpioDigInputConf> gpioDigitalInputDefaultSettings;

    public void setGpioDigitalOutputDefaultSettings(List<GpioDigOutConf> gpioDefaultSettings) {
	this.gpioDigitalOutputDefaultSettings = gpioDefaultSettings;
    }

    public List<GpioDigOutConf> getGpioDigitalOutputDefaultSettings() {
	return gpioDigitalOutputDefaultSettings;
    }

    public void setGpioDigitalInputDefaultSettings(List<GpioDigInputConf> gpioDigitalInputDefaultSettings) {
	this.gpioDigitalInputDefaultSettings = gpioDigitalInputDefaultSettings;
    }

    public List<GpioDigInputConf> getGpioDigitalInputDefaultSettings() {
	return gpioDigitalInputDefaultSettings;
    }

}
