package com.pfausoft.raspi.gpio.in;

import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public interface GpioDigitalInputController {

    void registerListener(String id, GpioPinListenerDigital listener);

    void removeListener(String id, GpioPinListenerDigital listener);

    void removeAllListeners(String id);

}
