package com.bubbaj2016.darkmc.listeners;

import org.bukkit.event.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import com.bubbaj2016.darkmc.MoneyHandler;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event){
        if (MoneyHandler.loadPlayerBalance(event.getPlayer().getUniqueId().toString()) == -1){
            MoneyHandler.registerPlayer(event.getPlayer().getUniqueId().toString());
        }
    }

}
