package com.bubbaj2016.darkmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import com.bubbaj2016.darkmc.commands.KeyCommand;
import com.bubbaj2016.darkmc.commands.LocWandCommand;
import com.bubbaj2016.darkmc.commands.RemoveBlocksCommand;
import com.bubbaj2016.darkmc.listeners.BlockBreakHandler;
import com.bubbaj2016.darkmc.listeners.BlockPlaceHandler;
import com.bubbaj2016.darkmc.listeners.PlayerLeaveListener;
import com.bubbaj2016.darkmc.listeners.RightClickHandler;
import com.bubbaj2016.darkmc.listeners.ShiftRightClickHandler;




public class Plugin extends JavaPlugin {
    public static JavaPlugin plugin;
    public static String url = "jdbc:sqlite:doors.db";
    @Override
    public void onEnable() {
        getLogger().info("DarkMC Starting");
        ItemManager.init();
        //getServer().getPluginManager().registerEvents(new BlockBreakHandler(), this);
        getServer().getPluginManager().registerEvents(new RightClickHandler(), this);
        getServer().getPluginManager().registerEvents(new ShiftRightClickHandler(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakHandler(), this);

        getCommand("KeyCommand").setExecutor(new KeyCommand());
        getCommand("LocWandCommand").setExecutor(new LocWandCommand());
        getCommand("RemoveBlocksCommand").setExecutor(new RemoveBlocksCommand());
        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Bukkit.getServer().broadcastMessage("Loaded DB");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        plugin = this;
        DoorHandler.loadDoorsFromDatabase();

    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin is Disabling!");
        DoorHandler.removeTextEntities();

    }
    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
