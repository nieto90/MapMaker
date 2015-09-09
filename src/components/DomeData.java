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
public class DomeData extends Data {
    private int radius;
    private Material material;
    
    public DomeData(){
        this.centerX = 0;
        this.centerY = 0;
        this.centerZ = 0;
        this.radius = 100;
        this.material = Material.GLASS;
    }
    
    public DomeData(int x, int y, int z){
        this.centerX = x;
        this.centerY = y;
        this.centerZ = z;
        this.radius = 100;
        this.material = Material.GLASS;
    }
    
    public DomeData(int x, int y, int z, int r){
        this.centerX = x;
        this.centerY = y;
        this.centerZ = z;
        this.radius = r;
        this.material = Material.GLASS;
    }
    
    public DomeData(int x, int y, int z, int r, Material m){
        this.centerX = x;
        this.centerY = y;
        this.centerZ = z;
        this.radius = r;
        this.material = m;
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
