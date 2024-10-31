package com.bubbaj2016.darkmc.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.bubbaj2016.darkmc.BlockHandler;
import com.bubbaj2016.darkmc.DoorHandler;
import com.bubbaj2016.darkmc.flooredLoc;


public class BlockPlaceHandler implements Listener{
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE){
            return;
        }
        if(!DoorHandler.allowedToPlace(new flooredLoc(event.getBlockPlaced().getLocation()), event.getPlayer().getUniqueId().toString())){

            event.setCancelled(true);
            return;
        }
        if (BlockHandler.numOfBlocksFromPlayer(event.getPlayer().getUniqueId().toString()) > 20){
            return;
        }
        BlockHandler.addBlock(event.getPlayer().getUniqueId().toString(), event.getBlockPlaced());

    }
}

