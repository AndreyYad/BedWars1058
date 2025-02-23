package com.andrei1058.bedwars._core.players;

import com.andrei1058.bedwars.arena.IArena;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = true, exclude = {"isSpectator"})
public class BwSpectator extends BwPlayer {

    @Getter private final boolean isSpectator = true;

    protected BwSpectator(Player bukkitPlayer, IArena arena) {
        super(bukkitPlayer, arena);
    }
}
