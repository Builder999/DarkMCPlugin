package com.bubbaj2016.darkmc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;

public class JobHandler {
    static ArrayList<Job> jobs = new ArrayList<>();
    static HashMap<String, Job> playerJobs = new HashMap<>();

    static HashMap<Player, Entity> jobLabel = new HashMap<>();

    public static void addJobs() throws IOException{
        String jobFolder = Plugin.getPlugin().getDataFolder().getAbsolutePath()+ "/Jobs";

        File jobFolderFile = new File(jobFolder);
        jobFolderFile.mkdirs();
        File[] files = jobFolderFile.listFiles();
        if (files.length == 0){
            YamlConfiguration templateConfig = new YamlConfiguration();
            ArrayList<String> itemList = new ArrayList<>();
            String path = jobFolder + "/Civilian.yaml";
            itemList.add("Dirt 64");
            templateConfig.set("name", "Civilian");
            templateConfig.set("category", "Civilian");
            templateConfig.set("maxSlots", 10);
            templateConfig.set("items", itemList);
            templateConfig.save(path);
        }
        for (File file : files) {
            FileConfiguration job = YamlConfiguration.loadConfiguration(file);
            if (job.getString("name") != null){
                jobs.add(new Job(job.getString("name"), job.getString("category"), job.getInt("maxSlots"), job.getStringList("items")));
            }
            
            
        }
    }

    public static ArrayList<String> getJobsList(){
        ArrayList<String> jobList = new ArrayList<>();
        for (Job job: jobs){
            String stringToAdd = "Name: " + job.name + " Category: " + job.category + " Max Slots: " + job.maxSlots + " items: ";
            for (String string : job.items){
                stringToAdd = stringToAdd + string + " "; 
            }
            jobList.add(stringToAdd);
        }
        return jobList;
    }

    public static int getNumPeopleOnJob(Job job){
        int i = 0;
        for (Job jobIndex : playerJobs.values()){
            if (jobIndex.equals(job)){
                i++;
            }
        }
        return i;
    }
    public static void assignJob(String PlayerID, Job job){
        playerJobs.put(PlayerID, job);
    }

    public static void assignJob(String playerID, String jobName){
        playerJobs.put(playerID, getJobFromJobName(jobName));
        ArrayList<String> items = getJobFromJobName(jobName).getitems();
        for (String itemName : items) {
            itemName = itemName.strip();
            String[] itemInfo = itemName.split(" ");
            Bukkit.getPlayer(UUID.fromString(playerID)).getInventory().addItem(new ItemStack(Material.matchMaterial(itemInfo[0]),Integer.parseInt(itemInfo[1])));
        }
    }
    public static void removePlayerFromJobList(String PlayerID){
        playerJobs.remove(PlayerID);
    }
    public static Job getJobFromJobName(String jobName){
        for (Job job : jobs) {
            if (jobName.equals(job.name)){
                return job;
            }
        }
        return null;
    }
    public static Job getPlayerJob(String PlayerID){
        if (playerJobs.get(PlayerID).equals(null)){
            assignJob(PlayerID, "Civilian");
        }
        return playerJobs.get(PlayerID);
    }

    public static void addJobBar(Player player){
        World world = player.getWorld();
        TextDisplay display = world.spawn(player.getLocation(), TextDisplay.class);
        ArmorStand stand = world.spawn(player.getLocation(), ArmorStand.class);
        display.setText(getPlayerJob(player.getUniqueId().toString()).getName());
        display.setVisibleByDefault(true);
        display.setBillboard(Billboard.CENTER);
        stand.addPassenger(display);
        stand.setSmall(true);
        stand.setVisible(false);
        stand.setCollidable(false);
        player.addPassenger(stand);
        jobLabel.put(player, stand);
    }

    public static void removeText(Player player){
        for (Entity entitity : jobLabel.get(player).getPassengers()){
            entitity.remove();
        }
        jobLabel.get(player).remove();
        jobLabel.remove(player);
    }
}
