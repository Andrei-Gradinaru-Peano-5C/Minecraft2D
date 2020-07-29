/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.entitys;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import minecraft2d.classes.KeyState;

/**
 *
 * @author Andrei
 */
public abstract class Entity {
    /** Position in X and Y axis the world of the entity*/
    public float x;
    public float y;
    // Hitbox of the entity
    protected Rectangle htibox;
    
    /** Name of the entity */
    protected String name;
    
    /** Speed, Max Speed, Acceleration, JumpPower and Weight of the entity */
    protected float maxMoveSpeed;
    protected float moveSpeed = 0.0f;
    protected float acceleration;
    protected float weight;
    protected float jumpPower;
    
    /** Images will be stored into the specific entity class, that beacuse
     * entity's may have different immages for movement animation or just random
     * skin's ( A zombie may have different skin but same behavior ). Here will
     * put just one image wich will be used for drawing purpose once a cicle.
     */
    protected Image toDrawModel;
    
    /**
     * Draw the entity into the given graphics context, always give the left
     * corner image coordinates, not the center
     * @param g Graphic context
     * @param x X coord
     * @param y Y coord
     * @param e Entity to be drawned
     */
    public static void DrawEntity(Graphics2D g, int x, int y, Entity e) {
        g.drawImage(e.toDrawModel, x, y, null);
    }
    
    public void move(long delta, KeyState ks) {
    }
    
}
