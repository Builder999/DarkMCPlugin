package com.bubbaj2016.darkmc;

import org.bukkit.Location;
public class DarkMCDoor {
    private DoorLoc doorLoc;
    private String signID;
    private String owner = "";
    private boolean locked = false;
    DoorLoc loc1;
    DoorLoc loc2;
    public DarkMCDoor(DoorLoc loc, String signID){
        doorLoc = loc;
        this.signID = signID;
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
    }

    public String getSignID(){
        return this.signID;
    }

    public void setLocs(DoorLoc loc1, DoorLoc loc2){
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    public String printLocs(){
        return "X: " + loc1.x + " Y: " + loc1.y + "\n" + "X: " + loc2.x + " Y: " + loc2.y;
    }

}
