package dev.yuri.mrcore.commands;

import dev.yuri.mrcore.MrCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {

    private final MrCore plugin;

    public NightCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.night")) {
                player.getWorld().setTime(14000);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("night")));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            }
        }else {
            System.out.println("Non sei un giocatore!");
        }
        return true;
    }
}
