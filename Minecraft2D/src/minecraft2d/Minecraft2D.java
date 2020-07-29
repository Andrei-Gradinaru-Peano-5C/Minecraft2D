/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d;

import minecraft2d.Levels.WorldBuilderLevel;
import minecraft2d.classes.DisplayArea;
import minecraft2d.classes.Loader;

/**
 *
 * @author Andrei
 */
public class Minecraft2D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DisplayArea test = new DisplayArea("Test Baby");
        Loader.loadModels();
        WorldBuilderLevel wl = new WorldBuilderLevel(800,592);

        test.setLevel(wl);
        
        //System.out.println(BlockMap.loadMap(wl, "src\\minecraft2d\\banana.txt"));
       
        try {
            wl.loop();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
         
    }
    
}
