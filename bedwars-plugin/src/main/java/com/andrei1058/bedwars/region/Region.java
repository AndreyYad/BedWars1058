package com.andrei1058.bedwars.region;

import org.bukkit.Location;

/// из АПИ

public interface Region {

    /**
     * Check if location is in region.
     */
    boolean isInRegion(Location location);

    /**
     * Check if is a protected region.
     */
    boolean isProtected();
}
