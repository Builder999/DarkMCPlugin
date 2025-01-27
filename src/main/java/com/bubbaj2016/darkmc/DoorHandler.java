package com.bubbaj2016.darkmc;

import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.Connection;

import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
public class DoorHandler {
    static ArrayList<DarkMCDoor> doors = new ArrayList<>();

    public static void saveDoorToDatabase(DarkMCDoor door){
        try {
        Connection conn2 = DriverManager.getConnection(Plugin.url);
        String sql = "INSERT INTO doors VALUES(?,?,?,?,?)";
        PreparedStatement pstm = conn2.prepareStatement(sql);
        pstm.setString(1, Utilities.locToDBString(door.getDoorLoc()));
        pstm.setString(2, Utilities.locToDBString(door.loc1));
        pstm.setString(3, Utilities.locToDBString(door.loc2));
        pstm.setString(4, Utilities.locToDBString(door.getSignLoc()));
        pstm.setString(5, door.getDoorLoc().getWorld().getName());

        pstm.executeUpdate();
        conn2.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void loadDoorsFromDatabase(){
        try {
            Connection conn = DriverManager.getConnection(Plugin.url);
            String sql = "SELECT * FROM doors;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet results = pstm.executeQuery();
            while (results.next()){
                World world = Bukkit.getServer().getWorld(results.getString(5));
                Location locOfDoor = Utilities.stringToLoc(world, results.getString(1));
                Location loc1 = Utilities.stringToLoc(world, results.getString(2));
                Location loc2 = Utilities.stringToLoc(world, results.getString(3));
                TextDisplay display = world.spawn(Utilities.stringToLoc(world, results.getString(4)), TextDisplay.class);
                display.setText("Owned By: ");
                display.setVisibleByDefault(true);
                display.setBillboard(Billboard.CENTER);
                display.setCustomName("DoorLabel");
                addDoor(new DarkMCDoor(new flooredLoc(locOfDoor), display, new flooredLoc(loc1), new flooredLoc(loc2)));
            }
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println(e.getMessage());
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
                return darkMCDoor;
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
                if (ent.getCustomName().equals("DoorLabel")){
                    System.out.println("Removed Door");
                    ent.remove();
                }
            }
        }
    }

}
