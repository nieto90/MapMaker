/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builders;

import components.BlockData;
import components.LabyrinthData;
import components.StructureData;
import mapmaker.MapMaker;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 *
 * @author nieto
 */
public class StructureBuilder {
    private static MapMaker plugin;
    
    public StructureBuilder(MapMaker instance){
        StructureBuilder.plugin = instance;
    }
    
    public static void buildStructure(StructureData data, Location center) {
        BlockData[][][] str = data.getStructure();
        
        int toX = str.length;
        int toY = str[0].length;
        int toZ = str[0][0].length;
        
        int radX = toX/2;
        int radZ = toZ/2;
        
        int cenX = center.getBlockX();
        int cenY = center.getBlockY();
        int cenZ = center.getBlockZ();
        
        World w = plugin.getServer().getWorld("world");
        
        for(int i = 0; i < toX; ++i){
            for(int j = 0; j < toY; ++j){
                for(int k = 0; k < toZ; ++k){
                    if(str[i][j][k].getData() == 0){
                        Block b = w.getBlockAt(cenX-radX+i, cenY+j, cenZ-radZ+k);
                        b.setType(str[i][j][k].getMaterial());
                        b.getState().setRawData(str[i][j][k].getRawData());
                        b.setData(str[i][j][k].getData());
                    }
                }
            }
        }
        
        for(int i = 0; i < toX; ++i){
            for(int j = 0; j < toY; ++j){
                for(int k = 0; k < toZ; ++k){
                    if(str[i][j][k].getData() != 0){
                        Block b = w.getBlockAt(cenX-radX+i, cenY+j, cenZ-radZ+k);
                        b.setType(str[i][j][k].getMaterial());
                        b.getState().setRawData(str[i][j][k].getRawData());
                        b.setData(str[i][j][k].getData());
                    }
                }
            }
        }
    }
    
    public static StructureData createStructureData(Location center, int radX, int height, int radZ){
        int cenX = center.getBlockX();
        int cenY = center.getBlockY();
        int cenZ = center.getBlockZ();
        
        int fromX = cenX - radX;
        int fromY = cenY;
        int fromZ = cenZ - radZ;
        
        Block[][][] str = new Block[radX*2+1][height][radZ*2+1];
        
        World w = plugin.getServer().getWorld("world");
        
        for(int i = 0; i <= radX*2; ++i){
            for(int j = 0; j < height; ++j){
                for(int k = 0; k <= radZ*2; ++k){
                    str[i][j][k] = w.getBlockAt(fromX+i,fromY+j,fromZ+k);
                }
            }
        }
        
        StructureData data = new StructureData(radX*2,height,radZ*2);
        data.setBaseCenter(cenX, cenY, cenZ);
        data.setStructure(str);
        
        return data;
    }
    
    public static void buildLabyrinth(Location center, LabyrinthData data){
        buildStructure(LabyrinthBuilder.generateLabyrinth(center, data), center);
    }
}
