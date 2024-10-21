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

import com.bubbaj2016.darkmc.BlockHandler;

import net.md_5.bungee.api.chat.hover.content.Text;


public class BlockBreakHandler implements Listener{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE){
            if (!BlockHandler.ownsBlock(event.getPlayer().getUniqueId().toString(), event.getBlock())){
                event.setCancelled(true);
            }
            else {
                BlockHandler.removeBlock(event.getPlayer().getUniqueId().toString(), event.getBlock());
            }
        }
    }
}
