package dev.yuri.mrcore.commands.gamemode;

import dev.yuri.mrcore.MrCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmsCommand implements CommandExecutor {

    private final MrCore plugin;

    public GmsCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.gms")) {
                if(args.length == 0) {
                    if(!player.getGameMode().equals(GameMode.SURVIVAL)) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gms")));
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("already-gms")));
                    }
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null) {
                        if(!target.getGameMode().equals(GameMode.SURVIVAL)) {
                            target.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gms-others").replace("%target%", target.getName())));
                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("already-gms-others").replace("%target%", target.getName())));
                        }
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "player-not-found"));
                    }
                }
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}
