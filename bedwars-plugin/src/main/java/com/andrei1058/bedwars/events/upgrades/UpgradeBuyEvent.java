
package com.andrei1058.bedwars.events.upgrades;

import com.andrei1058.bedwars.arena.IArena;
import com.andrei1058.bedwars.arena.team.ITeam;
import com.andrei1058.bedwars.upgrades.TeamUpgrade;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/// из АПИ

public class UpgradeBuyEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private TeamUpgrade teamUpgrade;
    private Player player;
    private final IArena arena;
    private ITeam team;
    private boolean cancelled = false;

    /**
     * Called when a Team Upgrade is bought
     */
    public UpgradeBuyEvent(TeamUpgrade teamUpgrade, Player player, ITeam team) {
        this.teamUpgrade = teamUpgrade;
        this.player = player;
        this.team = team;
        this.arena = team.getArena();
    }

    public IArena getArena() {
        return arena;
    }

    /**
     * Get who bought the upgrade
     */
    public Player getPlayer() {
        return player;
    }

    public ITeam getTeam() {
        return team;
    }

    public TeamUpgrade getTeamUpgrade() {
        return teamUpgrade;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancel event. Purchase will be cancelled.
     */
    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

}
