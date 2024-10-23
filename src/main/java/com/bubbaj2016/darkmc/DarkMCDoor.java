package com.bubbaj2016.darkmc;

import org.bukkit.Bukkit;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.TextDisplay;
import java.util.UUID;

public class DarkMCDoor {
    private DoorLoc doorLoc;
    private TextDisplay sign;
    private String owner = "";
    private boolean locked = false;
    DoorLoc loc1;
    DoorLoc loc2;
    public DarkMCDoor(DoorLoc loc, TextDisplay sign){
        doorLoc = loc;
        this.sign = sign;
    }

    public DoorLoc getDoorLoc() {
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

    public DoorLoc getSignLoc(){
        return new DoorLoc(sign.getLocation());
    }

    public void setLocs(DoorLoc loc1, DoorLoc loc2){
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    public String printLocs(){
        return "X: " + loc1.x + " Y: " + loc1.y + "\n" + "X: " + loc2.x + " Y: " + loc2.y;
    }

}
