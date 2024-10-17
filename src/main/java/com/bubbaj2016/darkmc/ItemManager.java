package com.bubbaj2016.darkmc;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
    public static ItemStack wand;
    
    public static void init(){
        createWand();
    }

    static void createWand(){
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("DoorWand");
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, false);
        item.setItemMeta(meta);
        wand = item;
    }
}
