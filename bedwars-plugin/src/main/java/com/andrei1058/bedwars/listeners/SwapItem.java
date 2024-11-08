package com.andrei1058.bedwars.listeners;

import com.andrei1058.bedwars.arena.GameState;
import com.andrei1058.bedwars.arena.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

//! из versionsupport_common

// Prevent item swap.
public class SwapItem implements Listener {

    @EventHandler
    public void itemSwap(PlayerSwapHandItemsEvent e) {
        if (e.isCancelled()) return;
        if (Arena.isPlaying(e.getPlayer())) {
            if (Arena.getArenaByPlayer(e.getPlayer()).getStatus() != GameState.playing)
                e.setCancelled(true);
        } else if (Arena.isSpectating(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}