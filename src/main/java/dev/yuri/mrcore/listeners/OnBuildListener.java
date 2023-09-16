package dev.yuri.mrcore.listeners;

import dev.yuri.mrcore.MrCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBuildListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!MrCore.buildMode.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!MrCore.buildMode.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }
}
