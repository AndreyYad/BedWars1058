
package com.andrei1058.bedwars.server;

import com.andrei1058.bedwars.configuration.ConfigManager;
import org.bukkit.entity.Player;

//! из АПИ

public interface ISetupSession {

    /**
     * Get used world name.
     */
    String getWorldName();

    /**
     * Get player doing the setup.
     */
    Player getPlayer();

    /**
     * Get setup type.
     */
    SetupType getSetupType();

    /**
     * Get arena config.
     */
    ConfigManager getConfig();

    /**
     * Teleport player target world.
     */
    void teleportPlayer();

    /**
     * Close setup session.
     */
    void close();
}
