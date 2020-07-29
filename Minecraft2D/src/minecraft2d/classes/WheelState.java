/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.classes;

/**
 *
 * @author Andrei
 */
public class WheelState { 
    public int wheelRotation = 0;
    public long lastTimeMoved = 0;
    public boolean changed = false;

    public WheelState() {}

    /**
     * Copy Constructor
     * @param ws WhellState to clone
     */
    public WheelState(WheelState ws) {
        this.wheelRotation = ws.wheelRotation;
        this.lastTimeMoved = ws.lastTimeMoved;
    }

    /**
     * Control if WheelState had some changes
     * @param ws the new WheelState to check with
     * @return 
     */
    public boolean isUpdated(WheelState ws) {
        if(this.lastTimeMoved != ws.lastTimeMoved) {
            return true;
        } else {
            return false;
        }
    }
}
