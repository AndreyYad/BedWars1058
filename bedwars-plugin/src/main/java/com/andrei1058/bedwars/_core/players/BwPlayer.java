package com.andrei1058.bedwars._core.players;

import com.andrei1058.bedwars._core.BwObject;
import com.andrei1058.bedwars.arena.IArena;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bukkit.entity.Player;

import static com.andrei1058.bedwars._core.utils.BwIdentifierUtils.*;

@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = true, exclude = {"bukkitPlayer", "isSpectator"})
public class BwPlayer extends BwObject {

    private Player bukkitPlayer;

    @Getter private final boolean isSpectator = false;

    protected BwPlayer(Player bukkitPlayer, IArena arena) {
        super(arena);
        this.bukkitPlayer = bukkitPlayer;
        writeIdentifier(this.bukkitPlayer, this.getId());
    }

    public void destruct() {
        eraseIdentifier(bukkitPlayer);
    }

    public Player forBukkit() {
        return bukkitPlayer;
    }
}
