package com.andrei1058.bedwars.listeners;

import com.andrei1058.bedwars.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.events.player.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.events.server.ArenaDisableEvent;
import com.andrei1058.bedwars.events.server.ArenaEnableEvent;
import com.andrei1058.bedwars.arena.ArenaGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RefreshGUI implements Listener {

    @EventHandler
    public void onGameStateChange(GameStateChangeEvent e){
        int size = e.getArena().getPlayers().size();
        for (Player p : Bukkit.getOnlinePlayers()){
            ArenaGUI.refreshInv(p, e.getArena(), size);
        }
    }

    @EventHandler
    public void onPlayerJoinArena(PlayerJoinArenaEvent e){
        int size = e.getArena().getPlayers().size();
        if (!e.isSpectator()){
            size++;
        }
        for (Player p : Bukkit.getOnlinePlayers()){
            ArenaGUI.refreshInv(p, e.getArena(), size);
        }
    }

    @EventHandler
    public void onPlayerLeaveArena(PlayerLeaveArenaEvent e){
        int size = e.getArena().getPlayers().size();
        if (!e.isSpectator()){
            size--;
        }
        for (Player p : Bukkit.getOnlinePlayers()){
            ArenaGUI.refreshInv(p, e.getArena(), size);
        }
    }

    @EventHandler
    public void onArenaEnable(ArenaEnableEvent e){
        for (Player p : Bukkit.getOnlinePlayers()){
            ArenaGUI.refreshInv(p, e.getArena(), 0);
        }
    }

    @EventHandler
    public void onArenaDisable(ArenaDisableEvent e){
        for (Player p : Bukkit.getOnlinePlayers()){
            ArenaGUI.refreshInv(p, null, 0);
        }
    }
}
