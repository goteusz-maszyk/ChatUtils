package org.gotitim.chatutils.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.gotitim.chatutils.Main;

import java.util.ArrayList;
import java.util.List;

public class CCMCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Main plugin = Main.plugin;
        plugin.reloadConfig();
        sender.sendMessage("Reloaded config.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase("ccm") && args.length == 0) {
            if(!(sender instanceof Player)) return null;

            List<String> options = new ArrayList<String>();
            options.add("reload");

            return options;
        }

        return null;
    }
}
