package dev.yuri.mrcore.commands.spawn;

import dev.yuri.mrcore.MrCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final MrCore plugin;

    public SetSpawnCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.setspawn")) {
                plugin.spawnConfig.set("spawn", player.getLocation());
                plugin.saveSpawnConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("set-spawn")));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            }
        }else {
            System.out.println("Non sei un giocatore!");
        }
        return true;
    }
}
