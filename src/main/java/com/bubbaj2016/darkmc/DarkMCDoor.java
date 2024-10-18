package com.bubbaj2016.darkmc;

public class DarkMCDoor {
    private DoorLoc doorLoc;
    private String signID;
    private String owner = "";
    private boolean locked = false;
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


}
