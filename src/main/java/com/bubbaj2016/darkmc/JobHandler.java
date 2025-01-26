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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;

public class JobHandler {
    static ArrayList<Job> jobs = new ArrayList<>();
    static HashMap<String, Job> playerJobs = new HashMap<>();

    static HashMap<Player, TextDisplay> jobLabel = new HashMap<>();

    public static void addJobs() throws IOException{
        File[] files = Plugin.getPlugin().getDataFolder().listFiles();
        if (files.length == 1){
            YamlConfiguration templateConfig = new YamlConfiguration();
            ArrayList<String> itemList = new ArrayList<>();
            String path = Plugin.getPlugin().getDataFolder().getAbsolutePath();
            path = path + "/Civilian.yaml";
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
        TextDisplay display = world.spawn(player.getLocation().add(0, 1, 0), TextDisplay.class);
        display.setText(getPlayerJob(player.getUniqueId().toString()).getName());
        display.setVisibleByDefault(true);
        display.setBillboard(Billboard.CENTER);
        player.addPassenger(display);
        jobLabel.put(player, display);
    }

    public static void removeText(Player player){
        TextDisplay display = jobLabel.get(player);
        display.remove();
        jobLabel.remove(player);
    }
}
