package com.bubbaj2016.darkmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bubbaj2016.darkmc.JobHandler;
import com.bubbaj2016.darkmc.MoneyHandler;

public class JobCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        for (String jobInfo : JobHandler.getJobsList()) {
            player.sendMessage(jobInfo);
        }
        return true;
    }
}
