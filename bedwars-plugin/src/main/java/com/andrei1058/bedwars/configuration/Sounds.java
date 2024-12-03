package com.andrei1058.bedwars.configuration;


import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.arena.NextEvent;
import com.andrei1058.bedwars.configuration.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

import static com.andrei1058.bedwars.BedWars.plugin;
import static com.andrei1058.bedwars.configuration.ConfigPath.*;

public class Sounds {

    private static final ConfigManager sounds = new ConfigManager(plugin, "sounds", plugin.getDataFolder().getPath());

    /**
     * Load sounds configuration
     */
    private Sounds() {
    }

    public static void init() {
        YamlConfiguration yml = sounds.getYml();

        addDefSound("game-end", "ITEM_TRIDENT_THUNDER");
        addDefSound("rejoin-denied", "ENTITY_VILLAGER_NO");
        addDefSound("rejoin-allowed", "ENTITY_SLIME_JUMP");
        addDefSound("spectate-denied", "ENTITY_VILLAGER_NO");
        addDefSound("spectate-allowed", "ENTITY_SLIME_JUMP");
        addDefSound("join-denied", "ENTITY_VILLAGER_NO");
        addDefSound("join-allowed", "ENTITY_SLIME_JUMP");
        addDefSound("spectator-gui-click", "ENTITY_SLIME_JUMP");
        addDefSound(SOUNDS_COUNTDOWN_TICK, "ENTITY_CHICKEN_EGG");
        addDefSound(SOUNDS_COUNTDOWN_TICK_X + "5", "ENTITY_CHICKEN_EGG");
        addDefSound(SOUNDS_COUNTDOWN_TICK_X + "4", "ENTITY_CHICKEN_EGG");
        addDefSound(SOUNDS_COUNTDOWN_TICK_X + "3", "ENTITY_CHICKEN_EGG");
        addDefSound(SOUNDS_COUNTDOWN_TICK_X + "2", "ENTITY_CHICKEN_EGG");
        addDefSound(SOUNDS_COUNTDOWN_TICK_X + "1", "ENTITY_CHICKEN_EGG");
        addDefSound(SOUND_GAME_START, "BLOCK_SLIME_BLOCK_FALL");

        addDefSound(SOUNDS_KILL, "ENTITY_EXPERIENCE_ORB_PICKUP");

        addDefSound(SOUNDS_BED_DESTROY, "ENTITY_ENDER_DRAGON_GROWL");
        addDefSound(SOUNDS_BED_DESTROY_OWN, "ENTITY_WITHER_DEATH");
        addDefSound(SOUNDS_INSUFF_MONEY, "ENTITY_VILLAGER_NO");
        addDefSound(SOUNDS_BOUGHT, "ENTITY_VILLAGER_YES");

        addDefSound(NextEvent.BEDS_DESTROY.getSoundPath(), "ENTITY_ENDER_DRAGON_GROWL");
        addDefSound(NextEvent.DIAMOND_GENERATOR_TIER_II.getSoundPath(), "ENTITY_PLAYER_LEVELUP");
        addDefSound(NextEvent.DIAMOND_GENERATOR_TIER_III.getSoundPath(), "ENTITY_PLAYER_LEVELUP");
        addDefSound(NextEvent.EMERALD_GENERATOR_TIER_II.getSoundPath(), "ENTITY_GHAST_WARN");
        addDefSound(NextEvent.EMERALD_GENERATOR_TIER_III.getSoundPath(), "ENTITY_GHAST_WARN");
        addDefSound(NextEvent.ENDER_DRAGON.getSoundPath(), "ENTITY_ENDER_DRAGON_FLAP");

        addDefSound("player-re-spawn", "BLOCK_SLIME_BLOCK_FALL");
        addDefSound("arena-selector-open", "ENTITY_CHICKEN_EGG");
        addDefSound("stats-gui-open", "ENTITY_CHICKEN_EGG");
        addDefSound("trap-sound", "ENTITY_ENDERMAN_TELEPORT");
        addDefSound("shop-auto-equip", "ITEM_ARMOR_EQUIP_GENERIC");
        addDefSound("egg-bridge-block", "ENTITY_CHICKEN_EGG");
        addDefSound("ender-pearl-landed", "ENTITY_ENDERMAN_TELEPORT");
        addDefSound("pop-up-tower-build", "ENTITY_CHICKEN_EGG");
        yml.options().copyDefaults(true);

        // remove old paths
        yml.set("bought", null);
        yml.set("insufficient-money", null);
        yml.set("player-kill", null);
        yml.set("countdown", null);
        sounds.save();
    }

    private static Sound getSound(String path) {
        try {
            return Sound.valueOf(sounds.getString(path + ".sound"));
        } catch (Exception ex) {
            return Sound.ITEM_TRIDENT_THUNDER;
        }
    }

    public static void playSound(String path, List<Player> players) {
        if(path.equalsIgnoreCase("none")) return;
        final Sound sound = getSound(path);
        int volume = getSounds().getInt(path + ".volume");
        int pitch = getSounds().getInt(path + ".pitch");
        if (sound != null) {
            players.forEach(p -> p.playSound(p.getLocation(), sound, volume, pitch));
        }
    }

    /**
     * @return true if sound is valid and it was played.
     */
    public static boolean playSound(Sound sound, List<Player> players) {
        if (sound == null) return false;
        players.forEach(p -> p.playSound(p.getLocation(), sound, 1f, 1f));
        return true;
    }

    public static void playSound(String path, Player player) {
        final Sound sound = getSound(path);
        float volume = (float) getSounds().getYml().getDouble(path + ".volume");
        float pitch = (float) getSounds().getYml().getDouble(path + ".pitch");
        if (sound != null) player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public static ConfigManager getSounds() {
        return sounds;
    }

    private static void addDefSound(String path, String value) {
        // convert old paths
        if (getSounds().getYml().get(path) != null && getSounds().getYml().get(path + ".volume") == null) {
            String temp = getSounds().getYml().getString(path);
            getSounds().getYml().set(path, null);
            getSounds().getYml().set(path + ".sound", temp);
        }
        getSounds().getYml().addDefault(path + ".sound", value);
        getSounds().getYml().addDefault(path + ".volume", 1);
        getSounds().getYml().addDefault(path + ".pitch", 1);
    }

    public static  void playsoundArea(String path, Location location, float x, float y){
        final Sound sound = getSound(path);
        if (sound != null) location.getWorld().playSound(location, sound, x, y);
    }
}
