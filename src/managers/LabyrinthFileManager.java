/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import components.LabyrinthData;
import mapmaker.MapMaker;
import utils.CustomFile;

/**
 *
 * @author nieto
 */
public class LabyrinthFileManager {
    private static MapMaker plugin;
    private static String file;
    private static LabyrinthData data;
    
    public LabyrinthFileManager(MapMaker instance){
        plugin = instance;
        file = "labyrinth.cfg";
        init();
    }
    
    public LabyrinthData reloadConfig(){
        init();
        return data;
    }
    
    public static LabyrinthData readNewConfig(String file){
        LabyrinthFileManager.file = file;
        init();
        return data;
    }
    
    public static LabyrinthData getData(){
        return data;
    }
    
    private static void init(){
        data = new LabyrinthData();
        
        try {
            String path = plugin.getDataFolder().getPath() + "/" + LabyrinthFileManager.file;
            for(String s : CustomFile.getLinesFromFile(path)){
                String[] line = s.split(" = ");
                readerSelector(line[0], line[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("2" + e.getMessage());
        }
        
        data.reload();
    }
    
    private static void readerSelector(String key, String value) throws Exception{
        switch(key){
            case "center":
                readCenter(value);
                break;
            case "radius":
                readRadius(value);
                break;
            default:
                throw new Exception("[ERROR] There was some problem reading labyrinth.cfg file.");
        }
    }
    
    private static void readCenter(String line){
        try{
            String[] center = line.split(":");
            LabyrinthFileManager.data.setCenter(Integer.parseInt(center[0]),
                    Integer.parseInt(center[1]),
                    Integer.parseInt(center[2]));
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    
    private static void readRadius(String line){
        LabyrinthFileManager.data.setRadius(readInt(line));
    }
    
    private static int readInt(String line){
        return Integer.parseInt(line);
    }
}
