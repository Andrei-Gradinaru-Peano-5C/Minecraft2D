/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.entitys;

import minecraft2d.classes.KeyState;
import minecraft2d.classes.Loader;

/**
 *
 * @author Andrei
 */
public class PlayerEntity extends Entity{
    /** Unique instance of this class*/
    private static PlayerEntity unique = new PlayerEntity();

    /**
     * Constructor
     */
    private PlayerEntity() {
        this.toDrawModel = Loader.loadModel("minecraft2d\\textures\\stevePiccolo.png", (Object)this);
        this.acceleration = 1f;
    }
    
    /**
     * Get the uniques instance of this class
     * @return The class wich describe a unique entity
     */
    public static PlayerEntity getInstance() {
        return unique;
    }
    
    @Override
    public void move(long delta, KeyState ks) {
        if(ks.a) {
            this.moveSpeed -= (this.acceleration * (delta/Math.pow(10, 9)));
        }
        if(ks.d) {
            this.moveSpeed += (this.acceleration * (delta/Math.pow(10, 9)));
        }
        this.x += this.moveSpeed;
        //System.out.println(delta);
    }
    
}
