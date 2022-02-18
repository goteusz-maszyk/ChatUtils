package org.gotitim.chatutils.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CUCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof Player)) return null;

        List<String> options = new ArrayList<String>();
        options.add("clear");
        options.add("lock");
        options.add("unlock");

        return options;
    }
}
