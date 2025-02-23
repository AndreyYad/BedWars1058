package com.andrei1058.bedwars.shop.defaultrestore;

import com.andrei1058.bedwars.arena.GameState;
import com.andrei1058.bedwars.arena.IArena;
import com.andrei1058.bedwars.arena.team.ITeam;
import com.andrei1058.bedwars.arena.Arena;
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
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

/// из versionsupport_common

// Used to restore default swords and bows if they are removed from the inventory and you remain with a less powerful weapon of the same kind. 1.12-.
public class ShopItemRestoreListener {

    // 1.11 or older
    public static class PlayerDrop implements Listener {
        @EventHandler
        public void onDrop(PlayerDropItemEvent e) {
            if (manageDrop(e.getPlayer(), e.getItemDrop())) e.setCancelled(true);
        }
    }

    public static class EntityDrop implements Listener {
        @EventHandler
        public void onDrop(EntityDropItemEvent e) {
            if (manageDrop(e.getEntity(), e.getItemDrop())) e.setCancelled(true);
        }
    }

    public static class EntityPickup implements Listener {
        @EventHandler
        public void onDrop(EntityPickupItemEvent e) {
            if (managePickup(e.getItem(), e.getEntity())) e.setCancelled(true);
        }
    }

    /**
     * Remove the default swords if the picked item is more powerful.
     *
     * @return true to cancel the event
     */
    public static boolean managePickup(Item item, LivingEntity player) {
        if (!(player instanceof Player)) return false;
        if (Arena.getArenaByPlayer((Player) player) == null) return false;
        if (Arena.getArenaByPlayer((Player) player).getStatus() != GameState.playing) return false;
        if (!Arena.getArenaByPlayer((Player) player).isPlayer((Player) player)) return false;

        if (BedWars.nms.isSword(item.getItemStack())) {
            for (ItemStack is : ((Player) player).getInventory()) {
                if (is == null) continue;
                if (is.getType() == Material.AIR) continue;
                if (!BedWars.nms.isCustomBedWarsItem(is)) continue;
                if (BedWars.nms.getCustomData(is).equalsIgnoreCase("DEFAULT_ITEM")) {
                    ((Player) player).getInventory().remove(is);
                    ((Player) player).updateInventory();
                    return false; // function will only return false. default item should only be checked. access tools should be put in chests
                }
            }
        }
        return false;
    }

    /**
     * If the dropped sword/ bow is a default item and is more powerful
     * than the others in the inventory give it back.
     * <p>
     * If the player remains without a sword give it the swords from the default items.
     * If the player remains without a bow give it bows from the default items.
     *
     * @return true to cancel the event.
     */
    private static boolean manageDrop(Entity player, Item item) {
        if (!(player instanceof Player)) return false;
        if (Arena.getArenaByPlayer((Player) player) == null) return false;
        IArena a = Arena.getArenaByPlayer((Player) player);
        if (a.getStatus() != GameState.playing) return false;
        if (!a.isPlayer((Player) player)) return false;
        if (BedWars.nms.isCustomBedWarsItem(item.getItemStack())
                && BedWars.nms.getCustomData(item.getItemStack()).equalsIgnoreCase("DEFAULT_ITEM")
                && BedWars.nms.isSword(item.getItemStack())) {
            boolean hasSword = false;
            for (ItemStack is : ((Player) player).getInventory()) {
                if (is == null) continue;
                if (BedWars.nms.isSword(is)) {
                    if (BedWars.nms.getDamage(is) >= BedWars.nms.getDamage(item.getItemStack())) {
                        hasSword = true;
                        break;
                    }
                }
            }

            return !hasSword;
        } else {
            boolean sword = false;
            for (ItemStack is : ((Player) player).getInventory()) {
                if (is == null) continue;
                if (BedWars.nms.isSword(is)) {
                    sword = true;
                    break;
                }
            }
            if (!sword) a.getTeam((Player) player).defaultSword((Player) player, true);
        }
        return false;
    }


    public static class DefaultRestoreInvClose implements Listener {

        /**
         * If the player moves a default sword or bow into another inventory
         * and he remains with a less powerful weapon restore the lost one.
         */
        @EventHandler
        public void onInventoryClose(InventoryCloseEvent e) {
            if (e.getInventory().getType() == InventoryType.PLAYER) return;
            if (Arena.getArenaByPlayer((Player) e.getPlayer()) == null) return;
            IArena a = Arena.getArenaByPlayer((Player) e.getPlayer());
            if (a.getStatus() != GameState.playing) return;
            if (!a.isPlayer((Player) e.getPlayer())) return;

            boolean sword = false;
            for (ItemStack is : e.getPlayer().getInventory()) {
                if (is == null) continue;
                if (is.getType() == Material.AIR) continue;
                if (BedWars.nms.isSword(is)) sword = true;
            }

            if (!sword) {
                ITeam team = a.getTeam((Player) e.getPlayer());
                if (team != null && !a.isReSpawning((Player) e.getPlayer())) {
                    team.defaultSword((Player) e.getPlayer(), true);
                }
            }
        }
    }
}
