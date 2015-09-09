package components;

import utils.CustomFunctions;

/**
 *
 * @author nieto90
 */
public class LabyrinthData {
    private int height;
    private int width;
    private int radius;
    private int centerX;
    private int centerY;
    private int centerZ;
    private int[][] m;

    public LabyrinthData(){}
    
    public LabyrinthData(int height, int width){
        this.height = height;
        this.width = width;
        m = new int[height][width];
        for(int i = 0; i < this.height; ++i)
            for(int j = 0; j < this.width; ++j)
                m[i][j] = CustomFunctions.randInt(1,3);
    }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public int[][] getM() {
        return m;
    }
    
    
    public int getValue(int x, int y){
        return m[x][y];
    }
    
    public void setValue(int x, int y, int v){
        m[x][y] = v;
    }
    
    public void setCenter(int x, int y, int z){
        this.centerX = x;
        this.centerY = y;
        this.centerZ = z;
        
    }
    
    public int getCenterX(){
        return this.centerX;
    }
    
    public int getCenterY(){
        return this.centerY;
    }
    
    public int getCenterZ(){
        return this.centerZ;
    }
    
    public void setRadius(int r){
        this.radius = r;
    }
    
    public int getRadius(){
        return this.radius;
    }
    
    public void reload(){
        height = radius*2 + 1;
        width = height;
        
        m = new int[height][width];
        for(int i = 0; i < this.height; ++i)
            for(int j = 0; j < this.width; ++j)
                m[i][j] = CustomFunctions.randInt(1,3);
    }
    
}
