package com.andrei1058.bedwars._fwextension.common.templates;

import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public class ItemStackTemplate {
    ///! Доделать остальные поля
    protected ExtMaterial type = ExtMaterial.EMPTY;
    protected int amount = 0;
    protected ItemMeta meta;
}
