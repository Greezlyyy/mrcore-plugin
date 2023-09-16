package dev.yuri.mrcore.commands;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import dev.yuri.mrcore.MrCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private final MrCore plugin;

    public VanishCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.vanish")) {
                if(!MrCore.vanishPlayers.contains(player.getUniqueId())) {
                    MrCore.vanishPlayers.add(player.getUniqueId());


                    for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                        if(onlinePlayers.hasPermission("mrcore.vanish"))
                            continue;
                        onlinePlayers.hidePlayer(player);
                    }

                    for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                        if(onlinePlayers.hasPermission("mrcore.vanish-alerts")) {
                            if(!onlinePlayers.equals(player)) {
                                onlinePlayers.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("vanish-enabled-alert").replace("%player%", player.getName())));
                            }
                        }
                    }

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("vanish-enabled")));
                }else {
                    MrCore.vanishPlayers.remove(player.getUniqueId());

                    for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                        onlinePlayers.showPlayer(player);
                    }

                    for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                        if(onlinePlayers.hasPermission("mrcore.vanish-alerts")) {
                            if(!onlinePlayers.equals(player)) {
                                onlinePlayers.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("vanish-disabled-alert").replace("%player%", player.getName())));
                            }
                        }
                    }

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("vanish-disabled")));
                    ActionBarAPI.sendActionBar(player, "", 1);
                }
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            }
        }else {
            System.out.println("Non sei un giocatore!");
        }
        return true;
    }
}
