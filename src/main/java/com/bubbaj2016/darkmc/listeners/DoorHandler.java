package com.bubbaj2016.darkmc.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.io.BukkitObjectInputStream;

import com.bubbaj2016.darkmc.DarkMCDoor;
import com.bubbaj2016.darkmc.DoorLoc;
import com.bubbaj2016.darkmc.Utilities;
public class DoorHandler {
    static ArrayList<DarkMCDoor> doors = new ArrayList<>();
    public static void addDoor(DoorLoc doorLoc,String UUID){
        DarkMCDoor door = new DarkMCDoor(doorLoc, UUID);
        doors.add(door);
    }

    public static void setOwner(DoorLoc loc, String ownerUUID){
        DarkMCDoor door = new DarkMCDoor(loc, ownerUUID);
        for (int i = 0; i < doors.size(); i++){
            if (doors.get(i).getDoorLoc().equals(loc)){
               door = doors.get(i); 
            }  
        }
        door.setOwner(ownerUUID);
        Entity signEnt = Utilities.getEntityByUniqueId(UUID.fromString(door.getSignID()));
        TextDisplay display = (TextDisplay) signEnt;
        display.setText("Owned by: " + Bukkit.getPlayer(UUID.fromString(ownerUUID)).getName());
    }

    public static String getDoorOwner(DoorLoc loc){
        for (int i = 0; i < doors.size(); i++){
            if (doors.get(i).getDoorLoc().equals(loc)){
                return doors.get(i).getOwner();
            }
        }
        return "";
    }

    public static boolean doorAdded(DoorLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                return true;
            }
        }
        return false;
    }

}
