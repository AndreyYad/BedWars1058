package com.andrei1058.bedwars.listeners.chat;

import com.andrei1058.bedwars.AFKSystem;
import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.andrei1058.bedwars.BedWars.plugin;

public class ChatAFK implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        Arena.afkCheck.remove(event.getPlayer().getUniqueId());
        if (AFKSystem.isPlayerAFK(event.getPlayer())) {
            // go sync
            Bukkit.getScheduler().runTask(plugin, () ->
                    AFKSystem.setPlayerAFK(event.getPlayer(), false)
            );
        }
    }
}
