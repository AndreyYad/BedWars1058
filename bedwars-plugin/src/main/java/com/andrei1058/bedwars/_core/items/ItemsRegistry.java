package com.andrei1058.bedwars._core.items;
import com.andrei1058.bedwars._core.players.BwPlayer;
import com.andrei1058.bedwars._core.players.BwSpectator;
import com.andrei1058.bedwars.arena.IArena;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ItemsRegistry {

    static private Map<String, BwItem> onGround = new HashMap<>();
    static private Map<String, BwItem> inInventory = new HashMap<>();

    protected static BwPlayer registerBwItem(Player bukkitPlayer, IArena arena) {

    }

    public static BwPlayer findBwPlayer(String id) {

    }

    public static BwSpectator findBwSpectator(String id) {

    }

    public static BwPlayer findBwPlayer(@NonNull Player bukkitPlayer) {

    }

    public static BwSpectator findBwSpectator(@NonNull Player bukkitPlayer) {

    }

    public static BwPlayer asBwPlayer(@NonNull Player bukkitPlayer) {

    }

    public static BwSpectator asBwSpectator(@NonNull Player bukkitPlayer) {

    }

    protected static void removeBwPlayer(Player bukkitPlayer) {

    }

    protected static void removeBwSpectator(Player bukkitPlayer) {

    }
}
