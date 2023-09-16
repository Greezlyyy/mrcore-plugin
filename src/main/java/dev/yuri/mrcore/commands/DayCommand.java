package dev.yuri.mrcore.commands;

import dev.yuri.mrcore.MrCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {

    private final MrCore plugin;

    public DayCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.day")) {
                player.getWorld().setTime(0);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("day")));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            }
        }else {
            System.out.println("Non sei un giocatore!");
        }
        return true;
    }
}
