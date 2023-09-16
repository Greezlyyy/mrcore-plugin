package dev.yuri.mrcore.commands;

import dev.yuri.mrcore.MrCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private final MrCore plugin;

    public FlyCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if(player.hasPermission("mrcore.fly")) {
                    if(player.getAllowFlight()) {
                        player.setAllowFlight(false);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fly-disabled")));
                    }else {
                        player.setAllowFlight(true);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fly-enabled")));
                    }
                }else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
                }
            }else if(args.length == 1) {
                if(player.hasPermission("mrcore.fly-others")) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null) {
                        if(target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fly-disabled-others").replace("%player%", target.getName())));
                        }else {
                            target.setAllowFlight(true);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fly-enabled-others").replace("%player%", target.getName())));
                        }
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found")));
                    }
                }else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
                }
            }
        }else {
            System.out.println("Non sei un giocatore!");
        }
        return true;
    }
}
