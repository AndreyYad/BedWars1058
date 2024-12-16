package com.andrei1058.bedwars._fwextension.materials;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum ExtMaterial {

    EMPTY(Material.AIR),
    NETHER_STAR(),
    GRAY_STAINED_GLASS_PANE(),
    SNOWBALL(),
    CHEST(),
    ORANGE_TERRACOTTA(),
    WHITE_WOOL(),
    LIME_STAINED_GLASS(),
    END_STONE(),
    LADDER(),
    IRON_INGOT(),
    OBSIDIAN(),
    GOLDEN_SWORD(),
    STONE_SWORD(),
    IRON_SWORD(),
    DIAMOND_SWORD(),
    STICK(),
    FISHING_ROD(),
    CHAINMAIL_BOOTS(),
    IRON_BOOTS(),
    DIAMOND_BOOTS(),
    SHEARS(),
    STONE_PICKAXE(),
    WOODEN_PICKAXE(),
    IRON_PICKAXE(),
    GOLDEN_PICKAXE(),
    DIAMOND_PICKAXE(),
    BOW(),
    ARROW(),
    BREWING_STAND(),
    POTION(),
    TNT(),
    WATER_BUCKET(),
    EGG(),
    MILK_BUCKET(),
    SPONGE();

    private final Material origin;
    private final int customModelData;

    ExtMaterial(Material originMaterial, int customModelData) {
        this.origin = originMaterial;
        this.customModelData = customModelData;
    }
    ExtMaterial(Material originMaterial) {
        this(originMaterial, 0);
    }
    ExtMaterial() {
        try {
            origin = Material.valueOf(this.name());
            customModelData = 0;
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException("Для не ванильных материалов нужно указывать соответствующий ванильный");
        }
    }

    public boolean isEmpty() {
        return origin == Material.AIR;
    }
}
