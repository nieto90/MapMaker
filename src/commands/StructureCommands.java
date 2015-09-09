package commands;

import components.CylinderData;
import components.StructureData;
import managers.StructureFileManager;
import java.util.Arrays;
import builders.CylinderBuilder;
import builders.DomeBuilder;
import mapmaker.MapMaker;
import builders.StructureBuilder;
import components.DomeData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.CustomFunctions;

/**
 *
 * @author nieto
 */
public class StructureCommands implements CommandExecutor {
    private MapMaker plugin;
    
    public StructureCommands(MapMaker instance){
        this.plugin = instance;
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean handled;
        
        if(sender instanceof Player && ((Player)sender).isOp()){
            if(label.equals("mm") || label.equals("mapmaker")){
                if(args.length > 0){
                    switch (args[0]) {
                        case "load":
                            if(args.length == 2){
                                String file = args[1] + ".st";
                                StructureFileManager.loadStructureFromFile(file);
                                StructureData sd = StructureFileManager.getData();
                                if(sd != null){
                                    Location loc = plugin.getServer().getPlayer(sender.getName()).getLocation();
                                    if(executeLoadStructure(sd,loc))
                                        sender.sendMessage("STRUCTURE LOADED!!!!!");
                                    else
                                        sender.sendMessage("FAIL");
                                } else {
                                    sender.sendMessage("Can't load this structure. File error.");
                                }
                                handled = true;
                            } else {
                                handled = false;
                            }
                            break;
                        case "save":
                            if(args.length == 4){
                                Location loc = plugin.getServer().getPlayer(sender.getName()).getLocation();
                                int radius;
                                int height;
                                String file = args[3] + ".st";

                                try{
                                    height = Integer.parseInt(args[1]);
                                    radius = Integer.parseInt(args[2]);
                                    if(radius > 0 && height > 0){
                                        if(executeSaveStructure(loc,height,radius,file))
                                            sender.sendMessage("STRUCTURE SAVED!!!!!");
                                        else
                                            sender.sendMessage("FAIL");
                                    } else {
                                        sender.sendMessage("Radius and height must be positive integers.");
                                    }
                                } catch (NumberFormatException e){
                                    sender.sendMessage("Radius and height must be numbers.");
                                }

                                handled = true;
                            } else {
                                handled = false;
                            }
                            break;
                        case "cylinder":
                            if(args.length >= 3 && args.length <= 4){
                                Location loc = plugin.getServer().getPlayer(sender.getName()).getLocation();
                                int radius;
                                int height;
                                Material m = null;
                                try{
                                    height = Integer.parseInt(args[1]);
                                    radius = Integer.parseInt(args[2]);
                                    if(args.length == 4)
                                        m = Material.getMaterial(args[3]);
                                    if(executeCylinder(loc.getBlockX(), loc.getBlockZ(), height, radius, m)){
                                        sender.sendMessage("Cylinder done successfully!");
                                    } else {
                                        sender.sendMessage("Radius and height must be positive integers.");
                                    }
                                } catch (NumberFormatException e){
                                    sender.sendMessage("Radius and height must be numbers.");
                                }
                                handled = true;
                            } else {
                                handled = false;
                            }
                            break;
                        case "dome":
                            if(args.length >= 2 && args.length <= 3){
                                Location loc = plugin.getServer().getPlayer(sender.getName()).getLocation();
                                int radius;
                                Material m = null;
                                try{
                                    radius = Integer.parseInt(args[1]);
                                    if(args.length == 3)
                                        m = Material.getMaterial(args[2]);
                                    if(executeDome(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), radius, m)){
                                        sender.sendMessage("Dome done successfully!");
                                    } else {
                                        sender.sendMessage("Radius must be a positive integer.");
                                    }
                                } catch (NumberFormatException e){
                                    sender.sendMessage("Radius must be a number.");
                                }
                                handled = true;
                            } else {
                                handled = false;
                            }
                            break;
                        case "spawn":
                            Location l = ((Player)sender).getLocation();
                            System.out.println(args[1] + " = '" + CustomFunctions.round(l.getX(), 1) + "," 
                                    + CustomFunctions.round(l.getY(), 1) + "," + CustomFunctions.round(l.getZ(), 1) + "," 
                                    + CustomFunctions.round(l.getYaw(), 1) + "'");
                            handled = true;
                            break;
                        case "chests":
                            if(args.length == 2){
                                Location loc = ((Player)sender).getLocation();
                                int x = loc.getBlockX();
                                int z = loc.getBlockZ();
                                int r = Integer.parseInt(args[1]);
                                World w = plugin.getServer().getWorld("world");
                                int c = 1;
                                for(int i = x-r; i < x + r; ++i){
                                    for(int j = 0; j < 256; ++j){
                                        for(int k = z - r; k < z + r; ++k){
                                            if(w.getBlockAt(i,j,k).getType() == Material.CHEST ||
                                                    w.getBlockAt(i,j,k).getType() == Material.TRAPPED_CHEST){
                                                System.out.println(String.valueOf(c) + " = " 
                                                        + String.valueOf(i) + ".0:" 
                                                        + String.valueOf(j) + ".0:" 
                                                        + String.valueOf(k) + ".0");
                                                ++c;
                                            }
                                        }
                                    }
                                }
                            }
                            handled = true;
                            break;
                        case "?":
                        case "help":
                            if(args.length == 1 || args.length == 2 && args[1].equals("1")){
                                sender.sendMessage((String[]) Arrays.asList(
                                        "---- Help MapMaker - Page 1/2 ----",
                                        " /mm load <file> :",
                                        "     Build the structure saved in this file.",
                                        " /mm save <height> <radius> <file> :",
                                        "     Saves all blocks in a radius around you",
                                        "     and from your feet to this height.",
                                        "     Creates or overwrites the file.",
                                        " /mm cylinder <height> <radius> [material] :",
                                        "     Creates a cylinder over a radius around you",
                                        "     and from y = 0 to this height.",
                                        "     If no Material is provided or incorrect Material,",
                                        "     cylinder will be made of GLASS.").toArray());
                            } else if (args.length == 2){
                                switch(args[1]){
                                    case "2":
                                        sender.sendMessage((String[]) Arrays.asList(
                                            "---- Help MapMaker - Page 2/2 ----",
                                            " /mm dome <radius> [material] :",
                                            "     Creates a dome over a radius around you",
                                            "     If no Material is provided or incorrect Material,",
                                            "     dome will be made of GLASS.",
                                            " /mm spawn <key> :",
                                            "     Logs current location with format:",
                                            "     key = 'X.x, Y.y, Z.z, YAW,yaw'",
                                            " /mm chests <radius> :",
                                            "     Checks all simple chests located",
                                            "     in a radius around you and from heigh 0 to 256.",
                                            "     Logs every chest location with format:",
                                            "     index = X.0:Y.0:Z.0").toArray());
                                        break;
                                    default:
                                        
                                }
                            }
                            handled = true;
                            break;
                        default:
                            handled = false;
                            break;
                    }
                } else {
                    handled = false;
                }
            } else {
                handled = false;
            }
        } else {
            handled = true;
        }
        
        return handled;
    }

    private boolean executeCylinder(int x, int z, int h, int r, Material m){
        CylinderData data;
        if(r < 0 || h < 0)
            return false;
        if(m != null)
            data = new CylinderData(x,z,h,r,m);
        else
            data = new CylinderData(x,z,h,r);
        CylinderBuilder.buildCylinder(data);
        return true;
    }
    
    
    private boolean executeDome(int x, int y, int z, int r, Material m) {
        DomeData data;
        if(r < 0)
            return false;
        if(m != null)
            data = new DomeData(x,y,z,r,m);
        else
            data = new DomeData(x,y,z,r);
        DomeBuilder.buildDome(data);
        return true;
    }
    
    private boolean executeLoadStructure(StructureData d, Location c){
        try{
            StructureBuilder.buildStructure(d, c);
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean executeSaveStructure(Location c, int h, int r, String f){
        try{
            StructureData data = StructureBuilder.createStructureData(c,r,h,r);
            StructureFileManager.saveStructureToFile(data, f);
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
