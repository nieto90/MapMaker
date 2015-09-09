/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker;

import mapmaker.MapMaker;
import utils.CustomFile;

/**
 *
 * @author nieto
 */
public class PluginConfig {
    private MapMaker plugin;
    private String file;
    private boolean autobuild = false;
    private String[] files;
    
    public PluginConfig(MapMaker instance){
        this.plugin = instance;
        file = "map.cfg";
        init();
    }
    
    public boolean isAutoBuildEnabled(){
        return this.autobuild;
    }
    
    public String[] getFiles(){
        return files;
    }
    
    private void init(){
        try {
            String path = plugin.getDataFolder().getPath() + "/" + this.file;
            for(String s : CustomFile.getLinesFromFile(path)){
                String[] line = s.split(" = ");
                readerSelector(line[0], line[1]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void readerSelector(String key, String value) throws Exception{
        switch(key){
            case "autobuild":
                readAutoBuild(value);
                break;
            case "files":
                readFiles(value);
                break;
            default:
                throw new Exception("[ERROR] There was some problem reading cylinder.cfg file.");
        }
    }
    
    private void readAutoBuild(String line){
        this.autobuild = readBool(line);
    }
    
    private void readFiles(String line){
        this.files = line.split(":");
    }
    
    private boolean readBool(String line){
        return line.equals("true");
    }
    
}
