/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builders;

import components.BlockData;
import components.LabyrinthData;
import components.StructureData;
import org.bukkit.Location;
import org.bukkit.Material;
import utils.CustomFunctions;

/**
 *
 * @author nieto
 */
public class LabyrinthBuilder {
    private static StructureData labyrinth;
    private static LabyrinthData m;
    
    public LabyrinthBuilder(){
        labyrinth = null;
        m = null;
    }
    
    public LabyrinthBuilder(Location loc, int radius){
        labyrinth = new StructureData(radius*2 + 1, 5, radius*2 + 1);
        labyrinth.setBaseCenter(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        m = new LabyrinthData(radius*2 + 1, radius*2 + 1);
    }
    
    public StructureData getLabyrinth(){
        return labyrinth;
    }
    
    public static StructureData generateLabyrinth(Location loc, LabyrinthData data){
        labyrinth = new StructureData(data.getRadius()*2 + 1, 5, data.getRadius()*2 + 1);
        labyrinth.setBaseCenter(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        m = data;
        return generateLabyrinth();
    }
    
    public static StructureData generateLabyrinth(){
        generateRandomMaze();
        
        BlockData wall = new BlockData(Material.STONE.toString());
        BlockData air = new BlockData(Material.AIR.toString());
        
        for(int i = 0; i < m.getWidth(); ++i){
            for(int k = 0; k < m.getHeight(); ++k){
                if(m.getValue(i, k) > 0){
                    for(int j = 0; j < 5; ++j){
                        labyrinth.addBlockAt(wall, i, j, k);
                    }
                } else {
                    for(int j = 0; j < 5; ++j){
                        labyrinth.addBlockAt(air, i, j, k);
                    }
                }
            }
        }
        return labyrinth;
    }
    
    private static void generateRandomMaze(){
        int cx = (m.getHeight()-1)/2 + 1;
        int cy = (m.getWidth()-1)/2 + 1;
        
        genRandomPath(m, cx, cy, 10, -1);
        
        for(int i = 1; i < m.getHeight()-1; ++i)
            for(int j = 1; j < m.getWidth()-1; ++j){
                if(m.getValue(i, j) == 10)
                    m.setValue(i, j, 0);
                else {
                    if(CustomFunctions.randInt(0, 3) == 0)
                        m.setValue(i, j, 0);
                }
            }
    }
    
    private static boolean genRandomPath(LabyrinthData m, int x, int y, int d, int pd){
        if(x < 0 || y < 0 || x > m.getHeight() - 1 || y > m.getWidth() - 1) return false;
        
        if(m.getValue(x, y)==d) return false;
        
        m.setValue(x, y, d);
        
        int fd = 0;

	if(pd == 0){							//UP
            m.setValue(x+1, y, 0);
            fd = 1;
	} else if(pd == 1){						//DOWN
            m.setValue(x-1, y, 0);
            fd = 0;
	} else if(pd == 2){						//LEFT
            m.setValue(x, y+1, 0);
            fd = 3;
	} else if(pd == 3){						//RIGHT
            m.setValue(x, y-1, 0);
            fd = 2;
	}
        
        int dir = CustomFunctions.randInt(0, 3);

        for(int i = 0; i < 4; ++i){
            dir = (dir+1)%4;
            if(dir != fd){
                if(dir == 0){							//UP
                    if(genRandomPath(m,x-2,y,d,dir)) return true;
                } else if(dir == 1){						//DOWN
                    if(genRandomPath(m,x+2,y,d,dir)) return true;
                } else if(dir == 2){						//LEFT
                    if(genRandomPath(m,x,y-2,d,dir)) return true;
                } else if(dir == 3){						//RIGHT
                    if(genRandomPath(m,x,y+2,d,dir)) return true;
                }
            }
        }
        return false;
    }
}
