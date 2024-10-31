package com.bubbaj2016.darkmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bubbaj2016.darkmc.MoneyHandler;

public class Money implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        if (args.length > 0 && player.isOp()){
            player.sendMessage("Updating Money");
            MoneyHandler.modMoneyPlayerMoney(player.getUniqueId().toString(), Integer.parseInt(args[0]));;
        }
        player.sendMessage(Integer.toString(MoneyHandler.loadPlayerBalance(player.getUniqueId().toString())));
        return true;
    }
}
