/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.classes;

import java.awt.event.KeyEvent;

/**
 *
 * @author Andrei
 */
public class KeyState {
    /** Here will be stored all keys info for 1 logic cicle */
    public boolean w = false;
    public boolean a = false;
    public boolean s = false;
    public boolean d = false;
    public boolean space = false;
    public boolean mouseButton1 = false;
    public boolean mouseButton3 = false;
    public boolean f1 = false;
    public boolean f2 = false;
    public boolean esc = false;
    
    /** Whell State info's */
    public WheelState wheelState = new WheelState();
    /* Static class to understand when whellState changes **/
    public static long lastTimeMoved = 0;
    
    /**
     * Create a new KeyState with the value of the folowing KeyChecker, this is
     * usefull is you want to have the current keyCombo and use this to do some
     * logic.
     * @param l KeyChecker to copy KeyState
     */
    public KeyState(KeyChecker l) {
        KeyState k = l.getKeyStatePointer();
        WheelState ws = k.wheelState;
        
        this.a = k.a;
        this.w = k.w;
        this.s = k.s;
        this.d = k.d;
        this.space = k.space;
        this.mouseButton1 = k.mouseButton1;
        this.mouseButton3 = k.mouseButton3;
        this.f1 = k.f1;
        this.f2 = k.f2;
        this.esc = k.esc;
        this.wheelState.wheelRotation = ws.wheelRotation;
        this.wheelState.lastTimeMoved = ws.lastTimeMoved;
        
        if(this.lastTimeMoved != this.wheelState.lastTimeMoved) {
           this.lastTimeMoved = this.wheelState.lastTimeMoved;
           this.wheelState.changed = true;
        } else {
            this.wheelState.changed = false;
        }
    }
    
    /**
     * Create a new KeyState
     */
    public KeyState() {
    }
    
    /**
     * If Existe, set the folowing key at the determined state
     * @param b key State
     * @param keyCode keyCode
     */
    public void setKey(boolean b, int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_A : this.a = b; break;
            case KeyEvent.VK_W : this.w = b; break;
            case KeyEvent.VK_S : this.s = b; break;
            case KeyEvent.VK_D : this.d = b; break;
            case KeyEvent.VK_SPACE : this.space = b; break;
            case KeyEvent.VK_F1 : this.f1 = b; break;
            case KeyEvent.VK_F2 : this.f2 = b; break;
            case KeyEvent.VK_ESCAPE : this.esc = b; break;
        }
    }
    
    /**
     * Set the folowing mouse button at the determined state
     * @param b button State
     * @param buttonCode buttonCode
     */
    public void setButton(boolean b, int buttonCode) {
        switch(buttonCode) {
            case 1 : this.mouseButton1 = b; break;
            case 3 : this.mouseButton3 = b; break;
        }
    }
    
    /**
     * Set Wheel Rotation
     * @param wheelRotation Whell Rotation
     * @param timeMoved Time of current action (NanoSeconds)
     */
    public void setWheelRotation(int wheelRotation, long timeMoved) {
        this.wheelState.wheelRotation = wheelRotation;
        this.wheelState.lastTimeMoved = timeMoved;
    }
    
}
