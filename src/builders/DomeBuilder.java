/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builders;

import components.DomeData;
import mapmaker.MapMaker;
import org.bukkit.World;
import utils.CustomFunctions;

/**
 *
 * @author nieto
 */
public class DomeBuilder {
    private static MapMaker plugin;
    
    public DomeBuilder(MapMaker instance){
        DomeBuilder.plugin = instance;
    }
    
    public static void buildDome(DomeData data) {
        int x = data.getCenterX();
        int y = data.getCenterY();
        int z = data.getCenterZ();
        int r = data.getRadius();
        
        World w = plugin.getServer().getWorld("world");
        
        for(int i = x - r - 2; i < x + r + 2; ++i){
            for(int j = y - r - 2; j < y + r + 2; ++j){
                for(int k = z - r - 2; k < z + r + 2; ++k){
                    if(CustomFunctions.isAtDistance(x,y,z,i,j,k,r))
                        w.getBlockAt(i, j, k).setType(data.getMaterial());
                }
            }
        }
    }
}
