package com.bubbaj2016.darkmc.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.bubbaj2016.darkmc.BlockHandler;
import com.bubbaj2016.darkmc.DoorHandler;
import com.bubbaj2016.darkmc.DoorLoc;


public class BlockPlaceHandler implements Listener{
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE){
            return;
        }
        if(!DoorHandler.allowedToPlace(new DoorLoc(event.getBlockPlaced().getLocation()), event.getPlayer().getUniqueId().toString())){

            event.setCancelled(true);
            return;
        }
        BlockHandler.addBlock(event.getPlayer().getUniqueId().toString(), event.getBlockPlaced());

    }
}

