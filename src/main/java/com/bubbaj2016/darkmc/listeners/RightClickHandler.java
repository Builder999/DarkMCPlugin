package com.bubbaj2016.darkmc.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server.Spigot;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Bisected;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.bubbaj2016.darkmc.DoorHandler;
import com.bubbaj2016.darkmc.DoorLoc;
import com.bubbaj2016.darkmc.ItemManager;
import com.bubbaj2016.darkmc.Plugin;
import com.bubbaj2016.darkmc.Utilities;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;



public class RightClickHandler implements Listener{
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (event.getItem() != null){
                 if (event.getItem().getItemMeta().equals(ItemManager.key.getItemMeta())){
                    if (event.getClickedBlock().getType() == Material.OAK_DOOR){
                        Location loc = event.getClickedBlock().getLocation();
                        Bisected bisect = (Bisected) event.getClickedBlock().getBlockData();
                        if (bisect.getHalf() == Half.TOP){
                            loc = loc.subtract(0, 1, 0);
                        }
                        DoorLoc doorLoc = new DoorLoc(loc);
                        if (DoorHandler.doorAdded(doorLoc)){
                            // if(DoorHandler.getDoorOwner(doorLoc).equals("")){
                            //     DoorHandler.setOwner(doorLoc, event.getPlayer().getUniqueId().toString());
                            // }
                             if (DoorHandler.getDoorOwner(doorLoc).equals(event.getPlayer().getUniqueId().toString())){
                                DoorHandler.toggleLock(doorLoc);
                                if (DoorHandler.getLocked(doorLoc)){
                                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Locked"));
                                }
                                else {
                                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Unlocked"));
                                }
                                event.setCancelled(true);
                            }
                        }
                    }
                }

                else if (event.getItem().getItemMeta().getDisplayName().equals(ItemManager.locWand.getItemMeta().getDisplayName())){
                    ItemMeta meta = event.getItem().getItemMeta();

                    if (meta.getPersistentDataContainer().has(new NamespacedKey(Plugin.getPlugin(), "x2"))){
                        if (event.getClickedBlock().getType() == Material.OAK_DOOR){
                            event.setCancelled(true);
                            Bukkit.getServer().broadcastMessage("Assigning Door");
                            Location tempDoorLoc = event.getClickedBlock().getLocation();
                            Bisected bisect = (Bisected) event.getClickedBlock().getBlockData();
                            if (bisect.getHalf() == Half.TOP){
                                tempDoorLoc = tempDoorLoc.subtract(0, 1, 0);
                            }
                            addDoor(event.getPlayer(), event.getClickedBlock());
                            DoorLoc doorLoc = new DoorLoc(tempDoorLoc);
                            PersistentDataContainer con = event.getItem().getItemMeta().getPersistentDataContainer();
                            DoorLoc loc1 = new DoorLoc(event.getPlayer().getWorld(), con.get(new NamespacedKey(Plugin.getPlugin(), "x1"), PersistentDataType.INTEGER), con.get(new NamespacedKey(Plugin.getPlugin(), "y1"), PersistentDataType.INTEGER), con.get(new NamespacedKey(Plugin.getPlugin(), "z1"), PersistentDataType.INTEGER));
                            DoorLoc loc2 = new DoorLoc(event.getPlayer().getWorld(), con.get(new NamespacedKey(Plugin.getPlugin(), "x2"), PersistentDataType.INTEGER), con.get(new NamespacedKey(Plugin.getPlugin(), "y2"), PersistentDataType.INTEGER), con.get(new NamespacedKey(Plugin.getPlugin(), "z2"), PersistentDataType.INTEGER));
                            DoorHandler.setArea(loc1, loc2, doorLoc);
                            Bukkit.getServer().broadcastMessage(DoorHandler.getDoorByLoc(doorLoc).printLocs());
                        }
                        meta.getPersistentDataContainer().remove(new NamespacedKey(Plugin.getPlugin(), "x1"));
                        meta.getPersistentDataContainer().remove(new NamespacedKey(Plugin.getPlugin(), "x2"));
                    }
                    else {
                        if (!meta.getPersistentDataContainer().has(new NamespacedKey(Plugin.getPlugin(), "x1"))){ 
                            meta.getPersistentDataContainer().set(new NamespacedKey(Plugin.getPlugin(), "x1"), PersistentDataType.INTEGER, event.getClickedBlock().getLocation().getBlockX());
                            meta.getPersistentDataContainer().set(new NamespacedKey(Plugin.getPlugin(), "y1"), PersistentDataType.INTEGER, event.getClickedBlock().getLocation().getBlockY());
                            meta.getPersistentDataContainer().set(new NamespacedKey(Plugin.getPlugin(), "z1"), PersistentDataType.INTEGER, event.getClickedBlock().getLocation().getBlockZ());
                            Bukkit.getServer().broadcastMessage("Assigning Loc 1");

                        }
                        else if (!meta.getPersistentDataContainer().has(new NamespacedKey(Plugin.getPlugin(), "x2"))){
                            meta.getPersistentDataContainer().set(new NamespacedKey(Plugin.getPlugin(), "x2"), PersistentDataType.INTEGER, event.getClickedBlock().getLocation().getBlockX());
                            meta.getPersistentDataContainer().set(new NamespacedKey(Plugin.getPlugin(), "y2"), PersistentDataType.INTEGER, event.getClickedBlock().getLocation().getBlockY());
                            meta.getPersistentDataContainer().set(new NamespacedKey(Plugin.getPlugin(), "z2"), PersistentDataType.INTEGER, event.getClickedBlock().getLocation().getBlockZ());
                            Bukkit.getServer().broadcastMessage("Assigning Loc 2");
                        }
                    }
                    event.getItem().setItemMeta(meta);
                }
            }


            if (event.getItem() == null){
                if (event.getClickedBlock().getType() == Material.OAK_DOOR){
                    Bisected bisect = (Bisected) event.getClickedBlock().getBlockData();
                    Location loc = event.getClickedBlock().getLocation();
                    if (bisect.getHalf() == Half.TOP){
                        loc = loc.subtract(0, 1, 0);
                    }
                    DoorLoc doorLocation = new DoorLoc(loc);
                    if(DoorHandler.doorAdded(doorLocation)){
                        if (DoorHandler.getLocked(doorLocation)){
                            event.setCancelled(true);
                        }
                    }
                }
            }            
        }
        
        
    }

    private void addDoor(Player player, Block clickedBlock){
        Door door = (Door) clickedBlock.getBlockData();
        Location loc = clickedBlock.getLocation();
        if (door.getHalf() == Half.TOP){
            loc = loc.subtract(0, 1, 0);
        }
        //loc = Utilities.stripRotation(loc);
        DoorLoc doorLoc = new DoorLoc(player.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        Directional direction = (Directional) clickedBlock.getBlockData();

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
        TextDisplay display = player.getWorld().spawn(loc, TextDisplay.class);
        display.setText("Owned By: ");
        display.setVisibleByDefault(true);
        display.setBillboard(Billboard.CENTER);
        DoorHandler.addDoor(doorLoc, display.getUniqueId().toString());
    }
}
