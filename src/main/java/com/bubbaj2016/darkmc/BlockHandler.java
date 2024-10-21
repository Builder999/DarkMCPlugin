package com.bubbaj2016.darkmc;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockHandler {
    static HashMap<String, ArrayList<Block>> blocks = new HashMap<>();



    public static void addBlock(String playerUUID, Block block){
        if (blocks.get(playerUUID) == null){
            blocks.put(playerUUID, new ArrayList<Block>());
        }
        blocks.get(playerUUID).add(block);
    }

    public static void removeBlock(String playerUUID, Block block){
        if (blocks.get(playerUUID) == null){
            blocks.put(playerUUID, new ArrayList<Block>());
        }
        blocks.get(playerUUID).remove(block);
    }

    public static boolean ownsBlock(String playerUUID, Block block){
        if (blocks.get(playerUUID) == null){
            return false;
        }
        for (Block forBlock : blocks.get(playerUUID)) {
            if (forBlock.equals(block)){
                return true;
            }
        }
        return false;
    }

    public static void removePlayerBlocks(String playerUUID){
        if (blocks.get(playerUUID) != null){
            for (Block blockToBreak : blocks.get(playerUUID)) {
                blockToBreak.breakNaturally();
                Bukkit.broadcastMessage("Removing Block");
            }
        }
        blocks.get(playerUUID).clear();
    }

}
