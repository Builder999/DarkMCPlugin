package com.bubbaj2016.darkmc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.TextDisplay;
import java.util.UUID;

public class DarkMCDoor {
    private flooredLoc doorLoc;
    private TextDisplay sign;
    private String owner = "";
    private boolean locked = false;
    flooredLoc loc1;
    flooredLoc loc2;
    public DarkMCDoor(flooredLoc loc, TextDisplay sign){
        doorLoc = loc;
        this.sign = sign;
    }

    public DarkMCDoor(flooredLoc loc, TextDisplay sign, flooredLoc loc1, flooredLoc loc2){
        doorLoc = loc;
        this.sign = sign;
    }

    public flooredLoc getDoorLoc() {
        return doorLoc;
    }

    public boolean locked(){
        return locked;
    }
    public void setLocked(boolean locked){
        this.locked = locked;
    }

    public String getOwner(){
        return owner;
    }
    
    public void setOwner (String id){
        owner = id;
        if (id.equals("")){
            sign.setText("Owned by: ");
        }
        else {
            sign.setText("Owned by: " + Bukkit.getPlayer(UUID.fromString(id)).getName());
        }
    }

    public TextDisplay getSign(){
        return this.sign;
    }

    public Location getSignLoc(){
        return sign.getLocation();
    }

    public void setLocs(flooredLoc loc1, flooredLoc loc2){
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    public String printLocs(){
        return "X: " + loc1.getBlockX() + " Y: " + loc1.getBlockY() + "\n" + "X: " + loc2.getBlockX() + " Y: " + loc2.getBlockY();
    }

}
