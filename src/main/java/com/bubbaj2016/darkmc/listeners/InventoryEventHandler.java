package com.bubbaj2016.darkmc.listeners;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.bubbaj2016.darkmc.MoneyHandler;


public class InventoryEventHandler implements Listener{
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if (event.getView().getTitle().equals("Shop")){
            if (event.getCurrentItem() != null){
                event.setCancelled(true);
                List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                int price = Integer.parseInt(lore.get(0));
                Player player = (Player) event.getWhoClicked();
                if (price <= MoneyHandler.loadPlayerBalance(player.getUniqueId().toString())){
                    ItemStack itemBought = event.getCurrentItem();
                    itemBought.getItemMeta().setLore(null);
                    event.getWhoClicked().getInventory().addItem(itemBought);
                    MoneyHandler.modMoneyPlayerMoney(player.getUniqueId().toString(), -price);
                }
                else {
                    player.sendMessage("you are broke as hell");
                }

            }
        }
    }
}
