
package org.erhsroboticsclub.libx.frcx;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Class extending the capabilities of the FRC Joystick class.
 * 
 * @author David
 */
public class JoystickX {
    
    private Joystick stick;
    private final int MAX_BUTTON = 20;
    private final int MAX_AXIS = 10;
    private boolean[] flags = new boolean[MAX_BUTTON];
    private boolean flag_trigger;
    private double throttle;
    /**
     * Wrapper for the FRC Joystick Class 
     * @param port
     */
    public JoystickX(int port) {
        stick = new Joystick(port);
    }
    
    
    public Joystick getFRCJoystick() {
        return stick;
    }
    /**
     * 
     * @param button
     * @return if the button is currently being held down.
     */
    public boolean isButtonDown(int button) {
        if(button >= MAX_BUTTON || button <= 0) return false;
        return stick.getRawButton(button);
    }
    
    /**
     * 
     * @param button
     * @return whether the button has been pressed
     */
    public boolean buttonPressed(int button) {
        if(button >= MAX_BUTTON || button <= 0) return false;
        if(isButtonDown(button)) {
            if(!flags[button]) 
                return (flags[button] = true);
            else return false;
        }
        else return (flags[button] = false);
    }
    
    /**
     * 
     * @param axis
     * @return some sort of information about the axis
     */
    public double getAxis(int axis) {
        if(axis >= MAX_AXIS || axis <= 0) return -1;
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
        if(getTrigger()) {
            if(!flag_trigger) 
                return (flag_trigger = true);
            else return false;
        }
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
