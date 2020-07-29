/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.blocks;

import java.io.Serializable;
import minecraft2d.classes.Loader;

/**
 *
 * @author Andrei
 */
public class Oak_logBlock extends Block implements Serializable{
    /** Unique instance of this class*/
    private static Oak_logBlock unique = new Oak_logBlock();
    
    /**
     * Constructor
     */
    private Oak_logBlock() {
        this.name = "Oak_logBlock";
        this.image = Loader.loadModel("minecraft2d/textures/oak_log.png",this);
        this.blocks.add(this);
    }
    
    /**
     * Get the uniques instance of this class
     * @return The class wich describe a unique block
     */
    public static Oak_logBlock getInstance() {
        return unique;
    }
}
