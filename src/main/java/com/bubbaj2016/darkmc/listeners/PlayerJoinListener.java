package com.bubbaj2016.darkmc.listeners;

import org.bukkit.event.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import com.bubbaj2016.darkmc.Utilities;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event){
        Utilities.playerSetup(event.getPlayer());
    }

}
