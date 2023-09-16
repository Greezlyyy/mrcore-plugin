package dev.yuri.mrcore.commands;

import dev.yuri.mrcore.MrCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {

    private final MrCore plugin;

    public TpCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.tp")) {
                if(args.length == 0) {
                    player.sendMessage("§6Commands list §7(/tp)");
                    player.sendMessage("§8• §7/tp <player>");
                    player.sendMessage("§8• §7/tp <player> <player>");
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        player.teleport(target);
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found")));
                    }
                }else if(args.length == 2) {
                    Player target1 = Bukkit.getPlayerExact(args[0]);
                    Player target2 = Bukkit.getPlayerExact(args[1]);
                    if(target1 != null) {
                        if(target2 != null) {
                            target1.teleport(target2);
                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found")));
                        }
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found")));
                    }
                }else {
                    player.sendMessage("§6Commands list §7(/tp)");
                    player.sendMessage("§8• §7/tp <player>");
                    player.sendMessage("§8• §7/tp <player> <player>");
                }
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}
