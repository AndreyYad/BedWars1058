
package com.andrei1058.bedwars.upgrades;

import com.andrei1058.bedwars.arena.team.ITeam;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

//! из АПИ

public interface UpgradeAction {

    /**
     * Apply action to team.
     */
    @Deprecated
    default void onBuy(ITeam bwt) {
        onBuy(null, bwt);
    }

    /**
     * Apply action to team.
     *
     * @param player buyer.
     * @param bwt    team receiver.
     */
    void onBuy(@Nullable Player player, ITeam bwt);
}
