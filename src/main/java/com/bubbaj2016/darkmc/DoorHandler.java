package com.bubbaj2016.darkmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.io.BukkitObjectInputStream;
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
        if (ownerUUID.equals("")){
            display.setText("Owned by: ");
        }
        else {
            display.setText("Owned by: " + Bukkit.getPlayer(UUID.fromString(ownerUUID)).getName());
        }
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

    public static void toggleLock(DoorLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                darkMCDoor.setLocked(!darkMCDoor.locked());
            }
        }
    }

    public static boolean getLocked(DoorLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                return darkMCDoor.locked();
            }
        }
        return false;
    }

    public static void setArea(DoorLoc loc1, DoorLoc loc2, DoorLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                darkMCDoor.setLocs(loc1, loc2);
            }
        }
    }

    public static DarkMCDoor getDoorByLoc(DoorLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                return darkMCDoor;
            }
        }
        return null;
    }


    public static boolean allowedToPlace(DoorLoc locOfPlacement, String playerUUID){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getOwner().equals(playerUUID)){
                if (Utilities.inLocation(locOfPlacement.toLocation(), darkMCDoor.loc1.toLocation(), darkMCDoor.loc2.toLocation())){
                    return true;
                }
            }
        }
        return false;
    }
    public static void removeOwnerFromDoors(String playerID){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getOwner().equals(playerID)){
                darkMCDoor.setOwner("");
            }
        }
    }

}
