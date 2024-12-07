package com.andrei1058.bedwars;

import com.andrei1058.bedwars.bukkitwrap.PluginManagerWrap;
import com.andrei1058.bedwars.events.player.PlayerAfkEvent;
import com.andrei1058.bedwars.arena.Arena;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AFKSystem {
    private static final HashMap<UUID, Integer> afkPlayers = new HashMap<>();

    public static boolean isPlayerAFK(Player player) {
        return afkPlayers.containsKey(player.getUniqueId());
    }

    public static void setPlayerAFK(Player player, boolean value) {
        if (value) {
            if (!afkPlayers.containsKey(player.getUniqueId())) {
                afkPlayers.put(player.getUniqueId(), Arena.afkCheck.get(player.getUniqueId()));
                PluginManagerWrap.callEvent(new PlayerAfkEvent(player, PlayerAfkEvent.AFKType.START));
            }
        } else {
            if (afkPlayers.containsKey(player.getUniqueId())) {
                afkPlayers.remove(player.getUniqueId());
                PluginManagerWrap.callEvent(new PlayerAfkEvent(player, PlayerAfkEvent.AFKType.END));
            }
            Arena.afkCheck.remove(player.getUniqueId());
        }
    }
}
