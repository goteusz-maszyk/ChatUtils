package org.gotitim.chatutils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.gotitim.chatutils.Main;

import java.util.Objects;

public class PlayerJoin implements Listener {
    Main plugin;
    public PlayerJoin(Main m) {
        plugin = m;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(Objects.requireNonNull(plugin.getConfig().getString("join_message"))
                .replace("&", "§")
                .replace("%player%", e.getPlayer().getDisplayName()));
    }
}
