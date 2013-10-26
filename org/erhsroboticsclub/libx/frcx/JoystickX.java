
package org.erhsroboticsclub.libx.frcx;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Class extending the capabilities of the FRC Joystick class.
 * 
 * @author David
 */
public class JoystickX {
    
    private Joystick stick;
    
    boolean[] flags = new boolean[20];
    boolean flag_trigger = false;
    int throttle;
    /**
     * Without the javadoc I can't name these params properly
     * @param arg0 the port, I'm assuming.
     */
    public JoystickX(int arg0) {
        stick = new Joystick(arg0);
    }
    
    /**
     * 
     * @param button
     * @return if the button is currently being held down.
     */
    public boolean isButtonDown(int button) {
        return stick.getRawButton(button);
    }
    
    /**
     * 
     * @param button
     * @return whether the button has been pressed
     */
    public boolean buttonPressed(int button) {
        if(isButtonDown(button) && !flags[button]) {
            return (flags[button] = true);
        }
        else {
            return (flags[button] = false);
        }
    }
    
    /**
     * 
     * @param axis
     * @return some sort of information about the axis
     */
    public double getAxis(int axis) {
        return stick.getRawAxis(axis);
    }
    
    /**
     * Thought this method might be useful
     * @return Direction in degrees
     */
    public double getDirectionDeg() {
        return stick.getDirectionDegrees();
    }
    /**
     * Thought this method might be useful.
     * @return Direction in radians
     */
    public double getDirectionRad() {
        return stick.getDirectionRadians();
    }
    /**
     * Thought this method might be useful.
     * @return magnitude of the joystick
     */
    public double getMagnitude() {
        return stick.getMagnitude();
    }
    
    /**
     * @return position of the throttle
     */
    public double getThrottle() {
        return (throttle = stick.getThrottle());
    }
    /**
     * @return the difference between the current throttle and the value 
     *  recorded the last time getThrottle or getDeltaThrottle were called
     */
    public double getDeltaThrottle() {
        double delta = stick.getThrottle() - throttle;
        getThrottle();
        return delta;
    }
    
    /**
     * @return true if the trigger is currently being pulled
     */
    public boolean getTrigger() {
        return stick.getTrigger();
    }
    
    /**
     * @return true if the trigger has been pressed, 
     *  but not again until after it is released.
     */
    public boolean getTriggerPressed() {
        if(getTrigger() && !flag_trigger) 
            return (flag_trigger = true);
        else return (flag_trigger = false);
    }
    
    /**
     * @return the x value of the joystick
     */
    public double getX() {
        return stick.getX();
    }
    /**
     * @return the y value of the joystick
     */
    public double getY() {
        return stick.getY();
    }
    /**
     * @return the z value of the joystick
     */
    public double getZ() {
        return stick.getZ();
    }
}
