
package com.andrei1058.bedwars.arena.shop;

import com.andrei1058.bedwars.arena.IArena;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

//! из АПИ

public interface IBuyItem {

    /**
     * Check if tier is loaded
     */
    boolean isLoaded();

    /**
     * Give content to a player
     */
    void give(Player player, IArena arena);

    /**
     * Get upgrade identifier.
     * Used to remove old tier items.
     */
    String getUpgradeIdentifier();

    ItemStack getItemStack();

    void setItemStack(ItemStack itemStack);

    boolean isAutoEquip();

    void setAutoEquip(boolean autoEquip);

    boolean isPermanent();

    void setPermanent(boolean permanent);

    boolean isUnbreakable();

    void setUnbreakable(boolean permanent);
}
