/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft2d.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import minecraft2d.blocks.Block;

/**
 *
 * @author Andrei
 */
public class BlockMap {
    /** 
     * 2Dimension ArrayList wich contain all blocks, Bassicly and arrayList
     * wich hold all arrayList of a line of blocks.
     * 
     * First arrayList contains X coords, and seconds arrayList holds all
     * blocks for this line
     * 
     * ----► X
     *    |
     *    |
     *    ▼ Y
     */
    private ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();
    protected int indexesX;
    protected int indexesY;
    
    /**
     * Creates a new Map to store Blocks
     */
    public BlockMap(int width, int height) {
        this.indexesX = (int) (width/Block.WIDTH);
        this.indexesY = (int) (height/Block.WIDTH);
        this.inizializeMap();
    }
    
    /**
     * Get the block at the requested Indexes
     * @param xIndex
     * @param yIndex
     * @return Block entity
     */
    public Block getBlock(int xIndex, int yIndex) {
        return this.blocks.get(xIndex).get(yIndex);
    }
    
    /**
     * Set the block at given indexex
     * @param xIndex
     * @param yIndex
     * @param block 
     */
    public void setBlock(int xIndex, int yIndex, Block block) {
        this.blocks.get(xIndex).remove(yIndex);
        this.blocks.get(xIndex).add(yIndex, block);
    }
    
    /**
     * Remove the block at given idexes, if aleardy null nothing happens
     * @param xIndex
     * @param yIndex 
     */
    public void removeBlock(int xIndex, int yIndex) {
        this.setBlock(xIndex, yIndex, null);
    }
    
    /**
     * Iniziale the memory for arrayList's wich contains the blocks
     */
    private void inizializeMap() {
        for(int x=0; x<this.indexesX; x++) {
            // Adds and arrayList for each collum
            this.blocks.add(new ArrayList<Block>());
            for(int y=0; y<this.indexesY; y++) {
                // Adds null just for reserving the space for each row in this collum
                this.blocks.get(x).add(null);
            }
        }
    }
    
    /**
     * Get the index of the folowing line X / Y
     * @param coord Coordinate X or Y 
     * @return the index of block in this coordinate
     */
    public static int getIndex(int coord) {
        return (int) (coord/Block.HEIGHT);
    }
    
    /**
     * Get the coordinate of the folowing index
     * @param index
     * @return 
     */
    public static int getCoordinate(int index) {
        return (int) (Block.HEIGHT*index);
    }
    
    /**
     * Method wich save Atcual Level to the wanted Filename
     * @param map
     * @param fileName filePath + name
     * @return true is file was succesfully saved
     */
    public static boolean saveMap(BlockMap map, String fileName) {
        boolean state = false;
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(fileName);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            
            // Map wich stores only name of the blocks
            ArrayList<ArrayList<String>> blocks = new ArrayList<ArrayList<String>>();
            
            //inizialzie the map first
            for(int x=0; x<map.indexesX; x++) {
                // Adds and arrayList for each collum
                blocks.add(new ArrayList<String>());
                for(int y=0; y<map.indexesY; y++) {
                    // Adds null just for reserving the space for each row in this collum
                    blocks.get(x).add(null);
                }
            }
            
            //Load actual blocks name into the map to save
            for(int x=0; x<map.indexesX; x++) {
                for(int y=0; y<map.indexesY; y++) {
                    // Adds Block just for reserving the space for each row in this collum
                    if(map.blocks.get(x).get(y) != null) {
                        blocks.get(x).remove(y);
                        blocks.get(x).add(y ,map.blocks.get(x).get(y).name);
                        //System.out.println(blocks.size());
                    }
                }
                System.out.println( x + " : " + blocks.get(x).toString());
            }
            
            System.out.println("Size: " + blocks.size());
            
            oOut.writeObject(blocks);
            oOut.close();
            state = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BlockMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlockMap.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fOut.close();
            } catch (IOException ex) {
                Logger.getLogger(BlockMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return state;
    }
    
    /**
     * Method wich loadMap from 
     * @param map Level in wich to load the map
     * @param fileName Path to file
     * @return Re
     */
    public static boolean loadMap(BlockMap map, String fileName) {
        boolean state = false;
        FileInputStream fIn = null;
        try {
            fIn = new FileInputStream(fileName);
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            ArrayList<ArrayList<String>> blocksLoaded = (ArrayList<ArrayList<String>>)oIn.readObject();
            state = true;
            System.out.println("x size: " + blocksLoaded.size());
            map.indexesX = blocksLoaded.size();
            map.indexesY = blocksLoaded.get(0).size();
            map.blocks = new ArrayList<ArrayList<Block>>();
            
            for(int x=0; x<map.indexesX; x++) {
                // Adds and arrayList for each collum
                map.blocks.add(new ArrayList<Block>());
                for(int y=0; y<map.indexesY; y++) {
                    // Adds null just for reserving the space for each row in this collum
                    map.blocks.get(x).add(null);
                }
            }
            
            //System.out.println(blocksLoaded.size());
            //System.out.println(blocksLoaded.get(0).size());
            //System.out.println(blocksLoaded.get(0).size());
            //System.out.println(Block.blocks.size());
            //System.out.println(blocksLoaded.get(1).get(1));
            
            for(int x=0; x<map.indexesX; x++) {
                // Adds and arrayList for each collum
                map.blocks.add(new ArrayList<Block>());
                for(int y=0; y<map.indexesY; y++) {
                    // Adds The Block for each row in this collum
                    Block block = null;
                    for(int i=0; i<Block.blocks.size(); i++) {
                        /*System.out.println(Block.blocks.size());
                        System.out.println("i : " + i);*/
                        if( Block.blocks.get(i).name.equals(blocksLoaded.get(x).get(y)) ) {
                            block = Block.blocks.get(i);
                            map.blocks.get(x).remove(y);
                            map.blocks.get(x).add(y, block);
                        }
                    }
                    //System.out.println("Block " + y + ": " + blocksLoaded.get(x).get(y));
                }
                System.out.println("Block x->" + x + ": " + blocksLoaded.get(x));
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BlockMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlockMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlockMap.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fIn.close();
            } catch (IOException ex) {
                Logger.getLogger(BlockMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return state;
    }
}
