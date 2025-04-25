/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2021 Andrei Dascălu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Contact e-mail: andrew.dascalu@gmail.com
 */

package com.andrei1058.bedwars.maprestore.internal;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.configuration.ConfigPath;
import com.andrei1058.bedwars.api.server.ISetupSession;
import com.andrei1058.bedwars.api.server.RestoreAdapter;
import com.andrei1058.bedwars.api.server.ServerType;
import com.andrei1058.bedwars.api.util.FileUtil;
import com.andrei1058.bedwars.api.util.ZipFileUtil;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.arena.VoidChunkGenerator;
import com.andrei1058.bedwars.maprestore.internal.files.WorldZipper;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import java.io.IOException;
import java.nio.file.*;

import static com.andrei1058.bedwars.BedWars.config;
import static com.andrei1058.bedwars.BedWars.plugin;

@SuppressWarnings("CallToPrintStackTrace")
public class InternalAdapter extends RestoreAdapter {

    public static File backupFolder = new File(BedWars.plugin.getDataFolder() + "/Cache");
    public InternalAdapter(Plugin plugin) {
        super(plugin);
    }

    public static final String WORLD_PREFIX = "bedwars_";

    @Override
    public void onEnable(IArena a) {
        System.getLogger("IternalAdapter").log(System.Logger.Level.DEBUG, "onEnable");
        Bukkit.getScheduler().runTask(getOwner(), () -> {
            String worldName = a.getWorldName();
            if (Bukkit.getWorld(worldName) != null) {
                Bukkit.getScheduler().runTask(getOwner(), () -> {
                    World w = Bukkit.getWorld(worldName);
                    a.init(w);
                });
                return;
            }

            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                File bf = new File(backupFolder, worldName + ".zip"),
                        af = new File(Bukkit.getWorldContainer(), worldName);

                // 🔹 Папки для хранения сущностей
                File entitiesFolder = new File(af, "entities");
                File tempEntitiesFolder = new File(backupFolder, worldName + "_entities");

                // 🔹 1. Если папка `entities` существует, копируем её во временное место
                if (entitiesFolder.exists()) {
                    try {
                        copyFolder(entitiesFolder.toPath(), tempEntitiesFolder.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (bf.exists()) {
                    FileUtil.delete(af);
                }

                if (!bf.exists()) {
                    new WorldZipper(worldName, true);
                } else {
                    try {
                        ZipFileUtil.unzipFileIntoDirectory(bf, new File(Bukkit.getWorldContainer(), a.getWorldName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                deleteWorldTrash(worldName);

                Bukkit.getScheduler().runTask(plugin, () -> {
                    WorldCreator wc = new WorldCreator(worldName);
                    wc.generateStructures(false);
                    wc.generator(new VoidChunkGenerator());
                    World w = Bukkit.createWorld(wc);
                    if (w == null){
                        throw new IllegalStateException("World should not be null");
                    }
                    w.setKeepSpawnInMemory(true);
                    w.setAutoSave(false);

                    // 🔹 2. Восстанавливаем `entities`, если он был сохранен
                    if (tempEntitiesFolder.exists()) {
                        try {
                            copyFolder(tempEntitiesFolder.toPath(), entitiesFolder.toPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            });
        });
    }

    // 📂 Функция для копирования папок
    private void copyFolder(Path source, Path target) throws IOException {
        Files.walk(source).forEach(sourcePath -> {
            try {
                Path targetPath = target.resolve(source.relativize(sourcePath));
                if (Files.isDirectory(sourcePath)) {
                    Files.createDirectories(targetPath);
                } else {
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void onRestart(IArena a) {
        Bukkit.getScheduler().runTask(getOwner(), () -> {
            if (BedWars.getServerType() == ServerType.BUNGEE) {
                if (Arena.getGamesBeforeRestart() == 0) {
                    if (Arena.getArenas().isEmpty()) {
                        plugin.getLogger().info("Dispatching command: " + config.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_RESTART_CMD));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), config.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_RESTART_CMD));
                    }
                } else {
                    if (Arena.getGamesBeforeRestart() != -1) {
                        Arena.setGamesBeforeRestart(Arena.getGamesBeforeRestart() - 1);
                    }
                    Bukkit.unloadWorld(a.getWorldName(), false);
                    if (Arena.canAutoScale(a.getArenaName())) {
                        Bukkit.getScheduler().runTaskLater(plugin, () -> new Arena(a.getArenaName(), null), 80L);
                    }
                }
            } else {
                Bukkit.unloadWorld(a.getWorldName(), false);
                Bukkit.getScheduler().runTaskLater(plugin, () -> new Arena(a.getArenaName(), null), 80L);
            }
            if (!a.getWorldName().equals(a.getArenaName())) {
                deleteWorld(a.getWorldName());
            }
        });
    }

    @Override
    public void onDisable(IArena a) {
        if(BedWars.isShuttingDown()) {
            Bukkit.unloadWorld(a.getWorldName(), false);
            return;
        }
        Bukkit.getScheduler().runTask(getOwner(), () -> Bukkit.unloadWorld(a.getWorldName(), false));
    }

    @Override
    public void onSetupSessionStart(ISetupSession s) {
        System.getLogger("IternalAdapte").log(System.Logger.Level.DEBUG, "onSetupSessionStart");
        System.getLogger("Setup").log(System.Logger.Level.INFO, "start");
        Bukkit.getScheduler().runTaskAsynchronously(getOwner(), () -> {
            String worldName = s.getWorldName();
            File bf = new File(backupFolder, worldName + ".zip");
            File af = new File(Bukkit.getWorldContainer(), worldName);

            // 🔹 Восстанавливаем папку entities перед загрузкой мира
            File backupEntities = new File("backups/" + worldName + "/entities");
            File worldEntities = new File(Bukkit.getWorldContainer(), worldName + "/entities");
            if (backupEntities.exists()) {
                try {
                    FileUtils.copyDirectory(backupEntities, worldEntities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bf.exists()) {
                FileUtil.delete(af);
                try {
                    ZipFileUtil.unzipFileIntoDirectory(bf, new File(Bukkit.getWorldContainer(), worldName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            WorldCreator wc = new WorldCreator(worldName);
            wc.generator(new VoidChunkGenerator());
            wc.generateStructures(false);
            Bukkit.getScheduler().runTask(getOwner(), () -> {
                try {
                    File level = new File(Bukkit.getWorldContainer(), worldName + "/region");
                    if (level.exists()) {
                        s.getPlayer().sendMessage(ChatColor.GREEN + "Loading " + worldName + " from Bukkit worlds container.");
                        deleteWorldTrash(worldName);
                        World w = Bukkit.createWorld(wc);
                        w.setKeepSpawnInMemory(true);
                    } else {
                        try {
                            s.getPlayer().sendMessage(ChatColor.GREEN + "Creating a new void map: " + worldName);
                            World w = Bukkit.createWorld(wc);
                            w.setKeepSpawnInMemory(true);
                            Bukkit.getScheduler().runTaskLater(plugin, s::teleportPlayer, 20L);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            s.close();
                        }
                        return;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    s.close();
                    return;
                }
                Bukkit.getScheduler().runTaskLater(plugin, s::teleportPlayer, 20L);
            });
        });
    }

    @Override
    public void onSetupSessionClose(ISetupSession s) {
        Bukkit.getScheduler().runTask(getOwner(), () -> {
            World world = Bukkit.getWorld(s.getWorldName());
            if (world != null) {
                world.save();
            }

            // 🔹 Сохраняем папку entities перед выгрузкой мира
            File worldEntities = new File(Bukkit.getWorldContainer(), s.getWorldName() + "/entities");
            File backupEntities = new File("backups/" + s.getWorldName() + "/entities");
            try {
                if (worldEntities.exists()) {
                    FileUtils.copyDirectory(worldEntities, backupEntities);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Bukkit.unloadWorld(s.getWorldName(), true);
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> new WorldZipper(s.getWorldName(), true));
        });
    }


    @Override
    public boolean isWorld(String name) {
        return new File(Bukkit.getWorldContainer(), name + "/region").exists();
    }

    @Override
    public void deleteWorld(String name) {
        Bukkit.getScheduler().runTaskAsynchronously(getOwner(), () -> {
            try {
                FileUtils.deleteDirectory(new File(Bukkit.getWorldContainer(), name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void cloneArena(String name1, String name2) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                FileUtils.copyDirectory(new File(Bukkit.getWorldContainer(), name1), new File(Bukkit.getWorldContainer(), name2));
                deleteWorldTrash(name2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public List<String> getWorldsList() {
        List<String> worlds = new ArrayList<>();
        File dir = Bukkit.getWorldContainer();
        if (dir.exists()) {
            File[] fls = dir.listFiles();
            for (File fl : Objects.requireNonNull(fls)) {
                if (fl.isDirectory()) {
                    File dat = new File(fl.getName() + "/region");
                    if (dat.exists() && !fl.getName().startsWith("bw_temp")) {
                        worlds.add(fl.getName());
                    }
                }
            }
        }
        return worlds;
    }

    @Override
    public void convertWorlds() {
        File dir = new File(plugin.getDataFolder(), "/Arenas");
        if (dir.exists()) {
            List<File> files = new ArrayList<>();
            File[] fls = dir.listFiles();
            for (File fl : Objects.requireNonNull(fls)) {
                if (fl.isFile()) {
                    if (fl.getName().contains(".yml")) {
                        files.add(fl);
                    }
                }
            }

            // lowerCase arena names - new 1.14 standard
            File folder, newName;

            List<File> toRemove = new ArrayList<>(), toAdd = new ArrayList<>();
            for (File file : files) {
                if (!file.getName().equals(file.getName().toLowerCase())) {
                    newName = new File(dir.getPath() + "/" + file.getName().toLowerCase());
                    if (!file.renameTo(newName)) {
                        toRemove.add(file);
                        BedWars.plugin.getLogger().severe("Could not rename " + file.getName() + " to " + file.getName().toLowerCase() + "! Please do it manually!");
                    } else {
                        toAdd.add(newName);
                        toRemove.add(file);
                    }
                    folder = new File(plugin.getServer().getWorldContainer(), file.getName().replace(".yml", ""));
                    if (folder.exists()) {
                        if (!folder.getName().equals(folder.getName().toLowerCase())) {
                            if (!folder.renameTo(new File(plugin.getServer().getWorldContainer().getPath() + "/" + folder.getName().toLowerCase()))) {
                                BedWars.plugin.getLogger().severe("Could not rename " + folder.getName() + " folder to " + folder.getName().toLowerCase() + "! Please do it manually!");
                                toRemove.add(file);
                                return;
                            }
                        }
                    }
                }
            }

            for (File f : toRemove) {
                files.remove(f);
            }

            files.addAll(toAdd);
        }
        Bukkit.getScheduler().runTaskAsynchronously(getOwner(), () -> {
            File[] files = Bukkit.getWorldContainer().listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f != null && f.isDirectory()) {
                        if (f.getName().contains("bw_temp_")) {
                            deleteWorld(f.getName());
                        }
                    }
                }
            }
        });
    }

    @Override
    public String getDisplayName() {
        return "Internal Restore Adapter";
    }

    private void deleteWorldTrash(String world) {
        for (File f : new File[]{new File(Bukkit.getWorldContainer(), world + "/level.dat"),
                new File(Bukkit.getWorldContainer(), world + "/level.dat_mcr"),
                new File(Bukkit.getWorldContainer(), world + "/level.dat_old"),
                new File(Bukkit.getWorldContainer(), world + "/session.lock"),
                new File(Bukkit.getWorldContainer(), world + "/uid.dat")}) {
            if (f.exists()) {
                if (!f.delete()) {
                    getOwner().getLogger().warning("Could not delete: " + f.getPath());
                    getOwner().getLogger().warning("This may cause issues!");
                }
            }
        }
    }
}