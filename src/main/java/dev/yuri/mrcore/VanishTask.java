package dev.yuri.mrcore;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class VanishTask extends BukkitRunnable {

    private final MrCore plugin;

    public VanishTask(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(MrCore.vanishPlayers.contains(player.getUniqueId())) {
                ActionBarAPI.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("vanish-enabled-actionbar")));
            }
        }
    }
}
