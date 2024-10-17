package com.bubbaj2016.darkmc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Bisected.Half;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import com.bubbaj2016.darkmc.ItemManager;



public class RightClickHandler implements Listener{
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (event.getItem() != null){
                if (event.getItem().getItemMeta().equals(ItemManager.wand.getItemMeta())){
                    if (event.getClickedBlock().getType() == Material.OAK_DOOR){
                        Player player = event.getPlayer();
                        Door door = (Door) event.getClickedBlock().getBlockData();
                        Location loc = event.getClickedBlock().getLocation();
                        if (door.getHalf() == Half.TOP){
                            loc = loc.subtract(0, 1, 0);
                        }
                        event.setCancelled(true);
                        Directional direction = (Directional) event.getClickedBlock().getBlockData();

                        if (direction.getFacing() == BlockFace.NORTH){
                            loc = loc.add(0, 1, 1.25);
                        }
                        else if (direction.getFacing() == BlockFace.EAST){
                            loc = loc.add(-.5, 1, 0);
                        }
                        else if (direction.getFacing() == BlockFace.SOUTH){
                            loc = loc.add(0, 1, -.25);

                        }     
                        else if (direction.getFacing() == BlockFace.WEST){
                            Bukkit.getServer().getLogger().info(direction.getFacing().toString());
                            loc = loc.add(1.5, 1, 0);
                        }
                        TextDisplay display = event.getPlayer().getWorld().spawn(loc, TextDisplay.class);
                        display.setText("Owned By: " + player.getName());
                        display.setVisibleByDefault(true);
                        display.setBillboard(Billboard.CENTER);
                        DoorHandler.addDoor(loc, display.getUniqueId().toString());
                        DoorHandler.addOwner(display.getUniqueId().toString(), event.getPlayer().getUniqueId().toString());
                        Bukkit.getServer().getLogger().info(DoorHandler.getSignFromDoorLoc(loc));
                    }
                }
            }
            
        }
        
        
    }
}
