package com.andrei1058.bedwars.arena.feature;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars._fwextension.helpering.statichelpers.PluginManagerHelper;
import com.andrei1058.bedwars.arena.IArena;
import com.andrei1058.bedwars.configuration.ConfigPath;
import com.andrei1058.bedwars.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.events.player.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.metrics.MetricsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.LinkedList;

public class SpoilPlayerTNTFeature {

    private static SpoilPlayerTNTFeature instance;
    private final LinkedList<Player> playersWithTnt = new LinkedList<>();

    private SpoilPlayerTNTFeature() {
        PluginManagerHelper.registerEvents(new TNTListener(), BedWars.plugin);
        Bukkit.getScheduler().runTaskTimer(BedWars.plugin, new ParticleTask(), 20, 1L);
    }

    public static void init() {
        var enable = BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_PERFORMANCE_SPOIL_TNT_PLAYERS);
        if (enable) {
            if (instance == null) {
                instance = new SpoilPlayerTNTFeature();
            }
        }
        MetricsManager.appendPie("tnt_spoil_enable", () -> String.valueOf(enable));
    }

    private static class ParticleTask implements Runnable {

        @Override
        public void run() {
            for (Player player : instance.playersWithTnt) {
                if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) return;
                BedWars.nms.playRedStoneDot(player);
            }
        }
    }

    private static class TNTListener implements Listener {

        @EventHandler
        public void onDie(PlayerKillEvent event) {
            instance.playersWithTnt.remove(event.getVictim());
        }

        @EventHandler
        public void onLeave(PlayerLeaveArenaEvent event) {
            instance.playersWithTnt.remove(event.getPlayer());
        }

        @EventHandler(ignoreCancelled = true)
        public void onPickUp(PlayerPickupItemEvent event) {
            if (event.getItem().getItemStack().getType() == Material.TNT) {
                IArena arena = Arena.getArenaByPlayer(event.getPlayer());
                if (arena == null || !arena.isPlayer(event.getPlayer()) || arena.isSpectator(event.getPlayer())) return;
                if (instance.playersWithTnt.contains(event.getPlayer())) return;
                instance.playersWithTnt.add(event.getPlayer());
            }
        }

        @EventHandler(ignoreCancelled = true)
        public void onDrop(PlayerDropItemEvent event) {
            if (event.getItemDrop().getItemStack().getType() == Material.TNT) {
                IArena arena = Arena.getArenaByPlayer(event.getPlayer());
                if (arena == null || !arena.isPlayer(event.getPlayer()) || arena.isSpectator(event.getPlayer())) return;
                if (!instance.playersWithTnt.contains(event.getPlayer())) return;
                if (event.getPlayer().getInventory().contains(Material.TNT)) return;
                instance.playersWithTnt.remove(event.getPlayer());
            }
        }

        @EventHandler(ignoreCancelled = true)
        public void onPlace(BlockPlaceEvent event) {
            ItemStack inHand = event.getItemInHand();
            IArena arena = Arena.getArenaByPlayer(event.getPlayer());
            if (arena == null || !arena.isPlayer(event.getPlayer()) || arena.isSpectator(event.getPlayer())) return;
            if (inHand.getType() == Material.TNT) {
                if (!instance.playersWithTnt.contains(event.getPlayer())) return;
                Bukkit.getScheduler().runTaskLater(BedWars.plugin, () -> {
                    if (!event.getPlayer().getInventory().contains(Material.TNT)) {
                        instance.playersWithTnt.remove(event.getPlayer());
                    }
                }, 1L);
            }
        }

        @EventHandler(ignoreCancelled = true)
        public void inventorySwitch(InventoryCloseEvent event) {
            Player player = (Player) event.getPlayer();
            IArena arena = Arena.getArenaByPlayer(player);

            // Ensure the player is in an arena, is not a spectator, and is indeed a player
            if (arena == null || !arena.isPlayer(player) || arena.isSpectator(player)) {
                return;
            }

            boolean hasTnt = player.getInventory().contains(Material.TNT);

            if (instance.playersWithTnt.contains(player)) {
                if (!hasTnt) {
                    instance.playersWithTnt.remove(player);
                }
            } else {
                if (hasTnt) {
                    instance.playersWithTnt.add(player);
                }
            }
        }
    }
}