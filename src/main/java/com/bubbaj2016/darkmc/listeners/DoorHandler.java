package com.bubbaj2016.darkmc.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
public class DoorHandler {
    static ArrayList<String> doors = new ArrayList<>();
    static HashMap<Location, String> doorInfo = new HashMap<>();
    static HashMap<String, String> ownerInfo = new HashMap<>();
    public static void addDoor(Location doorLoc,String UUID){
        doorInfo.put(doorLoc, UUID);
    }

    public static void addOwner(String signUUID, String ownerUUID){
        ownerInfo.put(signUUID, ownerUUID);
    }
    public static void removeOwner(String signUUID){
        ownerInfo.put(signUUID, "");
    }

    public static Location getDoorLocFromSignUUID(String UUID){
        for (Location loc : doorInfo.keySet()){
            if (doorInfo.get(loc).equals(UUID)){
                return loc;
            }
        }
        return null;
    }

    public static String getOwnerUUIDFromSignUUID(String UUID){
        return ownerInfo.get(UUID);
    }

    public static String getSignFromDoorLoc(Location loc){
        return doorInfo.get(loc);
    }

}
