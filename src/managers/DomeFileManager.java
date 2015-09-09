/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import components.DomeData;
import mapmaker.MapMaker;
import org.bukkit.Material;
import utils.CustomFile;

/**
 *
 * @author nieto
 */
public class DomeFileManager {
    private static MapMaker plugin;
    private static String file;
    private static DomeData data;
    
    public DomeFileManager(MapMaker instance){
        plugin = instance;
        file = "dome.cfg";
        init();
    }
    
    public DomeData reloadConfig(){
        init();
        return data;
    }
    
    public static DomeData readNewConfig(String file){
        DomeFileManager.file = file;
        init();
        return data;
    }
    
    public static DomeData getData(){
        return data;
    }
    
    private static void init(){
        data = new DomeData();
        
        try {
            String path = plugin.getDataFolder().getPath() + "/" + DomeFileManager.file;
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
            case "material":
                readMaterial(value);
                break;
            default:
                throw new Exception("[ERROR] There was some problem reading dome.cfg file.");
        }
    }
    
    private static void readCenter(String line){
        try{
            String[] center = line.split(":");
            DomeFileManager.data.setCenterX(Integer.parseInt(center[0]));
            DomeFileManager.data.setCenterY(Integer.parseInt(center[1]));
            DomeFileManager.data.setCenterZ(Integer.parseInt(center[2]));
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    
    private static void readMaterial(String line){
        DomeFileManager.data.setMaterial(Material.getMaterial(line));
    }
    
    private static void readRadius(String line){
        DomeFileManager.data.setRadius(readInt(line));
    }
    
    private static int readInt(String line){
        return Integer.parseInt(line);
    }
}
