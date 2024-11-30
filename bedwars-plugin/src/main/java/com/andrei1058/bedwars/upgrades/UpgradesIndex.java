package com.andrei1058.bedwars.upgrades;

import com.google.common.collect.ImmutableMap;
import org.bukkit.entity.Player;

/// из АПИ

public interface UpgradesIndex {

    /**
     * Get menu name.
     */
    String getName();

    /**
     * Open this menu to a player.
     * Make sure to use {@link IBedWars.TeamUpgradesUtil#setWatchingGUI(Player)}
     *
     * @param player target player.
     */
    void open(Player player);

    /**
     * Add content to a menu.
     *
     * @param content content instance.
     * @param slot    where to put the content in the menu.
     * @return false if te given slot is in use.
     */
    boolean addContent(MenuContent content, int slot);

    /**
     *
     * @return total amount of tiers in upgrades
     */
    int countTiers();

    ImmutableMap<Integer, MenuContent> getMenuContentBySlot();
}
