package com.bubbaj2016.darkmc;


import org.bukkit.Location;
import org.bukkit.World;

public class flooredLoc {
    World world;
    int x;
    int y;
    int z;

    public flooredLoc(World world, int x, int y, int z){
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public flooredLoc(Location loc){
        this.world = loc.getWorld();
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null){
           flooredLoc loc = (flooredLoc) obj;
           
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

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " Z: " + z + "World: " + world;
    }

    public static flooredLoc fromString(String string){
        
        return null;
    }
    public Location toLocation(){
        return new Location(world, x, y, z);
    }
}