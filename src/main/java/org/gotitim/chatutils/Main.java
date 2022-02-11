package org.gotitim.chatutils;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.gotitim.chatutils.cmds.*;
import org.gotitim.chatutils.events.*;

import java.lang.reflect.Field;

public final class Main extends JavaPlugin {
    public static Main plugin;
    public static boolean chatLocked = false;
    @Override
    public void onEnable() {
        plugin = this;
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerJoin(this), this);
        manager.registerEvents(new PlayerLeave(this), this);
        manager.registerEvents(new ChatMessage(this), this);
        getCommand("cu").setExecutor(new CUCommand());
        getCommand("msg").setExecutor(new MsgCommand());

        saveDefaultConfig();

        try {
            Field commandMapField = manager.getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            CommandMap commandMap = (CommandMap) commandMapField.get(getServer().getPluginManager());
            if(getConfig().getBoolean("nick_command")) {
                commandMap.register("nick", new NickCommand("nick", this));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Class<?> getNMSClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
    }

    public static void setChatLocked(boolean value) {
        chatLocked = value;
    }
}
