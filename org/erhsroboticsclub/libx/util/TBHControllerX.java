package org.erhsroboticsclub.frc2014.utilities;

/**
 *
 * @author nick
 */
public class TBHControllerX {

    private double gain, setpoint;
    private double previousError;
    private double response;
    private double previousResponse;

    public TBHControllerX(double gain, double setpoint) {
        this.gain = gain;
        this.setpoint = setpoint;
    }

    public double getTBHResponse(double input) {
        double error = setpoint - input;
        
        response += gain * error;
        response = clamp(response, 0, 1);
        
        if (isPositive(previousError) != isPositive(error)) {
            response = (response + previousResponse) / 2;
            previousResponse = response;
            previousError = error;
        }
        return response;
    }
    
    /**************************
     * Mutators and Accessors *
     **************************/
    public void setGain(double gain) {
        this.gain = gain;
    }
    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }
    public double getGain() {
        return this.gain;
    }
    public double getSetpoint() {
        return this.setpoint;
    }
    
    /****************************
     * Private Helper Functions *
     ****************************/
    private boolean isPositive(double num) {
        return num > 0;
    }
    private double clamp(double input, double min, double max) {
        if (input <= min) {
            return min;
        } else if (input >= max) {
            return max;
        }
        return input;
    }
}
