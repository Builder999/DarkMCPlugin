package com.bubbaj2016.darkmc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.bubbaj2016.darkmc.DoorHandler;
import com.bubbaj2016.darkmc.DoorLoc;


public class BlockPlaceHandler implements Listener{
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE){
            return;
        }
        if(!DoorHandler.allowedToPlace(new DoorLoc(event.getBlockAgainst().getLocation()), event.getPlayer().getUniqueId().toString())){
            event.setCancelled(true);
        }
    }
}

