package com.andrei1058.bedwars.support.version.common;

import com.andrei1058.bedwars.server.VersionSupport;
import com.andrei1058.bedwars.listeners.Interact_1_13Plus;
import com.andrei1058.bedwars.listeners.ItemDropPickListener;
import com.andrei1058.bedwars.listeners.PlayerDropPick_1_11Minus;
import com.andrei1058.bedwars.listeners.SwapItem;
import com.andrei1058.bedwars.shop.defaultrestore.ShopItemRestoreListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/// из versionsupport_common

public class VersionCommon {

    public VersionCommon(VersionSupport versionSupport) {
            //noinspection ConstantConditions

            registerListeners(versionSupport.getPlugin(), new SwapItem(), new ItemDropPickListener.ArrowCollect());
            registerListeners(versionSupport.getPlugin(), new ShopItemRestoreListener.EntityDrop(), new Interact_1_13Plus(), new ItemDropPickListener.EntityDrop());
            registerListeners(versionSupport.getPlugin(), new ItemDropPickListener.EntityPickup(), new ShopItemRestoreListener.EntityPickup());
            registerListeners(versionSupport.getPlugin(), new ItemDropPickListener.PlayerDrop(), new ShopItemRestoreListener.PlayerDrop());
            registerListeners(versionSupport.getPlugin(), new ShopItemRestoreListener.DefaultRestoreInvClose());
    }

    private void registerListeners(Plugin plugin, Listener... listener) {
        for (Listener l : listener) {
            plugin.getServer().getPluginManager().registerEvents(l, plugin);
        }
    }
}
