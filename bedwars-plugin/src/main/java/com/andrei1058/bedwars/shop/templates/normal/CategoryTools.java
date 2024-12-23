package com.andrei1058.bedwars.shop.templates.normal;

import com.andrei1058.bedwars._fwextension.menus.templates.ElementsGroupTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.elements.TierTemplate;

import static com.andrei1058.bedwars._fwextension.materials.ExtMaterial.*;
import static com.andrei1058.bedwars._fwextension.shops.templates.ShopsTemplatesHelper.*;

class CategoryTools extends ElementsGroupTemplate {

    public void _read() {
        elements(
            xxxx, xxxxxxx, xxxx, xxxx, xxxx, xxxx, xxxx, xxxx, xxxx,
            ____, _______, ____, ____, ____, ____, ____, ____, ____,
            ____, pickaxe, ____, ____, ____, ____, ____, ____, ____,
            ____, _______, ____, ____, ____, ____, ____, ____, ____,
            xxxx, xxxxxxx, xxxx, xxxx, xxxx, xxxx, xxxx, xxxx, xxxx
        );
        super._read();
    }

    TierTemplate pickaxe = tier()
        .slot(s()
            .type(WOODEN_PICKAXE)
            .price(IRON, 10))
        .slot(s()
            .type(IRON_PICKAXE)
            .price(IRON, 10));
}
