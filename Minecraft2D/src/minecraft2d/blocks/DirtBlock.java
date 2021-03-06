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
public class DirtBlock extends Block implements Serializable{
    /** Unique instance of this class*/
    private static DirtBlock unique = new DirtBlock();
    
    /**
     * Constructor
     */
    private DirtBlock() {
        this.name = "DirtBlock";
        this.image = Loader.loadModel("minecraft2d/textures/dirt.png",(Object)this);
        this.blocks.add(this);
    }
    
    /**
     * Get the uniques instance of this class
     * @return The class wich describe a unique block
     */
    public static DirtBlock getInstance() {
        return unique;
    }
}
