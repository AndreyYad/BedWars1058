package com.andrei1058.bedwars.shop.templates.normal;

import com.andrei1058.bedwars._fwextension.menus.templates.ElementsGroupTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.elements.SlotTemplate;

import static com.andrei1058.bedwars._fwextension.materials.ExtMaterial.*;
import static com.andrei1058.bedwars._fwextension.shops.templates.ShopsTemplatesHelper.*;

class CategoryBlocks extends ElementsGroupTemplate {

    public void _read() {
        elements(
            xxxx, xxxx, xxxx, xxxxx, xxxxx, xxxxxx, xxxxxxxx, xxxx, xxxx,
            ____, ____, ____, _____, _____, ______, ________, ____, ____,
            ____, wool, clay, glass, stone, ladder, obsidian, ____, ____,
            ____, ____, ____, _____, _____, ______, ________, ____, ____,
            xxxx, xxxx, xxxx, xxxxx, xxxxx, xxxxxx, xxxxxxxx, xxxx, xxxx
        );
        super._read();
    }

    SlotTemplate wool = slot()
        .view(v()
            .type(WHITE_WOOL)
            .amount(16)
        )
        .price(
            IRON, 4
        );

    SlotTemplate clay = slot()
        .view(view()
            .type(ORANGE_TERRACOTTA)
            .amount(16))
        .price(
            IRON, 12);

    SlotTemplate glass = slot()
        .type(LIME_STAINED_GLASS)
        .amount(4)
        .price(IRON, 8);

    SlotTemplate stone = slot()
        .view(v()
            .type(END_STONE)
            .amount(12)
        )
        .currency(GOLD)
        .cost(4);

    SlotTemplate ladder = slot()
        .view(view()
            .type(LADDER)
            .amount(16))
        .currency(IRON)
        .cost(4);

    SlotTemplate obsidian = slot()
        .type(OBSIDIAN)
        .amount(4)
        .currency(EMERALD)
        .cost(4);
}
