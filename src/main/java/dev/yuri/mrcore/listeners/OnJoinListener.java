package dev.yuri.mrcore.listeners;

import dev.yuri.mrcore.MrCore;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class OnJoinListener implements Listener {

    private final MrCore plugin;

    public OnJoinListener(MrCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        User user = LuckPermsProvider.get().getUserManager().getUser(event.getPlayer().getUniqueId());
        String prefix = user.getCachedData().getMetaData().getPrefix();
        if(prefix != null) {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join-message").replace("%player%", prefix + event.getPlayer().getName())));
        }else if(prefix == null) {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join-message").replace("%player%", event.getPlayer().getName())));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(plugin.getConfig().getBoolean("spawn-on-join")) {
            Location spawnLocation = (Location) plugin.spawnConfig.get("spawn");
            event.getPlayer().teleport(spawnLocation);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        User user = LuckPermsProvider.get().getUserManager().getUser(event.getPlayer().getUniqueId());
        String prefix = user.getCachedData().getMetaData().getPrefix();
        if(prefix != null) {
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("quit-message").replace("%player%", prefix + event.getPlayer().getName())));
        }else if(prefix == null) {
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("quit-message").replace("%player%", event.getPlayer().getName())));
        }
    }

    @EventHandler
    public void onTest(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
