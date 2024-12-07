package com.andrei1058.bedwars.bukkitwrap;

import com.andrei1058.bedwars.bukkitwrap.methods.SimplePluginManagerMethods;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;

@SuppressWarnings("unused")
public class PluginManagerWrap {
    private final static PluginManager pluginManager = Bukkit.getPluginManager();


    /**
     * Подмена логики SimplePluginManager на ее копию с моими изменениями
     */
    public static void registerEvents(@NotNull Listener listener, @NotNull Plugin plugin) {
        SimplePluginManagerMethods.registerEvents(listener, plugin);
    }

    /**  ↓ ↓ ↓ Статичные адаптеры к публичным методам PluginManager ↓ ↓ ↓ */

    public static void registerInterface(@NotNull Class<? extends PluginLoader> loader) throws IllegalArgumentException {
        pluginManager.registerInterface(loader);
    }
    @Nullable
    public static Plugin getPlugin(@NotNull String name) {
        return pluginManager.getPlugin(name);
    }
    @NotNull
    public static Plugin[] getPlugins() {
        return pluginManager.getPlugins();
    }
    public static boolean isPluginEnabled(@NotNull String name) {
        return pluginManager.isPluginEnabled(name);
    }
    public static boolean isPluginEnabled(@Nullable Plugin plugin) {
        return pluginManager.isPluginEnabled(plugin);
    }
    @Nullable
    public static Plugin loadPlugin(@NotNull File file) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException {
        return pluginManager.loadPlugin(file);
    }
    @NotNull
    public static Plugin[] loadPlugins(@NotNull File directory) {
        return pluginManager.loadPlugins(directory);
    }
    public static void disablePlugins() {
        pluginManager.disablePlugins();
    }
    public static void clearPlugins() {
        pluginManager.clearPlugins();
    }
    public static void callEvent(@NotNull Event event) throws IllegalStateException {
        pluginManager.callEvent(event);
    }
    public static void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull Plugin plugin) {
        pluginManager.registerEvent(event, listener, priority, executor, plugin);
    }
    public static void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull Plugin plugin, boolean ignoreCancelled) {
        pluginManager.registerEvent(event, listener, priority, executor, plugin, ignoreCancelled);
    }
    public static void enablePlugin(@NotNull Plugin plugin) {
        pluginManager.enablePlugin(plugin);
    }
    public static void disablePlugin(@NotNull Plugin plugin) {
        pluginManager.disablePlugin(plugin);
    }
    @Nullable
    public static Permission getPermission(@NotNull String name) {
        return pluginManager.getPermission(name);
    }
    public static void addPermission(@NotNull Permission perm) {
        pluginManager.addPermission(perm);
    }
    public static void removePermission(@NotNull Permission perm) {
        pluginManager.removePermission(perm);
    }
    public static void removePermission(@NotNull String name) {
        pluginManager.removePermission(name);
    }
    @NotNull
    public static Set<Permission> getDefaultPermissions(boolean op) {
        return pluginManager.getDefaultPermissions(op);
    }
    public static void recalculatePermissionDefaults(@NotNull Permission perm) {
        pluginManager.recalculatePermissionDefaults(perm);
    }
    public static void subscribeToPermission(@NotNull String permission, @NotNull Permissible permissible) {
        pluginManager.subscribeToPermission(permission, permissible);
    }
    public static void unsubscribeFromPermission(@NotNull String permission, @NotNull Permissible permissible) {
        pluginManager.unsubscribeFromPermission(permission, permissible);
    }
    @NotNull
    public static Set<Permissible> getPermissionSubscriptions(@NotNull String permission) {
        return pluginManager.getPermissionSubscriptions(permission);
    }
    public static void subscribeToDefaultPerms(boolean op, @NotNull Permissible permissible) {
        pluginManager.subscribeToDefaultPerms(op, permissible);
    }
    public static void unsubscribeFromDefaultPerms(boolean op, @NotNull Permissible permissible) {
        pluginManager.unsubscribeFromDefaultPerms(op, permissible);
    }
    @NotNull
    public static Set<Permissible> getDefaultPermSubscriptions(boolean op) {
        return pluginManager.getDefaultPermSubscriptions(op);
    }
    @NotNull
    public static Set<Permission> getPermissions() {
       return pluginManager.getPermissions();
    }
    public static boolean useTimings() {
        return pluginManager.useTimings();
    }
}
