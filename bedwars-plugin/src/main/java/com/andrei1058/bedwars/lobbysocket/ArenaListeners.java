package com.andrei1058.bedwars.lobbysocket;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.arena.IArena;
import com.andrei1058.bedwars.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.events.player.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.events.server.ArenaEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaListeners implements Listener {

    @EventHandler
    public void onPlayerJoinArena(PlayerJoinArenaEvent e) {
        if (e == null) return;
        final IArena a = e.getArena();
        Bukkit.getScheduler().runTaskAsynchronously(BedWars.plugin, ()-> ArenaSocket.sendMessage(ArenaSocket.formatUpdateMessage(a)));
    }

    @EventHandler
    public void onPlayerLeaveArena(PlayerLeaveArenaEvent e){
        if (e == null) return;
        final IArena a = e.getArena();
        Bukkit.getScheduler().runTaskAsynchronously(BedWars.plugin, ()-> ArenaSocket.sendMessage(ArenaSocket.formatUpdateMessage(a)));
    }

    @EventHandler
    public void onArenaStatusChange(GameStateChangeEvent e){
        if (e == null) return;
        final IArena a = e.getArena();
        Bukkit.getScheduler().runTaskAsynchronously(BedWars.plugin, ()-> ArenaSocket.sendMessage(ArenaSocket.formatUpdateMessage(a)));
    }

    @EventHandler
    public void onArenaLoad(ArenaEnableEvent e){
        if (e == null) return;
        final IArena a = e.getArena();
        Bukkit.getScheduler().runTaskAsynchronously(BedWars.plugin, ()-> ArenaSocket.sendMessage(ArenaSocket.formatUpdateMessage(a)));
    }
}
