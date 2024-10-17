package com.bubbaj2016.darkmc;

import org.bukkit.plugin.java.JavaPlugin;

import com.bubbaj2016.darkmc.commands.WandCommand;
import com.bubbaj2016.darkmc.listeners.BlockBreakHandler;
import com.bubbaj2016.darkmc.listeners.RightClickHandler;




public class Plugin extends JavaPlugin {
    public static JavaPlugin plugin;
    
    @Override
    public void onEnable() {
        getLogger().info("DarkMC Starting");
        getServer().getPluginManager().registerEvents(new BlockBreakHandler(), this);
        getServer().getPluginManager().registerEvents(new RightClickHandler(), this);

        getCommand("WandCommand").setExecutor(new WandCommand());
        ItemManager.init();

    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin is Disabling!");
    }
}
