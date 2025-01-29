package com.bubbaj2016.darkmc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
public class DoorHandler {
    static ArrayList<DarkMCDoor> doors = new ArrayList<>();

    public static void saveDoorToDatabase(DarkMCDoor door) throws IOException{
        String doorsFolder = Plugin.getPlugin().getDataFolder().getAbsolutePath().toString()+"/doors.yaml";
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(doorsFolder));
        String data =
            Utilities.locToDBString(door.getDoorLoc())+","+
            Utilities.locToDBString(door.loc1)+","+
            Utilities.locToDBString(door.loc2)+","+
            Utilities.locToDBString(door.getSignLoc())+','+
            door.getDoorLoc().getWorld().getName();
        List<String> doors = config.getStringList("doors");
        doors.add(data);
        config.set("doors", doors);
        config.save(doorsFolder);        
    }

    public static void loadDoorsFromDatabase(){
            YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(Plugin.getPlugin().getDataFolder().getAbsolutePath().toString()+"/doors.yaml"));
            List<String> doorList = config.getStringList("doors");
            for (String string : doorList) {
                System.out.println(string);
                String[] args = string.split(",");
                World world = Bukkit.getServer().getWorld(args[4]);
                Location locOfDoor = Utilities.stringToLoc(world, args[0]);
                Location loc1 = Utilities.stringToLoc(world, args[1]);
                Location loc2 = Utilities.stringToLoc(world, args[2]);
                TextDisplay display = world.spawn(Utilities.stringToLoc(world, args[3]), TextDisplay.class);
                display.setText("Owned By: ");
                display.setVisibleByDefault(true);
                display.setBillboard(Billboard.CENTER);
                display.setCustomName("DoorLabel");
                addDoor(new DarkMCDoor(new flooredLoc(locOfDoor), display, new flooredLoc(loc1), new flooredLoc(loc2)));   
            }
    }


    public static void addDoor(flooredLoc doorLoc,TextDisplay sign){
        DarkMCDoor door = new DarkMCDoor(doorLoc, sign);
        doors.add(door);
    }

    public static void addDoor(DarkMCDoor door){
        doors.add(door);
    }

    public static void setOwner(flooredLoc loc, String ownerUUID){
        DarkMCDoor door = null;
        for (int i = 0; i < doors.size(); i++){
            if (doors.get(i).getDoorLoc().equals(loc)){
               door = doors.get(i); 
            }  
        }
        if (door == null){
            return;
        }
        door.setOwner(ownerUUID);
    }

    public static String getDoorOwner(flooredLoc loc){
        for (int i = 0; i < doors.size(); i++){
            if (doors.get(i).getDoorLoc().equals(loc)){
                return doors.get(i).getOwner();
            }
        }
        return "";
    }

    public static boolean doorAdded(flooredLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                return true;
            }
        }
        return false;
    }

    public static void toggleLock(flooredLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                darkMCDoor.setLocked(!darkMCDoor.locked());
            }
        }
    }

    public static boolean getLocked(flooredLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                return darkMCDoor.locked();
            }
        }
        return false;
    }

    public static void setArea(flooredLoc loc1, flooredLoc loc2, flooredLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                darkMCDoor.setLocs(loc1, loc2);
            }
        }
    }

    public static DarkMCDoor getDoorByLoc(flooredLoc loc){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getDoorLoc().equals(loc)){
                System.out.println("Equals");
                System.out.println(darkMCDoor.getDoorLoc());
                System.out.println(loc);
                return darkMCDoor;
            }
            else {
                System.out.println("Not Equals");
                System.out.println(darkMCDoor.getDoorLoc());
                System.out.println(loc);
            }
        }
        return null;
    }


    public static boolean allowedToPlace(flooredLoc locOfPlacement, String playerUUID){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getOwner().equals(playerUUID)){
                if (Utilities.inLocation(locOfPlacement, darkMCDoor.loc1, darkMCDoor.loc2)){
                    return true;
                }
            }
        }
        return false;
    }
    public static void removeOwnerFromDoors(String playerID){
        for (DarkMCDoor darkMCDoor : doors) {
            if (darkMCDoor.getOwner().equals(playerID)){
                darkMCDoor.setOwner("");
            }
        }
    }

    public static void removeTextEntities(){
        for (Entity ent : Plugin.getPlugin().getServer().getWorlds().get(0).getEntities()) {
            if (ent instanceof TextDisplay){
                if (ent.getCustomName() != null && ent.getCustomName().equals("DoorLabel")){
                    System.out.println("Removed Door");
                    ent.remove();
                }
            }
        }
    }

}
