package com.bubbaj2016.darkmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bubbaj2016.darkmc.JobHandler;

public class JobCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0){
            for (String jobInfo : JobHandler.getJobsList()) {
                player.sendMessage(jobInfo);
            }
        }
        else if (args.length == 1){
            if (args[0].equals("getJobName")){
                player.sendMessage(JobHandler.getPlayerJob(player.getUniqueId().toString()).getName());
            }
        }
        
        return true;
    }
}
