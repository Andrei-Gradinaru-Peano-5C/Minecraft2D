/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.blocks;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Andrei
 */
public abstract class Block {
    /** Block Pixel Width */
    public final static int WIDTH = 16;
    /** Block Pixel Heigth */
    public final static int HEIGHT = 16;
    /** Model of the Block */
    protected Image image;
    /** Name of the Block */
    public String name;
    /** Block list of all Block*/
    public static ArrayList<Block> blocks = new ArrayList<Block>();
    
    /**
     * Draw The Block at the given coordinates into the given context
     * @param g Graphic context
     * @param x X coordinats
     * @param y Y coordinats
     * @param block Block to draw
     */
    public static void drawBlock(Graphics2D g, int x, int y, Block block){
        g.drawImage(block.image, x, y, null);
    }
    
}
