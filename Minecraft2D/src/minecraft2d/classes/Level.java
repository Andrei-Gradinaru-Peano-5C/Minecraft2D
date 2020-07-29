/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.classes;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import minecraft2d.blocks.Block;
import minecraft2d.entitys.Entity;

/**
 *
 * @author Andrei
 */
public abstract class Level extends BlockMap {
    /** Graphics */
    private Canvas canvas = new Canvas();
    protected BufferStrategy bufferStrategy;
    
    /** Info about window */
    public int preferedWidth;
    public int preferedHeight;
    
    /** Listeners */
    protected KeyChecker kChecker = new KeyChecker();
    
    /** Info's about level */
    protected String name;
    protected Image bakcground;
    protected ArrayList<Entity> entities = new ArrayList<Entity>();

    /**
     * Create a new Level and determine Canvas Dimensions
     * @param width Width of level
     * @param height Heigth of level
     */
    public Level(int width, int height) {
        super(width,height);
        this.canvas.setBounds(0, 0, width, height);
        this.preferedHeight = height;
        this.preferedWidth = width;
        
        // Adding listeners
        this.canvas.addKeyListener(kChecker);
        this.canvas.addMouseListener(kChecker);
        this.canvas.addMouseWheelListener(kChecker);
    }
    
    /**
     * Get Graphics canvas of this level
     * @return Canvas of this level
     */
    public Canvas getCanvas() {
        return this.canvas;
    }
    
    /**
     * Create StrategyBufffers, need to be called after this canvas was
     * added to a visible Frame
     */
    public void createBufferStrategy() {
        this.canvas.createBufferStrategy(2);
        this.bufferStrategy = this.canvas.getBufferStrategy();
    }
    
    /**
     * Draw the level scene
     */
    public void drawLevel(long delta) {
        System.out.println(delta);
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        this.entities.get(0).move(delta,this.kChecker.getKeyState());
        try {
            g.clearRect(0, 0, this.preferedWidth, this.preferedHeight);
            //g.drawImage(bakcground, 0, 0, null);
            for(int xIndex=0; xIndex<this.indexesX; xIndex++) {
                for(int yIndex=0; yIndex<this.indexesY; yIndex++) {
                    if(this.getBlock(xIndex, yIndex) != null) {
                        Block.drawBlock(g, 
                                BlockMap.getCoordinate(xIndex), 
                                BlockMap.getCoordinate(yIndex), 
                                this.getBlock(xIndex, yIndex));
                    }
                }
            }
            Entity.DrawEntity(g, (int)this.entities.get(0).x, (int)this.entities.get(0).y, this.entities.get(0));
            //System.out.println(this.entities.get(0).x);
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        finally {
            g.dispose();
            bufferStrategy.show();
        }
    }
    
    public abstract void logic();
    
}
