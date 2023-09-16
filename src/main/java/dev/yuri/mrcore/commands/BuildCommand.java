package dev.yuri.mrcore.commands;

import dev.yuri.mrcore.MrCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mrcore.build")) {
                if(!MrCore.buildMode.contains(player.getUniqueId())) {
                    MrCore.buildMode.add(player.getUniqueId());
                    player.sendMessage("§bBuild §8> §eBuild mode §aENABLED");
                }else {
                    MrCore.buildMode.remove(player.getUniqueId());
                    player.sendMessage("§bBuild §8> §eBuild mode §cDISABLED");
                }
            }else {
                player.sendMessage("§cYou don't have the permission to execute this command!");
            }
        }
        return true;
    }
}
