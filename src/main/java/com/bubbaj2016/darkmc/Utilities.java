package com.bubbaj2016.darkmc;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.material.Door;

public class Utilities {
    public static Entity getEntityByUniqueId(UUID uniqueId) {
    for (World world : Bukkit.getWorlds()) {
        for (Entity entity : world.getEntities()) {
            if (entity.getUniqueId().equals(uniqueId))
                return entity;
         }
    }

    return null;
}

    public static Location stripRotation(Location loc){
        return new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
    }

    public static boolean inLocation(Location currentLoc, Location loc1, Location loc2){
        if (Math.min(loc1.getBlockX(), loc2.getBlockX()) <= currentLoc.getBlockX() && currentLoc.getBlockX() <= Math.max(loc1.getBlockX(), loc2.getBlockX())){
            if (Math.min(loc1.getBlockY(), loc2.getBlockY()) <= currentLoc.getBlockY() && currentLoc.getBlockY() <= Math.max(loc1.getBlockY(), loc2.getBlockY())){
                if (Math.min(loc1.getBlockZ(), loc2.getBlockZ()) <= currentLoc.getBlockZ() && currentLoc.getBlockZ() <= Math.max(loc1.getBlockZ(), loc2.getBlockZ())){
                    return true;
                }
            }
        }
        return false;
    }
}


