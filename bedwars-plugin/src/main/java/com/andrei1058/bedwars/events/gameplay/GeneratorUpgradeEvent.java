
package com.andrei1058.bedwars.events.gameplay;

import com.andrei1058.bedwars.arena.generator.IGenerator;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

//! из АПИ

public class GeneratorUpgradeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private IGenerator generator;

    /**
     * Called when a generator is upgraded.
     */
    public GeneratorUpgradeEvent(IGenerator generator) {
        this.generator = generator;
    }

    /**
     * Get generator
     */
    public IGenerator getGenerator() {
        return generator;
    }


    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
