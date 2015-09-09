/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author nieto
 */
public abstract class Data {
    protected int centerX;
    protected int centerY;
    protected int centerZ;
    
    
    public Data(){}

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getCenterZ() {
        return centerZ;
    }

    public void setCenterZ(int centerZ) {
        this.centerZ = centerZ;
    }
    
    
    
}
