package mapmaker;

import builders.CylinderBuilder;
import builders.DomeBuilder;
import builders.LabyrinthBuilder;
import builders.StructureBuilder;
import managers.CommandManager;
import commands.StructureCommands;
import components.CylinderData;
import org.bukkit.plugin.java.JavaPlugin;
import managers.CylinderFileManager;
import components.LabyrinthData;
import managers.DomeFileManager;
import managers.LabyrinthFileManager;
import managers.StructureFileManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import utils.CustomFile;

/**
 *
 * @author nieto90
 */
public class MapMaker extends JavaPlugin {

    private static MapMaker mapMaker;
    private CylinderFileManager cylinderReader;
    private LabyrinthFileManager labyrinthReader;
    private StructureFileManager structureFileManager;
    private DomeFileManager domeFileManager;
    private CylinderBuilder cylinderMaker;
    private StructureBuilder structureMaker;
    private DomeBuilder domeMaker;
    private PluginConfig pluginConfig;
    private CommandManager commandManager;
    private LabyrinthBuilder labyrinthManager;
    private StructureCommands structureCmd;
    
    @Override
    public void onEnable() {
        mapMaker = this;
        cylinderReader = new CylinderFileManager(this);
        labyrinthReader = new LabyrinthFileManager(this);
        domeFileManager = new DomeFileManager(this);
        cylinderMaker = new CylinderBuilder(this);
        structureMaker = new StructureBuilder(this);
        domeMaker = new DomeBuilder(this);
        pluginConfig = new PluginConfig(this);
        commandManager = new CommandManager(this);
        labyrinthManager = new LabyrinthBuilder();
        structureFileManager = new StructureFileManager(this);
        
        // Add commands here
        structureCmd = new StructureCommands(this);
        commandManager.addCommand("mapmaker", structureCmd);
        commandManager.addCommand("mm", structureCmd);
        
        init();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandManager.dispatch(sender, command, label, args);
    }
    
    private void init(){
        if(pluginConfig.isAutoBuildEnabled()){
            for(String file : pluginConfig.getFiles()){
                build(file);
            }
        }
    }
    
    private void build(String file){
        Location loc = null;
        switch(file){
            case "cylinder":
                break;
            case "labyrinth":
                LabyrinthFileManager.readNewConfig(file + ".cfg");
                LabyrinthData data = LabyrinthFileManager.getData();
                loc = new Location(getServer().getWorld("world"),
                        data.getCenterX(),data.getCenterY(),data.getCenterZ());
                StructureBuilder.buildLabyrinth(loc, data);
                break;
            case "lobby":
                String path = getDataFolder().getPath() + "/" + file + ".cfg";
                for(String s : CustomFile.getLinesFromFile(path)){
                    String[] ss = s.split(" = ");
                    if(ss[0].equals("center")){
                        String[] l = ss[1].split(":");
                        loc = new Location(getServer().getWorld("world"),
                            Integer.parseInt(l[0]),Integer.parseInt(l[1]),
                                Integer.parseInt(l[2]));
                    }else if(ss[0].equals("file")){
                        StructureFileManager.loadStructureFromFile(ss[1]+".st");
                    }
                }
                StructureBuilder.buildStructure(StructureFileManager.getData(), loc);
                break;
            default:
                break;
        }
    }
    
}
