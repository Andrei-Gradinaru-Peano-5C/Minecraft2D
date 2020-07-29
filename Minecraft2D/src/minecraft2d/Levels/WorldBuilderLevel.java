/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.Levels;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import minecraft2d.blocks.Block;
import minecraft2d.classes.BlockMap;
import minecraft2d.classes.KeyState;
import minecraft2d.classes.Level;
import minecraft2d.entitys.PlayerEntity;

/**
 *
 * @author Andrei
 */
public class WorldBuilderLevel extends Level {
    private int selectedBlock;
    private ArrayList<Block> blocks;
    
    /**
     * Create a new Level where you can edit/build the world. For test purpose
     * @param width Width of the world
     * @param height Height of the world
     */
    public WorldBuilderLevel(int width, int height) {
        super(width, height);
        /*blocks.add(DirtBlock.getInstance());
        blocks.add(StoneBlock.getInstance());
        blocks.add(Oak_logBlock.getInstance());
        blocks.add(Oak_leaves.getInstance());
        blocks.add(Grass_block.getInstance());
        System.out.println(blocks.toString());
        System.out.println(Block.blocks.toString());*/
        blocks = Block.blocks;
        this.entities.add(PlayerEntity.getInstance());
    }
    
    /**
     * Do level logic
     */
    @Override
    public void logic() {
        KeyState ks = this.kChecker.getKeyState();
        Point m = this.getCanvas().getMousePosition();
        
        if(ks.mouseButton1) {
            this.setBlock(BlockMap.getIndex(m.x), BlockMap.getIndex(m.y), blocks.get(selectedBlock));
        } else if (ks.mouseButton3) {
            this.removeBlock(BlockMap.getIndex(m.x), BlockMap.getIndex(m.y));
        }
        
        if(ks.wheelState.changed) {
            if(ks.wheelState.wheelRotation > 0) {
                if(selectedBlock < blocks.size()-1) {
                    selectedBlock++;
                } else {
                    selectedBlock = 0;
                }
            } else {
                if(selectedBlock > 0) {
                    selectedBlock--;
                } else {
                    selectedBlock = blocks.size()-1;
                }
            }
        }
        
        if(ks.f1) {
            this.kChecker.getKeyStatePointer().f1 = false;
            JFileChooser JF = new JFileChooser("src\\minecraft2d\\saves");
            JF.setApproveButtonText("Salva");
            int option = JF.showSaveDialog(null);
            if(option == JFileChooser.APPROVE_OPTION) {
                File f = JF.getSelectedFile();
                BlockMap.saveMap(this, f.getPath());
                System.out.println("OK");
            }
        } else if (ks.f2) {
            this.kChecker.getKeyStatePointer().f2 = false;
            JFileChooser JF = new JFileChooser("src\\minecraft2d\\saves");
            JF.setApproveButtonText("Carica");
            int option = JF.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION) {
                File f = JF.getSelectedFile();
                BlockMap.loadMap(this, f.getPath());
                System.out.println("OK");
            }
        }
        
    }
    
    public void loop() {
        long delta = 0;
        long lastLoopTime = System.nanoTime();
        while(true) {
            this.drawLevel(delta);
            this.logic();
            delta = System.nanoTime() - lastLoopTime; 
            lastLoopTime = System.nanoTime();
            System.out.println(delta);
        }
    }

}