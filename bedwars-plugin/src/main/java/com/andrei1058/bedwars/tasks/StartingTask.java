
package com.andrei1058.bedwars.tasks;

import com.andrei1058.bedwars.arena.IArena;
import org.bukkit.scheduler.BukkitTask;

/// из АПИ

public interface StartingTask {

    int getCountdown();

    void setCountdown(int countdown);

    IArena getArena();

    int getTask();

    BukkitTask getBukkitTask();

    void cancel();
}
