package com.bubbaj2016.darkmc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.md_5.bungee.api.chat.hover.content.Text;


public class BlockBreakHandler implements Listener{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        loc = loc.add(0, 2, 0);
        TextDisplay display = event.getPlayer().getWorld().spawn(loc, TextDisplay.class);
        display.setText("Shut The Fuck Up!");
        display.setVisibleByDefault(true);
        display.setBillboard(Billboard.CENTER);
        Bukkit.getServer().getLogger().info("Broke Block");
        
        
    }
}
