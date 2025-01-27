package com.bubbaj2016.darkmc;


import org.bukkit.Location;
import org.bukkit.World;

public class flooredLoc extends Location{

    public flooredLoc(World world, double z, double y, double x){
        super(world, x, y, z);
        this.setX(locToBlock(this.getX()));
        this.setY(locToBlock(this.getY()));
        this.setZ(locToBlock(this.getZ()));
    }

    public flooredLoc(Location loc){
        super(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    @Override
    public String toString() {
        return "X: " + this.getX() + " Y: " + this.getBlockY() + " Z: " + this.getBlockZ() + "World: " + this.getWorld();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()){
            return false;
        }
        flooredLoc otherLoc = (flooredLoc) obj;

        if (this.getBlockX() == otherLoc.getBlockX()){
            if (this.getBlockY() == otherLoc.getBlockY()){
                if (this.getBlockZ() == otherLoc.getBlockZ()){
                    if (this.getWorld().equals(otherLoc.getWorld())){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}