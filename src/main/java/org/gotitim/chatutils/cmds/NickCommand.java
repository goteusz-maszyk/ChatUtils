package org.gotitim.chatutils.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.gotitim.chatutils.Main;

public class NickCommand extends BukkitCommand {
    Main plugin;
    public NickCommand(String name, Main m) {
        super(name);
        plugin = m;

        this.description = "Change your displayed nick on server";
        this.usageMessage = "/nick <nick>";
        this.setPermission("ccm.nick");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        if(!sender.hasPermission("ccm.nick")) { sender.sendMessage(ChatColor.RED + "You don't have permission to use /nick!"); }
        if (args == null || args.length == 0) { p.sendMessage("Please provide nick argument!"); return false; }

        assert args != null;
        String nick = args[0];
        p.setDisplayName(nick);
        p.setPlayerListName(nick);
        p.sendMessage("Successfully set your nick to " + nick + "!");

        return true;
    }
}
