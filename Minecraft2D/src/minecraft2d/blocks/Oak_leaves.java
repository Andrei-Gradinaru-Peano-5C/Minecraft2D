/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.blocks;

import minecraft2d.classes.Loader;

/**
 *
 * @author Andrei
 */
public class Oak_leaves extends Block{
    /** Unique instance of this class*/
    private static Oak_leaves unique = new Oak_leaves();
    
    /**
     * Constructor
     */
    private Oak_leaves() {
        this.name = "Oak_leaves";
        this.image = Loader.loadModel("minecraft2d/textures/oak_leaves.png",this);
        this.blocks.add(this);
    }
    
    /**
     * Get the uniques instance of this class
     * @return The class wich describe a unique block
     */
    public static Oak_leaves getInstance() {
        return unique;
    }
}
