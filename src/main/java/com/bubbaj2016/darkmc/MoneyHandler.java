package com.bubbaj2016.darkmc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class MoneyHandler {
    static HashMap<String, Integer> money = new HashMap<>(); 

    public static void loadValues(){
        String moneyFolder = Plugin.getPlugin().getDataFolder().getAbsolutePath().toString()+"/money.yaml";
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(moneyFolder));
        List<String> moneyInfo = config.getStringList("money");
        for (String info : moneyInfo) {
            String[] split = info.split(" ");
            String playerID = split[0];
            int balance = Integer.parseInt(split[1]);
            money.put(playerID, balance);
        }  
    }

    public static void saveMoney() throws IOException{
        String moneyFolder = Plugin.getPlugin().getDataFolder().getAbsolutePath().toString()+"/money.yaml";
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(moneyFolder));
        ArrayList<String> valuesToSave = new ArrayList<>();
        for (String string : money.keySet()) {
            valuesToSave.add(string+ " " + money.get(string));
        }
        config.set("money", valuesToSave);
        config.save(moneyFolder);
    }
    
    public static void registerPlayer(String playerUUID){
        money.put(playerUUID, 0);
    }

    public static void modMoneyPlayerMoney(String playerUUID, int modAmmount){
       int playerMoney = money.get(playerUUID);
       playerMoney += modAmmount;
       money.put(playerUUID, playerMoney);
    }

    public static int loadPlayerBalance(String playerID){
        if (money.get(playerID) == null){
            registerPlayer(playerID);
        }
        return money.get(playerID);
    }
}
