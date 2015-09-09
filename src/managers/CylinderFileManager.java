/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import components.CylinderData;
import mapmaker.MapMaker;
import org.bukkit.Material;
import utils.CustomFile;

/**
 *
 * @author nieto
 */
public class CylinderFileManager {
    private static MapMaker plugin;
    private static String file;
    private static CylinderData data;
    
    public CylinderFileManager(MapMaker instance){
        plugin = instance;
        file = "cylinder.cfg";
        init();
    }
    
    public CylinderData reloadConfig(){
        init();
        return data;
    }
    
    public static CylinderData readNewConfig(String file){
        CylinderFileManager.file = file;
        init();
        return data;
    }
    
    public static CylinderData getData(){
        return data;
    }
    
    private static void init(){
        data = new CylinderData();
        
        try {
            String path = plugin.getDataFolder().getPath() + "/" + CylinderFileManager.file;
            for(String s : CustomFile.getLinesFromFile(path)){
                String[] line = s.split(" = ");
                readerSelector(line[0], line[1]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void readerSelector(String key, String value) throws Exception{
        switch(key){
            case "center":
                readCenter(value);
                break;
            case "radius":
                readRadius(value);
                break;
            case "height":
                readHeight(value);
                break;
            case "material":
                readMaterial(value);
                break;
            default:
                throw new Exception("[ERROR] There was some problem reading cylinder.cfg file.");
        }
    }
    
    private static void readCenter(String line){
        try{
            String[] center = line.split(":");
            CylinderFileManager.data.setCenterX(Integer.parseInt(center[0]));
            CylinderFileManager.data.setCenterZ(Integer.parseInt(center[1]));
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    
    private static void readMaterial(String line){
        CylinderFileManager.data.setMaterial(Material.getMaterial(line));
    }
    
    private static void readHeight(String line){
        CylinderFileManager.data.setHeight(readInt(line));
    }
    
    private static void readRadius(String line){
        CylinderFileManager.data.setRadius(readInt(line));
    }
    
    private static int readInt(String line){
        return Integer.parseInt(line);
    }
}
