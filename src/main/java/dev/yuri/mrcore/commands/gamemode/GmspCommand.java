package dev.yuri.mrcore.commands.gamemode;

import dev.yuri.mrcore.MrCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmspCommand implements CommandExecutor {

    private final MrCore plugin;

    public GmspCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.gmsp")) {
                if(args.length == 0) {
                    if(!player.getGameMode().equals(GameMode.SPECTATOR)) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gmsp")));
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("already-gmsp")));
                    }
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null) {
                        if(!target.getGameMode().equals(GameMode.SPECTATOR)) {
                            target.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gmsp-others").replace("%target%", target.getName())));
                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("already-gmsp-others").replace("%target%", target.getName())));
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
