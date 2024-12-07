package com.andrei1058.bedwars.listeners;

import com.andrei1058.bedwars.arena.GameState;
import com.andrei1058.bedwars.bukkitwrap.validation.EventValidation;
import com.andrei1058.bedwars.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.arena.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AutoscaleListener implements Listener {

    @EventHandler
    public void onPlaying(GameStateChangeEvent e) {
        if (e.getNewState() == GameState.playing && Arena.canAutoScale(e.getArena().getArenaName())) {
            if (Arena.getGamesBeforeRestart() > 1){
                new Arena(e.getArena().getArenaName(), null);
            }
        }
    }
}
