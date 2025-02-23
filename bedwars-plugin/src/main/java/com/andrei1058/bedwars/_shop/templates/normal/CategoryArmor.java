package com.andrei1058.bedwars._shop.templates.normal;

import com.andrei1058.bedwars._fwextension.menus.templates.ElementsGroupTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.elements.SlotTemplate;
import org.bukkit.enchantments.Enchantment;

import static com.andrei1058.bedwars._fwextension.materials.ExtMaterial.*;
import static com.andrei1058.bedwars._fwextension.shops.templates.ShopsTemplatesHelper.*;

class CategoryArmor extends ElementsGroupTemplate {

    public void _read() {
        elements(
            xxxx, xxxxxxxxxxx, xxxxxxxxxxx, xxxx, xxxx, xxxx, xxxx, xxxx, xxxxxxxxx,
            ____, ___________, ___________, ____, ____, ____, ____, ____, _________,
            ____, stoneSword1, stoneSword2, ____, ____, ____, ____, ____, _________,
            ____, ___________, ___________, ____, ____, ____, ____, ____, someMelee,
            xxxx, xxxxxxxxxxx, xxxxxxxxxxx, xxxx, xxxx, xxxx, xxxx, xxxx, xxxxxxxxx
        );
        super._read();
    }

    SlotTemplate stoneSword1 = slot()
        .view(v()
            .type(STONE_SWORD)
        )
        .price(
            IRON, 10
        )
        .product(t()
            .type(STONE_SWORD)
            .enchant(Enchantment.KNOCKBACK, 1)
        );

    SlotTemplate stoneSword2 = slot()
        .type(STONE_SWORD)
        .price(IRON, 30)
        .product(t()
            .type(STONE_SWORD)
            .enchant(Enchantment.KNOCKBACK, 3));

    SlotTemplate someMelee = slot()
        .type(GOLDEN_SWORD)
        .price(GOLD, 10, EMERALD, 1)
        .product(SomeMelee.class);
}
