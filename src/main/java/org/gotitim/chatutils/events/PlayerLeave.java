package org.gotitim.chatutils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.gotitim.chatutils.Main;

import java.util.Objects;

public class PlayerLeave implements Listener {
    Main plugin;
    public PlayerLeave(Main m) {
        plugin = m;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerQuitEvent e) {
        e.setQuitMessage(Objects.requireNonNull(plugin.getConfig().getString("leave_message"))
                .replace("&", "§")
                .replace("%player%", e.getPlayer().getDisplayName()));
    }
}
