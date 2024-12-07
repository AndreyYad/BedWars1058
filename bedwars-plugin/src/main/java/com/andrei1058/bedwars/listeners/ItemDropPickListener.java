package com.andrei1058.bedwars.listeners;

import com.andrei1058.bedwars.arena.GameState;
import com.andrei1058.bedwars.arena.IArena;
import com.andrei1058.bedwars.bukkitwrap.PluginManagerWrap;
import com.andrei1058.bedwars.events.player.PlayerGeneratorCollectEvent;
import com.andrei1058.bedwars.server.ServerType;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.AFKSystem;
import com.andrei1058.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/// из versionsupport_common

public class ItemDropPickListener {

    // 1.11 or older
    public static class PlayerDrop implements Listener {
        @EventHandler
        public void onDrop(PlayerDropItemEvent e) {
            if (manageDrop(e.getPlayer(), e.getItemDrop())) e.setCancelled(true);
        }
    }

    public static class EntityDrop implements Listener {
        @EventHandler
        public void onDrop(EntityDropItemEvent e){
            if (manageDrop(e.getEntity(), e.getItemDrop())) e.setCancelled(true);
        }
    }

    public static class EntityPickup implements Listener {
        @EventHandler
        public void onPickup(EntityPickupItemEvent e){
            if (managePickup(e.getItem(), e.getEntity())) e.setCancelled(true);
        }
    }

    public static class ArrowCollect implements Listener {
        @EventHandler
        public void onArrowPick(PlayerPickupArrowEvent e){
            if (Arena.isSpectating(e.getPlayer())) {
                e.setCancelled(true);
            }
        }
    }

    /**
     * @return true if event should be cancelled
     */
    private static boolean managePickup(Item item, LivingEntity player) {
        if (!(player instanceof Player)) return false;
        if (BedWars.getServerType() == ServerType.MULTIARENA) {
            //noinspection ConstantConditions
            if (player.getLocation().getWorld().getName().equalsIgnoreCase(BedWars.getLobbyWorld())) {
                return true;
            }
        }
        IArena a = Arena.getArenaByPlayer((Player) player);
        if (a == null) return false;
        if (!a.isPlayer((Player) player)) {
            return true;
        }
        if (a.getStatus() != GameState.playing) {
            return true;
        }
        if (a.getRespawnSessions().containsKey(player)) {
            return true;
        }
        if (item.getItemStack().getType() == Material.ARROW) {
            item.setItemStack(BedWars.nms.createItemStack(item.getItemStack().getType().toString(), item.getItemStack().getAmount(), (short) 0));
            return false;
        }

        if (item.getItemStack().getType().toString().equals("BED")) {
            item.remove();
            return true;
        } else if (item.getItemStack().hasItemMeta()) {
            //noinspection ConstantConditions
            if (item.getItemStack().getItemMeta().hasDisplayName()) {
                if (item.getItemStack().getItemMeta().getDisplayName().contains("custom")) {
                    Material material = item.getItemStack().getType();
                    ItemMeta itemMeta = new ItemStack(material).getItemMeta();

                    //Call ore pick up event
                    if (!AFKSystem.isPlayerAFK(((Player) player).getPlayer())){
                        PlayerGeneratorCollectEvent event = new PlayerGeneratorCollectEvent((Player) player, item, a);
                        PluginManagerWrap.callEvent(event);
                        if (event.isCancelled()) {
                            return true;
                        } else {
                            item.getItemStack().setItemMeta(itemMeta);
                        }
                    }else return true; //Cancel event if player is afk
                }
            }
        }
        return false;
    }

    /**
     * @return true to cancel the event.
     */
    private static boolean manageDrop(Entity player, Item item) {
        if (!(player instanceof Player)) return false;
        if (BedWars.getServerType() == ServerType.MULTIARENA) {
            //noinspection ConstantConditions
            if (player.getLocation().getWorld().getName().equalsIgnoreCase(BedWars.getLobbyWorld())) {
                return true;
            }
        }
        IArena a = Arena.getArenaByPlayer((Player) player);
        if (a == null) return false;

        if (!a.isPlayer((Player) player)) {
            return true;
        }

        if (a.getStatus() != GameState.playing) {
            return true;
        } else {
            ItemStack i = item.getItemStack();
            if (i.getType() == Material.COMPASS) {
                return true;
            }
        }

        return a.getRespawnSessions().containsKey(player);
    }
}
