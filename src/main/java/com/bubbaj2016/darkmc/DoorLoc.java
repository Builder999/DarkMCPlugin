package com.bubbaj2016.darkmc;

import java.time.LocalDate;

import org.bukkit.Location;
import org.bukkit.World;

public class DoorLoc {
    World world;
    int x;
    int y;
    int z;

    public DoorLoc(World world, int x, int y, int z){
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public DoorLoc(Location loc){
        this.world = loc.getWorld();
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null){
           DoorLoc loc = (DoorLoc) obj;
           
           if (this.world.equals(loc.world)){
                if (this.x == loc.x){
                    if (this.y == loc.y){
                        if (this.z == loc.z){
                            return true;
                        }
                    }
                }
           }
        }
        return false;

    }

    @Override
    public int hashCode() {
        int code = 0;
        code += this.world.getUID().hashCode();
        code += 19*this.x;
        code += 19*this.y;
        code += 19*this.z;
        return code;
    }
}