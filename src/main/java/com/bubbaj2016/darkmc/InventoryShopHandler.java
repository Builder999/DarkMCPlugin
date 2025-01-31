package com.bubbaj2016.darkmc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryShopHandler {
    static HashMap<String, ArrayList<ItemStack>> jobShops = new HashMap<>();
    public static void createInventories() throws IOException {
        String jobFolder = Plugin.getPlugin().getDataFolder().getAbsolutePath()+ "/Jobs";
        File jobFolderFile = new File(jobFolder);
        jobFolderFile.mkdirs();
        File[] files = jobFolderFile.listFiles();
        if (files.length == 0){
            JobHandler.createDefaultJobFile(jobFolder);
        }
        for (File file : files) {
            FileConfiguration job = YamlConfiguration.loadConfiguration(file);
            List<String> shopItems = job.getStringList("shopItems");
            String jobName = job.getString("name");
            for(String shopItem: shopItems){
                String[] jobShopItems = shopItem.split(",");
                // String item = jobShopItems[0];
                // int amount = Integer.parseInt(jobShopItems[1]);
                // int price = Integer.parseInt(jobShopItems[2]);
                ItemStack stack = new ItemStack(Material.matchMaterial(jobShopItems[0]), Integer.parseInt(jobShopItems[1]));
                ItemMeta stackMeta = stack.getItemMeta();
                ArrayList<String> price = new ArrayList<>();
                price.add(jobShopItems[2]);
                stackMeta.setLore(price);
                stack.setItemMeta(stackMeta);
                ArrayList<ItemStack> jobItemList = jobShops.get(jobName);
                if (jobItemList == null){
                    jobItemList = new ArrayList<>();
                }
                jobItemList.add(stack);
                jobShops.put(jobName, jobItemList);
            }
        }    
    }

     public static Inventory getJobInv(String job){
        Inventory inv = Bukkit.createInventory(null,27, "Shop");
        if (jobShops.get(job)==null){
            System.out.println(job);
        }
        else {
            for (ItemStack itemStack : jobShops.get(job)) {
                inv.addItem(itemStack);
            }
        }
        return inv;
     } 
}
