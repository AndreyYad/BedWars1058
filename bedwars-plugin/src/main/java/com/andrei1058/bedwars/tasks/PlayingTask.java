
package com.andrei1058.bedwars.tasks;

import com.andrei1058.bedwars.arena.IArena;
import org.bukkit.scheduler.BukkitTask;

//! из АПИ

public interface PlayingTask {

    IArena getArena();

    BukkitTask getBukkitTask();

    /**
     * Get bukkit task id.
     */
    int getTask();

    int getBedsDestroyCountdown();

    int getDragonSpawnCountdown();

    int getGameEndCountdown();

    void cancel();
}
