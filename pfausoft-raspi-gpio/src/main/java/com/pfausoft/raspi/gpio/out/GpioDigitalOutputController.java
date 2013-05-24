package com.pfausoft.raspi.gpio.out;

/**
 * The Interface GpioDigitalOutput.
 */
public interface GpioDigitalOutputController {

    /**
     * Pulse a gpio output
     * 
     * @param id the id of the gpio output which was assigned with the
     *            GpioInitialSetting
     * @param durationMs the duration in ms to pulse
     * @param high the state to pulse; true - pulse high state, false - pulse
     *            low state
     * @param block true - do not allow to pulse other gpios during pulse
     */
    public void pulseGpio(String id, long durationMs, boolean high, boolean block);

    /**
     * Toggle the gpio output
     * 
     * @param id the id of the gpio toggle
     */
    public void toggleGpio(String id);

    /**
     * Sets the gpio state.
     * 
     * @param id the id of the gpio
     * @param high true - set to high; false - set to low
     */
    public void setGpioState(String id, boolean high);

    /**
     * Checks the is on gpio at high state.
     * 
     * @param id the id of the gpio
     * @return true - gpio is high; false - gpio is low
     */
    public boolean isGpioAtHighState(String id);

}
