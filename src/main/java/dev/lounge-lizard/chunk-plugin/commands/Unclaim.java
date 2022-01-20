package dev.lounge-lizard.chunk-plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import dev.lounge-lizard.chunk-plugin.managers.ChunkManager;

public class Unclaim {
    public Unclaim(ChunkManager chunkManager, Player player, Chunk chunk) {
        if (chunkManager.getOwner(chunk) != player) {
            player.sendMessage(ChatColor.RED + "You do not own this chunk!");
            return;
        }

        chunkManager.unclaim(player, chunk);
        player.sendMessage(ChatColor.GREEN + "You have successfully unclaimed this chunk!");
    }
}
