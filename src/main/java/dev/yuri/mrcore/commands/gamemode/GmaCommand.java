package dev.yuri.mrcore.commands.gamemode;

import dev.yuri.mrcore.MrCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmaCommand implements CommandExecutor {

    private final MrCore plugin;

    public GmaCommand(MrCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.gma")) {
                if(args.length == 0) {
                    if(!player.getGameMode().equals(GameMode.ADVENTURE)) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gma")));
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("already-gma")));
                    }
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null) {
                        if(!target.getGameMode().equals(GameMode.ADVENTURE)) {
                            target.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gma-others").replace("%target%", target.getName())));
                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("already-gma-others").replace("%target%", target.getName())));
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
