
package com.andrei1058.bedwars.upgrades;

import com.andrei1058.bedwars.arena.team.ITeam;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/// из АПИ

public interface MenuContent {

    /**
     * Item that represent the item in the GUI.
     *
     * @return item.
     */
    ItemStack getDisplayItem(Player player, ITeam team);

    /**
     * Manage what to do on click.
     */
    void onClick(Player player, ClickType clickType, ITeam team);

    /**
     * Get identifier.
     *
     * @return content name.
     */
    String getName();
}
