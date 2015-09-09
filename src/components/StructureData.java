/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import org.bukkit.block.Block;

/**
 *
 * @author nieto
 */
public class StructureData implements Serializable {
    private int baseCenterX;
    private int baseCenterY;
    private int baseCenterZ;
    private ArrayList<BlockData> structure_new = null;
    private BlockData[][][] structure = null;
    
    public StructureData(int x, int y, int z){
        structure = new BlockData[x][y][z];
        structure_new = new ArrayList<>();
    }
    
    public void setBaseCenter(int x, int y, int z){
        this.baseCenterX = x;
        this.baseCenterY = y;
        this.baseCenterZ = z;
    }
    
    public void setStructure(Block[][][] structure){
        int x = this.structure.length;
        int y = this.structure[0].length;
        int z = this.structure[0][0].length;
        for(int i = 0; i < x; ++i)
            for(int j = 0; j < y; ++j)
                for(int k = 0; k < z; ++k)
                    this.addBlockAt(structure[i][j][k], i, j, k);
    }
    
    public void setStructure_new(Block[][][] structure){
        int x = structure.length;
        int y = structure[0].length;
        int z = structure[0][0].length;
        for(int i = 0; i < x; ++i)
            for(int j = 0; j < y; ++j)
                for(int k = 0; k < z; ++k)
                    this.addBlockAt_new(structure[i][j][k], i, j, k);
    }
    
    public BlockData[][][] getStructure(){
        return this.structure;
    }
    
    public ArrayList<BlockData> getStructure_new(){
        sortStructure_new();
        return this.structure_new;
    }
    
    public int getBaseCenterX(){
        return this.baseCenterX;
    }
    
    public int getBaseCenterY(){
        return this.baseCenterY;
    }
    
    public int getBaseCenterZ(){
        return this.baseCenterZ;
    }
    
    public boolean addBlockAt(Block b, int x, int y, int z){
        if(this.structure != null){
            this.structure[x][y][z] = new BlockData();
            this.structure[x][y][z].setMaterial(b.getType().toString());
            this.structure[x][y][z].setData(b.getData());
            this.structure[x][y][z].setRawData(b.getState().getRawData());
            return true;
        }
        return false;
    }
    
    public boolean addBlockAt(BlockData b, int x, int y, int z){
        if(this.structure != null){
            this.structure[x][y][z] = b;
            return true;
        }
        return false;
    }
    
    public boolean addBlockAt_new(Block b, int x, int y, int z){
        if(this.structure_new != null){
            BlockData bd = new BlockData();
            bd.setMaterial(b.getType().toString());
            bd.setData(b.getData());
            bd.setRawData(b.getState().getRawData());
            bd.setPosition(x,y,z);
            this.structure_new.add(bd);
            return true;
        }
        return false;
    }
    
    public boolean addBlockAt_new(BlockData b, int x, int y, int z){
        if(this.structure_new != null){
            b.setPosition(x, y, z);
            this.structure_new.add(b);
            return true;
        }
        return false;
    }
    
    private void sortStructure_new(){
        this.structure_new.sort(new Comparator<BlockData>(){

            @Override
            public int compare(BlockData o1, BlockData o2) {
                if(o1.getData() < o2.getData()) return -1;
                else if(o1.getData() > o2.getData()) return 1;
                else {
                    if(o1.getRawData() < o2.getRawData()) return -1;
                    else if(o1.getRawData() > o2.getRawData()) return 1;
                    else return 0;
                }
            }
            
        });
    }
}
