package com.andrei1058.bedwars._fwextension.validation;

import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.arena.IArena;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;

public class RegisteredListenerWithValidation extends RegisteredListener {

    private final boolean validationEnabled;

    public RegisteredListenerWithValidation(@NotNull Listener listener, @NotNull EventExecutor executor, @NotNull EventPriority priority, @NotNull Plugin plugin, boolean ignoreCancelled, boolean validationEnabled) {
        super(listener, executor, priority, plugin, ignoreCancelled);
        this.validationEnabled = validationEnabled;
    }

    @Override
    public void callEvent(@NotNull final Event event) throws EventException {
        //noinspection ConstantValue
        if (event == null) {
            getPlugin().getLogger().warning("null event for " + getListener().getClass().getName());
        }
        if (validationEnabled) {
            /// добавить остальные варианты ивентов
            if (event instanceof PlayerEvent) {
                Player player = ((PlayerEvent) event).getPlayer();
                IArena arena = Arena.getArenaByPlayer(player);
                if (arena == null || !arena.isPlayer(player)) {
                    return;
                }
            }
        }
        super.callEvent(event);
    }
}
