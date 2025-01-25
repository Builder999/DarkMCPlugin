package com.bubbaj2016.darkmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.bubbaj2016.darkmc.commands.JobCommand;
import com.bubbaj2016.darkmc.commands.KeyCommand;
import com.bubbaj2016.darkmc.commands.LocWandCommand;
import com.bubbaj2016.darkmc.commands.Money;
import com.bubbaj2016.darkmc.commands.RemoveBlocksCommand;
import com.bubbaj2016.darkmc.listeners.BlockBreakHandler;
import com.bubbaj2016.darkmc.listeners.BlockPlaceHandler;
import com.bubbaj2016.darkmc.listeners.PlayerJoinListener;
import com.bubbaj2016.darkmc.listeners.PlayerLeaveListener;
import com.bubbaj2016.darkmc.listeners.RightClickHandler;
import com.bubbaj2016.darkmc.listeners.ShiftRightClickHandler;




public class Plugin extends JavaPlugin {
    public static JavaPlugin plugin;
    public static String url = "jdbc:sqlite:darkmc.db";
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
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);


        getCommand("KeyCommand").setExecutor(new KeyCommand());
        getCommand("LocWandCommand").setExecutor(new LocWandCommand());
        getCommand("RemoveBlocksCommand").setExecutor(new RemoveBlocksCommand());
        getCommand("money").setExecutor(new Money());
        getCommand("getJobs").setExecutor(new JobCommand());


        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Bukkit.getServer().broadcastMessage("Loaded DB");
                String sql = "CREATE TABLE IF NOT EXISTS money (playerUUID TEXT NOT NULL PRIMARY KEY, money REAL);";
                String sql2 = "CREATE TABLE IF NOT EXISTS doors (currentLoc TEXT NOT NULL PRIMARY KEY, loc1 TEXT, loc2 TEXT, displayLoc TEXT, worldName TEXT);";

                Statement stm = conn.createStatement();
                stm.execute(sql);
                Statement stm2 = conn.createStatement();
                stm2.executeQuery(sql2);

            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        plugin = this;
        DoorHandler.loadDoorsFromDatabase();

        this.saveDefaultConfig();

        try {
            JobHandler.addJobs();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
