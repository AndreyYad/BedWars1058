
package com.andrei1058.bedwars.events.player;

import com.andrei1058.bedwars.arena.IArena;
import com.andrei1058.bedwars.arena.team.ITeam;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/// из АПИ

@SuppressWarnings("unused")
///Bed Bug это типа клоп, но ивент про спавн чешуйниц
public class PlayerBedBugSpawnEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final IArena arena;
    private final ITeam playerTeam;

    ///чел случайно скопировал видимо. Вызывается при спавне чешуйницы (но не снарядом...)
    /**
     * Called when a bed gets destroyed.
     */
    public PlayerBedBugSpawnEvent(Player p, ITeam playerTeam, IArena arena) {
        this.player = p;
        this.playerTeam = playerTeam;
        this.arena = arena;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public IArena getArena() {
        return arena;
    }

    public ITeam getPlayerTeam() {
        return playerTeam;
    }
}
