/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.io.Serializable;
import org.bukkit.Material;

/**
 *
 * @author nieto
 */
public class BlockData implements Serializable {
    private String material;
    private byte data;
    private byte rawData;
    private int x;
    private int y;
    private int z;
    
    public BlockData(){
        this.material = Material.AIR.toString();
        this.data = 0;
        this.rawData = 0;
    }
    
    public BlockData(String material){
        this.material = material;
        this.data = 0;
        this.rawData = 0;
    }
    
    public void setMaterial(String material){
        this.material = material;
    }
    
    public Material getMaterial(){
        return Material.getMaterial(this.material);
    }
    
    public void setData(byte data){
        this.data = data;
    }
    
    public byte getData(){
        return this.data;
    }
    
    public void setRawData(byte data){
        this.rawData = data;
    }
    
    public byte getRawData(){
        return this.rawData;
    }
    
    public void setPosition(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
}
