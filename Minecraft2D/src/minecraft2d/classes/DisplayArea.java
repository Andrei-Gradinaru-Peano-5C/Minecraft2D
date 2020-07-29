/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.classes;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Andrei
 */
public class DisplayArea {
    /** Area display */
    private JFrame content;

    /**
     * Create a display area with the folowing title
     * @param name Title of JFrame
     */
    public DisplayArea(String name) {
        this.content = new JFrame(name);
        content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.setResizable(false);
    }
    
    /**
     * Set the active (viewed) level
     * @param level Level to display
     */
    public void setLevel(Level level) {
        JPanel Jpanel = (JPanel) content.getContentPane();
        Jpanel.setPreferredSize(new Dimension(level.preferedWidth, level.preferedHeight));
        Jpanel.setLayout(null);
        Jpanel.removeAll();
        // Now add The Canvas of level to this Frame
        Jpanel.add(level.getCanvas());
        //content.add(Jpanel); 
        content.setIgnoreRepaint(true);
        // Now Resize JFrame
        content.pack();
        this.setVisible(true);
        // Afeter Added createBufferStrategy
        level.createBufferStrategy();
        
    }
    
    /**
     * Set or unset Visibility od this Frame
     * @param b Visibility
     */
    public void setVisible(boolean b) {
        content.setVisible(b);
    }
    
}
