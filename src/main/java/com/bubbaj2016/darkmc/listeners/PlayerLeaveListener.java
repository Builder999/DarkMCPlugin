package com.bubbaj2016.darkmc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.bubbaj2016.darkmc.BlockHandler;
import com.bubbaj2016.darkmc.DoorHandler;

public class PlayerLeaveListener implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        BlockHandler.removePlayerBlocks(event.getPlayer().getUniqueId().toString());
        DoorHandler.removeOwnerFromDoors(event.getPlayer().getUniqueId().toString());
    }
}
