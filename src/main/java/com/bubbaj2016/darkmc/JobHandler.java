package com.bubbaj2016.darkmc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class JobHandler {
    static ArrayList<Job> jobs = new ArrayList<>();
    static HashMap<String, Job> playerJobs = new HashMap<>();
    static public void addJobs() throws IOException{
        File[] files = Plugin.getPlugin().getDataFolder().listFiles();
        if (files.length == 1){
            YamlConfiguration templateConfig = new YamlConfiguration();
            ArrayList<String> itemList = new ArrayList<>();
            String path = Plugin.getPlugin().getDataFolder().getAbsolutePath();
            path = path + "/Civilian.yaml";
            itemList.add("Dirt");
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

    static public ArrayList<String> getJobsList(){
        ArrayList<String> jobList = new ArrayList<>();
        for (Job job: jobs){
            String stringToAdd = "Name: " + job.name + " Category: " + job.category + "Max Slots: " + job.maxSlots + "items: ";
            for (String string : job.items){
                stringToAdd = stringToAdd + string + " "; 
            }
            jobList.add(stringToAdd);
        }
        return jobList;
    }

}
