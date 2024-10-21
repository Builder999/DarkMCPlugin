package com.bubbaj2016.darkmc.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bubbaj2016.darkmc.BlockHandler;
import com.bubbaj2016.darkmc.ItemManager;

public class RemoveBlocksCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (!player.isOp()){
                return false;
            }
            String targetID = Bukkit.getServer().getPlayer(args[0]).getUniqueId().toString();
            BlockHandler.removePlayerBlocks(targetID);
        }
        return true;
    }
    
}
