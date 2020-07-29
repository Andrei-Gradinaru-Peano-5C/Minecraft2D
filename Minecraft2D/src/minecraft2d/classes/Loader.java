/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.classes;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import minecraft2d.blocks.DirtBlock;
import minecraft2d.blocks.Grass_block;
import minecraft2d.blocks.Oak_leaves;
import minecraft2d.blocks.Oak_logBlock;
import minecraft2d.blocks.StoneBlock;
import minecraft2d.entitys.Entity;
import minecraft2d.entitys.PlayerEntity;

/**
 *
 * @author Andrei
 */
public abstract class Loader {
    /**
     * Load The Models into the JVM
     */
    public static void loadModels() {
        DirtBlock.getInstance();
        StoneBlock.getInstance();
        Grass_block.getInstance();
        Oak_leaves.getInstance();
        Oak_logBlock.getInstance();
        PlayerEntity.getInstance();
    }
    
    /**
     * Method wich load the model
     * @param path URL to the resource
     * @param e entity wich is loading the model
     * @return Image to draw
     */
    public static Image loadModel(String path, Object e) {
        BufferedImage sourceImage = null;
        Image image;
        
        // Loading of the model
        try {
            URL url = e.getClass().getClassLoader().getResource(path);
            if(url == null) {
                fail("Error: Cannont find resource for (" + e.toString() + ")");
            }
            sourceImage = ImageIO.read(url);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        // Creating an accelerated image where to put the image
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);
        // Put the image into the new accelerated image
        image.getGraphics().drawImage(sourceImage, 0, 0, null);
        
        return image;
    }
    
    /**
     * Utility method to handle varoius errors
     * @param text Error message to display
     */
    private static void fail(String text) {
        System.out.println(text);
        System.exit(-1);
    }
    
}
