package com.bubbaj2016.darkmc.listeners;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Bisected.Half;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import com.bubbaj2016.darkmc.DoorHandler;
import com.bubbaj2016.darkmc.flooredLoc;
import com.bubbaj2016.darkmc.ItemManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;



public class RightClickHandler implements Listener{
    HashMap<String, ArrayList<Location>> locWandMap = new HashMap<>();
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) throws IOException{
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (event.getItem() != null){
                 if (event.getItem().getItemMeta().equals(ItemManager.key.getItemMeta())){
                    if (event.getClickedBlock().getType().toString().contains("DOOR")){
                        Location loc = event.getClickedBlock().getLocation();
                        Bisected bisect = (Bisected) event.getClickedBlock().getBlockData();
                        if (bisect.getHalf() == Half.TOP){
                            loc = loc.subtract(0, 1, 0);
                        }
                        flooredLoc doorLoc = new flooredLoc(loc);
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
                    ArrayList<Location> location = locWandMap.get(event.getPlayer().getUniqueId().toString());
                    String playerID = event.getPlayer().getUniqueId().toString();
                    Block clickedBlock = event.getClickedBlock();
                    if (location == null){
                        location = new ArrayList<Location>();
                        location.add(clickedBlock.getLocation());
                        locWandMap.put(playerID, location);
                        event.getPlayer().sendMessage("Assigning Loc 1: " + location.get(0).toString());
                    }
                    else if (location.size() == 1) {
                        location.add(clickedBlock.getLocation());
                        locWandMap.put(playerID, location);
                        event.getPlayer().sendMessage("Assigning Loc 2: " + location.get(1).toString());
                    }
                    else if (location.size() == 2 && event.getClickedBlock().getType().toString().contains("DOOR")){
                        addDoor(clickedBlock);
                        Location tempDoorLoc = event.getClickedBlock().getLocation();
                        Bisected bisect = (Bisected) event.getClickedBlock().getBlockData();
                        if (bisect.getHalf() == Half.TOP){
                            tempDoorLoc = tempDoorLoc.subtract(0, 1, 0);
                        }
                        DoorHandler.setArea(new flooredLoc(location.get(0)), new flooredLoc(tempDoorLoc), new flooredLoc(tempDoorLoc));
                        DoorHandler.saveDoorToDatabase(DoorHandler.getDoorByLoc(new flooredLoc(tempDoorLoc)));
                        locWandMap.remove(playerID);
                        event.getPlayer().sendMessage("Assigning Door: " + clickedBlock.getLocation().toString());
                        event.setCancelled(true);
                    }
                }
            }


            if (event.getItem() == null){
                if (event.getClickedBlock().getType().toString().contains("DOOR")){
                    Bisected bisect = (Bisected) event.getClickedBlock().getBlockData();
                    Location loc = event.getClickedBlock().getLocation();
                    if (bisect.getHalf() == Half.TOP){
                        loc = loc.subtract(0, 1, 0);
                    }
                    flooredLoc doorLocation = new flooredLoc(loc);
                    if(DoorHandler.doorAdded(doorLocation)){
                        if (DoorHandler.getLocked(doorLocation)){
                            event.setCancelled(true);
                        }
                    }
                }
            }            
        }
        
        
    }

    private void addDoor(Block clickedBlock){
        World world = clickedBlock.getLocation().getWorld();
        Door door = (Door) clickedBlock.getBlockData();
        Location loc = clickedBlock.getLocation();
        if (door.getHalf() == Half.TOP){
            loc = loc.subtract(0, 1, 0);
        }
        //loc = Utilities.stripRotation(loc);
        flooredLoc doorLoc = new flooredLoc(world, loc.getBlockZ(), loc.getBlockY(), loc.getBlockX());
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
        TextDisplay display = world.spawn(loc, TextDisplay.class);
        display.setText("Owned By: ");
        display.setVisibleByDefault(true);
        display.setBillboard(Billboard.CENTER);
        display.setCustomName("DoorLabel");
        DoorHandler.addDoor(doorLoc, display);
    }
}
