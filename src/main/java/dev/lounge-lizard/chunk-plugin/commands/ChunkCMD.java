package dev.lounge-lizard.chunk-plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.lounge-lizard.chunk-plugin.Chunkd;
import dev.lounge-lizard.chunk-plugin.guis.ChunkManagerGUI;
import org.jetbrains.annotations.NotNull;

public class ChunkCMD implements CommandExecutor {
    private final Chunkd chunkd;

    public ChunkCMD(Chunkd chunkd) {
        this.chunkd = chunkd;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof final Player player) {
            switch (args.length) {
                case 0 -> {
                    player.sendMessage(ChatColor.GREEN + "Chunkd " + ChatColor.GRAY + "Created by Jonathan Curtis");
                    player.sendMessage(ChatColor.DARK_GRAY + "You are running v1.0");
                }
                case 1 -> {
                    if (args[0].equals("claim")) {
                        new Claim(chunkd.chunkManager, player, player.getLocation().getChunk());
                        return true;
                    }
                    if (args[0].equals("unclaim")) {
                        new Unclaim(chunkd.chunkManager, player, player.getLocation().getChunk());
                        return true;
                    }
                    if (args[0].equals("manage")) {
                        new ChunkManagerGUI(chunkd).open(player);
                        return true;
                    }
                }
                case 2 -> {
                    if (args[0].equals("unclaim")) {
                        chunkd.chunkManager.unclaim(player, player.getLocation().getChunk());
                    }
                    if (args[0].equals("rename")) {
                        player.sendMessage("Please type what you would like to rename this chunk to in the chat:");
                    }
                    if (args[0].equals("permissions")) {
                        player.sendMessage("Coming soon");
                    }
                }
                default -> {
                    player.sendMessage(ChatColor.DARK_GREEN + "Usage: /claim [claim/unclaim]");
                    return false;
                }
            }
        } else {
            sender.sendMessage("Sorry, but this command must be ran in game.");
        }
        return false;
    }
}
