package com.bubbaj2016.darkmc.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import com.bubbaj2016.darkmc.Plugin;
import com.bubbaj2016.darkmc.Utilities;

public class ReloadListener implements Listener{
    @EventHandler
    public void onReload(ServerLoadEvent event){
        for (Player player : Plugin.getPlugin().getServer().getOnlinePlayers()){
           Utilities.playerSetup(player); 
        }
    }
}
