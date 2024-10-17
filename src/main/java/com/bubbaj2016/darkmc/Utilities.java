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
}


