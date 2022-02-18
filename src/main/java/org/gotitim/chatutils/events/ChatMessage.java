package org.gotitim.chatutils.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.gotitim.chatutils.Main;

import java.util.List;
import java.util.Objects;

public class ChatMessage implements Listener {
    Main plugin;
    public ChatMessage(Main m) {
        plugin = m;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player p = e.getPlayer();

        if(!p.hasPermission("cu.bypass_banword")) {
            List<String> banned_words = plugin.getConfig().getStringList("banned_words");

            for (String word : banned_words) {
                if (message.contains(word)) e.setCancelled(true);
                return;
            }
        }
        if(!p.hasPermission("cu.bypass_chatlock") && Main.chatLocked) {
            e.setCancelled(true);
            return;
        }

        e.setFormat(Objects.requireNonNull(plugin.getConfig().getString("chat_message"))
                .replace("&", "§")
                .replace("%player%", p.getDisplayName())
                .replace("%message%", message.replace("&", "§"))
                .replace("%world%", p.getWorld().getName())
                .replace("%dimension%", p.getWorld().getEnvironment().name())
        );
    }
}
