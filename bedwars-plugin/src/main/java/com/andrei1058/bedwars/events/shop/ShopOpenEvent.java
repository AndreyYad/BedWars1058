
package com.andrei1058.bedwars.events.shop;

import com.andrei1058.bedwars.arena.IArena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

//! из АПИ

public class ShopOpenEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player player;
    private final IArena arena;
    private boolean cancelled = false;

    /**
     * Triggered when the shop NPS is clicked.
     * 
     * @deprecated Use {@link #ShopOpenEvent(Player, IArena)}
     */
    @Deprecated
    public ShopOpenEvent(Player player) {
        this.player = player;
        this.arena = null;
    }

    /**
     * Triggered when the shop NPS is clicked.
     */
    public ShopOpenEvent(Player player, IArena arena) {
        this.player = player;
        this.arena = arena;
    }

    public IArena getArena() {
        return arena;
    }

    /**
     * Get the player who opened the shop.
     */
    public Player getPlayer() {
        return player;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
