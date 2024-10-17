package com.bubbaj2016.darkmc.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;

import com.bubbaj2016.darkmc.DoorLoc;
public class DoorHandler {
    static ArrayList<String> doors = new ArrayList<>();
    static HashMap<DoorLoc, String> doorInfo = new HashMap<>();
    static HashMap<String, String> ownerInfo = new HashMap<>();
    public static void addDoor(DoorLoc doorLoc,String UUID){
        doorInfo.put(doorLoc, UUID);
        Bukkit.getServer().broadcastMessage(doorInfo.toString());
    }

    public static void addOwner(String signUUID, String ownerUUID){
        ownerInfo.put(signUUID, ownerUUID);
        Bukkit.getServer().broadcastMessage(ownerInfo.toString());

    }
    public static void removeOwner(String signUUID){
        ownerInfo.put(signUUID, "");
    }

    public static DoorLoc getDoorLocFromSignUUID(String UUID){
        for (DoorLoc loc : doorInfo.keySet()){
            if (doorInfo.get(loc).equals(UUID)){
                return loc;
            }
        }
        return null;
    }

    public static String getOwnerUUIDFromSignUUID(String UUID){
        return ownerInfo.get(UUID);
    }

    public static String getSignFromDoorLoc(DoorLoc loc){
        Bukkit.broadcastMessage(loc.toString());
        return doorInfo.get(loc);
    }

}
