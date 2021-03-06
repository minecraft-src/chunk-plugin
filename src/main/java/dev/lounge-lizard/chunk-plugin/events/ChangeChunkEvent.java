package dev.lounge-lizard.chunk-plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import dev.lounge-lizard.chunk-plugin.managers.ChunkManager;

public class ChangeChunkEvent implements Listener {
    private final ChunkManager chunkManager;

    public ChangeChunkEvent(ChunkManager chunkManager) {
        this.chunkManager = chunkManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (event.getFrom().getChunk() != event.getTo().getChunk()) {
            if (chunkManager.getOwner(event.getTo().getChunk()) != null) {
                if (chunkManager.getOwner(event.getTo().getChunk()) != chunkManager.getOwner(event.getFrom().getChunk())) {
                    if (chunkManager.getChunkName(chunkManager.getOwner(event.getTo().getChunk()), event.getTo().getChunk()) != null) {
                        player.sendMessage(ChatColor.GREEN + "Welcome to " + chunkManager.getChunkName(chunkManager.getOwner(event.getTo().getChunk()), event.getTo().getChunk()) + ".");
                        player.sendMessage(ChatColor.GREEN + "Owner: " + chunkManager.getOwner(event.getTo().getChunk()).getName());
                    } else {
                        player.sendMessage(ChatColor.GREEN + "You have entered " + chunkManager.getOwner(event.getTo().getChunk()).getName() + "'s territory.");
                    }
                }
            }
        }
    }
}
