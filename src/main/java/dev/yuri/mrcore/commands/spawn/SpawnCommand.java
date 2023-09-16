package dev.yuri.mrcore.commands.spawn;

import dev.yuri.mrcore.MrCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final MrCore plugin;

    public SpawnCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Location location = (Location) plugin.spawnConfig.get("spawn");
            if(location != null) {
                player.teleport(location);
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("spawn-not-set")));
            }
        }else {
            System.out.println("Non sei un giocatore!");
        }
        return true;
    }
}
