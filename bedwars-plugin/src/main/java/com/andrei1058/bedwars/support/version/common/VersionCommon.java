package com.andrei1058.bedwars.support.version.common;

import com.andrei1058.bedwars.bukkitwrap.PluginManagerWrap;
import com.andrei1058.bedwars.server.VersionSupport;
import com.andrei1058.bedwars.listeners.Interact_1_13Plus;
import com.andrei1058.bedwars.listeners.ItemDropPickListener;
import com.andrei1058.bedwars.listeners.SwapItem;
import com.andrei1058.bedwars.shop.defaultrestore.ShopItemRestoreListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/// из versionsupport_common

public class VersionCommon {

    public VersionCommon(VersionSupport versionSupport) {
        registerListeners(versionSupport.getPlugin(), new SwapItem(), new ItemDropPickListener.ArrowCollect());
        registerListeners(versionSupport.getPlugin(), new ShopItemRestoreListener.EntityDrop(), new Interact_1_13Plus(), new ItemDropPickListener.EntityDrop());
        registerListeners(versionSupport.getPlugin(), new ItemDropPickListener.EntityPickup(), new ShopItemRestoreListener.EntityPickup());
        /// разобраться почему для PlayerDrop это закомментили, а самих их подключали, хотя они и 1.11 or older
        // 1.12 drop listeners
        //if (versionSupport.getVersion() == 5){
        // common
        registerListeners(versionSupport.getPlugin(), new ItemDropPickListener.PlayerDrop(), new ShopItemRestoreListener.PlayerDrop());
        registerListeners(versionSupport.getPlugin(), new ShopItemRestoreListener.DefaultRestoreInvClose());
    }

    private void registerListeners(Plugin plugin, Listener... listener) {
        for (Listener l : listener) {
            PluginManagerWrap.registerEvents(l, plugin);
        }
    }
}
