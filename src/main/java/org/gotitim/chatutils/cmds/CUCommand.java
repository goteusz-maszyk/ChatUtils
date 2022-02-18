package org.gotitim.chatutils.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.gotitim.chatutils.Main;

public class CUCommand implements CommandExecutor {
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
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        for (int i = 0; i < 200; i++) {
                            player.sendMessage("\n");
                        }
                    }
                    sender.sendMessage("Chat cleared.");
                    return true;
                case ("lock"):
                    Main.setChatLocked(true);
                    sender.sendMessage("Chat locked. Only players with cu.bypass_chatlock permission can send messages now.");
                    return true;
                case ("unlock"):
                    Main.setChatLocked(false);
                    sender.sendMessage("Chat unlocked. Everyone can write again!");
                    return true;
            }
            return true;
        }
    }
}
