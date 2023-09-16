package dev.yuri.mrcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickallCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.kickall")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if(!players.equals(player)) {
                        players.kickPlayer("§cSei stato kickato!");
                    }
                }
            }else {
                player.sendMessage("§cNon hai il permesso!");
            }
        }
        return true;
    }
}
