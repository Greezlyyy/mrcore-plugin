package dev.yuri.mrcore.commands.gamemode;

import dev.yuri.mrcore.MrCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmcCommand implements CommandExecutor {

    private final MrCore plugin;

    public GmcCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if(player.hasPermission("mrcore.gmc")) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gmc-disabled")));
                    }else {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gmc-enabled")));
                    }
                }else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
                }
            }else if(args.length == 1) {
                if(player.hasPermission("mrcore.gmc-others")) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null) {
                        if(target.getGameMode().equals(GameMode.CREATIVE)) {
                            target.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gmc-disabled-others").replace("%player%", target.getName())));
                        }else {
                            target.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gmc-enabled-others").replace("%player%", target.getName())));
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
