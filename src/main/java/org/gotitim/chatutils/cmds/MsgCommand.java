package org.gotitim.chatutils.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.gotitim.chatutils.Main;

import java.util.Arrays;
import java.util.Objects;

public class MsgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args == null || args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please provide valid player name!");
            return false;
        }
        String displayName;
        if(sender instanceof Player) {
            displayName = ((Player) sender).getDisplayName();
        } else {
            displayName = Main.plugin.getConfig().getString("console_nick");
        }
        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            sender.sendMessage(ChatColor.RED + "Please provide valid player name!");
            return false;
        }
        String parsedMessage = Objects.requireNonNull(Main.plugin.getConfig().getString("direct_message"))
                .replace("&", "§")
                .replace("%sender%", displayName)
                .replace("%target%", target.getDisplayName())
                .replace("%message%", String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
        target.sendMessage(parsedMessage);
        sender.sendMessage(parsedMessage);

        return true;
    }
}
