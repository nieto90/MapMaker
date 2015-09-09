/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.util.HashMap;
import mapmaker.MapMaker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author nieto
 */
public class CommandManager {
    private MapMaker plugin;
    private HashMap<String, CommandExecutor> commands;

    public CommandManager(MapMaker instance) {
        this.plugin = instance;
        commands = new HashMap<>();
    }
    
    public void disable() {
        commands.clear();
        commands = null;
    }

    public void addCommand(String label, CommandExecutor executor) {
        commands.put(label, executor);
    }

    public boolean dispatch(CommandSender sender, Command command, String label, String[] args) {
        if (!commands.containsKey(label)) {
            return false;
        }

        boolean handled;

        CommandExecutor ce = commands.get(label);
        handled = ce.onCommand(sender, command, label, args);

        return handled;
    }
}
