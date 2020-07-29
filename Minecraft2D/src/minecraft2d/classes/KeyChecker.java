/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.classes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author Andrei
 */
public class KeyChecker implements KeyListener, MouseListener, MouseWheelListener{
    private KeyState keys = new KeyState();
    
    /**
     * Create a new KeyChecker
     */
    public KeyChecker() {
    }
    
    /**
     * Return a new KeyState wich contain all the info about keys in ths moment
     * @return new KeyState
     */
    public KeyState getKeyState() {
        return new KeyState(this);
    }
    
    /**
     * Return a direct poitner to the keyState od this listener
     * @return KeyState
     */
    public KeyState getKeyStatePointer() {
        return keys;
    }
    
    // Keyboard ----------------------------------------------------------------
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keys.setKey(true, e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keys.setKey(false, e.getKeyCode());
    }
    
    // Mouse Buttons -----------------------------------------------------------

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.keys.setButton(true, e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.keys.setButton(false, e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Mouse Whell -------------------------------------------------------------
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.keys.setWheelRotation(e.getWheelRotation(), System.nanoTime());
    }
    
}