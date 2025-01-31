package com.bubbaj2016.darkmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bubbaj2016.darkmc.BlockHandler;
import com.bubbaj2016.darkmc.InventoryShopHandler;
import com.bubbaj2016.darkmc.JobHandler;

public class ShopCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.openInventory(InventoryShopHandler.getJobInv(JobHandler.getPlayerJob(player.getUniqueId().toString()).getName()));
        }
        return true;    
    }
}
