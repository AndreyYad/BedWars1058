package com.andrei1058.bedwars._fwextension.materials;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum ExtMaterial {

    EMPTY(Material.AIR),
    /// от балды написал пока что
    STONE(Material.STONE),
    MY_ITEM(Material.BEDROCK, 10);

    private final Material origin;
    private final int customModelData;

    ExtMaterial(Material originMaterial, int customModelData) {
        this.origin = originMaterial;
        this.customModelData = customModelData;
    }
    ExtMaterial(Material originMaterial) {
        this.origin = originMaterial;
        this.customModelData = 0;
    }

    public boolean isEmpty() {
        return origin == Material.AIR;
    }
}
