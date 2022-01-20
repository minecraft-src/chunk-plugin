package dev.lounge-lizard.chunk-plugin;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import dev.lounge-lizard.chunk-plugin.commands.ChunkCMD;
import dev.lounge-lizard.chunk-plugin.commands.EditName;
import dev.lounge-lizard.chunk-plugin.events.ChangeChunkEvent;
import dev.lounge-lizard.chunk-plugin.guis.ChunkManagerGUI;
import dev.lounge-lizard.chunk-plugin.managers.ChunkManager;
import dev.lounge-lizard.chunk-plugin.managers.LocalDataManager;

import java.io.IOException;

public final class Chunkd extends JavaPlugin {
    public ChunkManager chunkManager;
    public LocalDataManager ldm;
    public EditName editName;

    @Override
    public void onEnable() {
        this.ldm = new LocalDataManager(this);
        this.chunkManager = new ChunkManager(this);
        this.editName = new EditName(chunkManager);

        ldm.createChunkConfig();

        System.out.println(ldm.toString());

        this.getCommand("chunk").setExecutor(new ChunkCMD(this));

        this.getServer().getPluginManager().registerEvents(new ChangeChunkEvent(this.chunkManager), this);
        this.getServer().getPluginManager().registerEvents(new ChunkManagerGUI(this), this);
        this.getServer().getPluginManager().registerEvents(editName, this);
    }

    @Override
    public void onDisable() {
        saveConfig();
        try {
            ldm.getChunkConfig().save(ldm.getChunksFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
