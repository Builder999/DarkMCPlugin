package com.bubbaj2016.darkmc;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import com.bubbaj2016.darkmc.commands.JobCommand;
import com.bubbaj2016.darkmc.commands.KeyCommand;
import com.bubbaj2016.darkmc.commands.LocWandCommand;
import com.bubbaj2016.darkmc.commands.Money;
import com.bubbaj2016.darkmc.commands.RemoveBlocksCommand;
import com.bubbaj2016.darkmc.commands.ShopCommand;
import com.bubbaj2016.darkmc.listeners.BlockBreakHandler;
import com.bubbaj2016.darkmc.listeners.BlockPlaceHandler;
import com.bubbaj2016.darkmc.listeners.InventoryEventHandler;
import com.bubbaj2016.darkmc.listeners.PlayerJoinListener;
import com.bubbaj2016.darkmc.listeners.PlayerLeaveListener;
import com.bubbaj2016.darkmc.listeners.ReloadListener;
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
        getServer().getPluginManager().registerEvents(new InventoryEventHandler(), this);
        getServer().getPluginManager().registerEvents(new ReloadListener(), this);


        getCommand("KeyCommand").setExecutor(new KeyCommand());
        getCommand("LocWandCommand").setExecutor(new LocWandCommand());
        getCommand("RemoveBlocksCommand").setExecutor(new RemoveBlocksCommand());
        getCommand("money").setExecutor(new Money());
        getCommand("getJobs").setExecutor(new JobCommand());
        getCommand("shop").setExecutor(new ShopCommand());

        plugin = this;

        try {
            InventoryShopHandler.createInventories();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DoorHandler.removeTextEntities();
        DoorHandler.loadDoorsFromDatabase();
        MoneyHandler.loadValues();
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
        try {
            MoneyHandler.saveMoney();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
