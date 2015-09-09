/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import org.bukkit.Material;

/**
 *
 * @author nieto
 */
public class CylinderData extends Data {
    private int height;
    private int radius;
    private Material material;
    
    public CylinderData(){
        this.centerX = 0;
        this.centerZ = 0;
        this.height = 200;
        this.radius = 100;
        this.material = Material.GLASS;
    }
    
    public CylinderData(int x, int z){
        this.centerX = x;
        this.centerZ = z;
        this.height = 200;
        this.radius = 100;
        this.material = Material.GLASS;
    }
    
    public CylinderData(int x, int z, int h){
        this.centerX = x;
        this.centerZ = z;
        this.height= h;
        this.radius = 100;
        this.material = Material.GLASS;
    }
    
    public CylinderData(int x, int z, int h, int r){
        this.centerX = x;
        this.centerZ = z;
        this.height= h;
        this.radius = r;
        this.material = Material.GLASS;
    }
    
    public CylinderData(int x, int z, int h, int r, Material m){
        this.centerX = x;
        this.centerZ = z;
        this.height= h;
        this.radius = r;
        this.material = m;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int heigh) {
        this.height = heigh;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    
}
