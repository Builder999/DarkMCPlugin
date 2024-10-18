package com.bubbaj2016.darkmc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Door;

import com.bubbaj2016.darkmc.DoorHandler;
import com.bubbaj2016.darkmc.DoorLoc;
import com.bubbaj2016.darkmc.ItemManager;

import net.md_5.bungee.api.chat.hover.content.Text;


public class ShiftRightClickHandler implements Listener{
    @EventHandler
    public void onShiftRightBreak(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().isSneaking()){
            if (event.getItem() == null){
                if (event.getClickedBlock().getType() == Material.OAK_DOOR){
                    DoorLoc loc = new DoorLoc(event.getClickedBlock().getLocation());
                    if (DoorHandler.doorAdded(loc)){
                        if (DoorHandler.getDoorOwner(loc).equals(event.getPlayer().getUniqueId().toString())){
                            DoorHandler.setOwner(loc, "");
                        }
                        else {
                            DoorHandler.setOwner(loc, event.getPlayer().getUniqueId().toString());
                        }
                        event.setCancelled(true);
                    }
                }
            }
        }
        
    }
}
