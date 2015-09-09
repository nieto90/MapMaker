/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import components.StructureData;
import mapmaker.MapMaker;
import utils.CustomFile;

/**
 *
 * @author nieto
 */
public class StructureFileManager {
    private static MapMaker plugin;
    private static StructureData data;
    
    public StructureFileManager(MapMaker instance){
        plugin = instance;
    }
    
    public static void loadStructureFromFile(String file){
        String path = plugin.getDataFolder().getPath() + "/structures/" + file;
        data = (StructureData) CustomFile.readObject(path);
    }
    
    public static void saveStructureToFile(StructureData data, String file){
        String path = plugin.getDataFolder().getPath() + "/structures/" + file;
        CustomFile.writeObject(data, path);
    }
    
    
    public static StructureData getData(){
        return data;
    }
}
