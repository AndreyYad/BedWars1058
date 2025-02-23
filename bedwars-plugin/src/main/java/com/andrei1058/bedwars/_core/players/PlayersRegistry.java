package com.andrei1058.bedwars._core.players;

import com.andrei1058.bedwars.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.events.player.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.events.player.PlayerReJoinEvent;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.andrei1058.bedwars._core.utils.BwIdentifierUtils.*;

///! мб переназвать
@SuppressWarnings("unused")
public class PlayersRegistry {

    static private Map<String, BwPlayer> bwPlayers = new HashMap<>();
    static private Map<String, BwSpectator> bwSpectators = new HashMap<>();

    protected static void registerBwPlayer(BwPlayer bwPlayer) {
        String id = UUID.randomUUID().toString();
        bwPlayer._init(id);
        bwPlayers.put(id, bwPlayer);
    }

    protected static void registerBwSpectator(BwSpectator bwSpectator) {
        String id = UUID.randomUUID().toString();
        bwSpectator._init(id);
        bwSpectators.put(id, bwSpectator);
    }

    public static BwPlayer findBwPlayer(String id) {
        return bwPlayers.get(id);
    }

    public static BwSpectator findBwSpectator(String id) {
        return bwSpectators.get(id);
    }

    public static BwPlayer findBwPlayer(@NonNull Player bukkitPlayer) {
        String id = readIdentifier(bukkitPlayer);
        if (id == null) {
            return null;
        }
        return findBwPlayer(id);
    }

    public static BwSpectator findBwSpectator(@NonNull Player bukkitPlayer) {
        String id = readIdentifier(bukkitPlayer);
        if (id == null) {
            return null;
        }
        return findBwSpectator(id);
    }

    public static BwPlayer asBwPlayer(@NonNull Player bukkitPlayer) {
        return findBwPlayer(bukkitPlayer);
    }

    public static BwSpectator asBwSpectator(@NonNull Player bukkitPlayer) {
        return findBwSpectator(bukkitPlayer);
    }

    protected static void removeBwPlayer(Player bukkitPlayer) {
        String id = readIdentifier(bukkitPlayer);
        eraseIdentifier(bukkitPlayer);
        bwPlayers.remove(id);
    }

    protected static void removeBwSpectator(Player bukkitPlayer) {
        String id = readIdentifier(bukkitPlayer);
        eraseIdentifier(bukkitPlayer);
        bwSpectators.remove(id);
    }



    public static class PlayersRegistryListener implements Listener {

        @EventHandler(priority = EventPriority.LOWEST)
        public void onArenaJoin(PlayerJoinArenaEvent e) {
            if (e.isSpectator()) {
                registerBwSpectator(new BwSpectator(e.getPlayer(), e.getArena()));
            } else {
                registerBwPlayer(new BwPlayer(e.getPlayer(), e.getArena()));
            }
        }

        @EventHandler(priority = EventPriority.LOWEST)
        public void onArenaJoin(PlayerReJoinEvent e) {
            registerBwSpectator(new BwSpectator(e.getPlayer(), e.getArena()));
        }

        @EventHandler(priority = EventPriority.LOWEST)
        public void onArenaLeave(PlayerLeaveArenaEvent e) {
            if (e.isSpectator()) {
                removeBwSpectator(e.getPlayer());
            } else {
                removeBwPlayer(e.getPlayer());
            }
        }

        @EventHandler(priority = EventPriority.LOWEST)
        public void onQuit(PlayerQuitEvent e) {
            if (findBwPlayer(e.getPlayer()) != null) {
                removeBwPlayer(e.getPlayer());
            } else {
                removeBwSpectator(e.getPlayer());
            }
        }
    }
}
