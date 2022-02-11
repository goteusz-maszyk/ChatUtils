package org.gotitim.chatutils.cmds;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.gotitim.chatutils.Main;

import java.util.ArrayList;
import java.util.List;

public class CUCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args == null || args.length == 0) {
            Main plugin = Main.plugin;
            plugin.reloadConfig();
            sender.sendMessage("Reloaded config.");
            return true;
        } else {
            switch (args[0]) {
                case ("clear"):
                    Bukkit.broadcastMessage(StringUtils.repeat("\n", 200));
                    return true;
                case ("lock"):
                    Main.setChatLocked(true);
                    return true;
                case ("unlock"):
                    Main.setChatLocked(false);
                    return true;
            }
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase("cu") && args.length == 0) {
            if(!(sender instanceof Player)) return null;

            List<String> options = new ArrayList<String>();
            options.add("clear");
            options.add("lock");
            options.add("unlock");

            return options;
        }

        return null;
    }
}
