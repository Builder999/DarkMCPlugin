package com.bubbaj2016.darkmc;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
    public static ItemStack key;
    public static ItemStack locWand;
    public static void init(){
        createKey();
        createLocWand();
    }

    static void createLocWand(){
        ItemStack item2 = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item2.getItemMeta();
        meta.setDisplayName("Door Location Wand");
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, false);
        item2.setItemMeta(meta);
        locWand = item2;
    }

    static void createKey(){
        ItemStack item = new ItemStack(Material.LEVER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Key");
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, false);
        item.setItemMeta(meta);
        key = item;
    }
}
