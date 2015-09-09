/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builders;

import components.CylinderData;
import mapmaker.MapMaker;
import org.bukkit.World;
import utils.CustomFunctions;

/**
 *
 * @author nieto
 */
public class CylinderBuilder {
    private static MapMaker plugin;
    
    public CylinderBuilder(MapMaker instance){
        this.plugin = instance;
    }
    
    public static void buildCylinder(CylinderData data) {
        int x = data.getCenterX();
        int z = data.getCenterZ();
        int r = data.getRadius();
        int h = data.getHeight();
        
        World w = plugin.getServer().getWorld("world");
        
        for(int i = x - r - 2; i < x + r + 2; ++i){
            for(int k = z - r - 2; k < z + r + 2; ++k){
                if(CustomFunctions.isAtDistance(x,z,i,k,r)){
                    for(int j = 0; j <= h; ++j){
                        w.getBlockAt(i, j, k).setType(data.getMaterial());
                    }
                } else if (CustomFunctions.compareDistances(x,z,i,k,r) < 0) {
                    w.getBlockAt(i, h, k).setType(data.getMaterial());
                }
            }
        }
    }
    
}
