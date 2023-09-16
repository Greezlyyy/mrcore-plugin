package dev.yuri.mrcore;

import dev.yuri.mrcore.commands.*;
import dev.yuri.mrcore.commands.gamemode.GmaCommand;
import dev.yuri.mrcore.commands.gamemode.GmcCommand;
import dev.yuri.mrcore.commands.gamemode.GmsCommand;
import dev.yuri.mrcore.commands.gamemode.GmspCommand;
import dev.yuri.mrcore.commands.spawn.SetSpawnCommand;
import dev.yuri.mrcore.commands.spawn.SpawnCommand;
import dev.yuri.mrcore.listeners.OnBuildListener;
import dev.yuri.mrcore.listeners.OnJoinListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class MrCore extends JavaPlugin {

    private File spawnFile;
    public FileConfiguration spawnConfig;
    public static List<UUID> buildMode = new ArrayList<>();
    public static List<UUID> vanishPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        getLogger().info("§8[§eMrCore§8] §aPlugin enabled successfully!");
        loadConfig();
        registerCommands();
        loadSpawnConfig();
        registerListeners();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new OnJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new OnBuildListener(), this);
        new VanishTask(this).runTaskTimer(this, 0L, 1L);
    }

    private void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("gmc").setExecutor(new GmcCommand(this));
        getCommand("day").setExecutor(new DayCommand(this));
        getCommand("night").setExecutor(new NightCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("clearchat").setExecutor(new ClearChatCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("kickall").setExecutor(new KickallCommand());
        getCommand("gms").setExecutor(new GmsCommand(this));
        getCommand("gmsp").setExecutor(new GmspCommand(this));
        getCommand("gma").setExecutor(new GmaCommand(this));
        getCommand("tp").setExecutor(new TpCommand(this));
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
    private void loadSpawnConfig() {
        getDataFolder().mkdirs();
        spawnFile = new File(getDataFolder(), "spawn.yml");
        if(!spawnFile.exists()) {
            try {
                spawnFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
    }

    public void saveSpawnConfig() {
        try {
            spawnConfig.save(spawnFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}