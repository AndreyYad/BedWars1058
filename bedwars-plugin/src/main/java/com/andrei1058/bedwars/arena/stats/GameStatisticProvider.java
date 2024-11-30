package com.andrei1058.bedwars.arena.stats;

import com.andrei1058.bedwars.language.Language;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

/// из АПИ

public interface GameStatisticProvider<T extends GameStatistic<?>> {

    /**
     * Unique statistic identifier.
     */
    String getIdentifier();

    /**
     * Plugin provider.
     * @return statistic owner.
     */
    Plugin getOwner();

    /**
     * Default value used when initializing game stats.
     */
    T getDefault();

    /**
     * Display value for undetermined values.
     * @param language desired translation.
     */
    String getVoidReplacement(@Nullable Language language);
}
