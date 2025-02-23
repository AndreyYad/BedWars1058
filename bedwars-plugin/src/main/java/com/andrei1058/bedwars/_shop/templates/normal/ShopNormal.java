package com.andrei1058.bedwars._shop.templates.normal;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.ShopTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.elements.SlotTemplate;

import static com.andrei1058.bedwars._fwextension.materials.ExtMaterial.*;
import static com.andrei1058.bedwars._fwextension.menus.templates.MenusTemplatesHelper.*;
import static com.andrei1058.bedwars._fwextension.shops.templates.ShopsTemplatesHelper.slot;


public class ShopNormal extends ShopTemplate {

    public void read() {
        elements(
            base, blocks, melee, armor, tools, ranged, potions, utility, ________,
            ____, ______, _____, _____, _____, ______, _______, _______, ________,
            ____, ______, _____, _____, _____, ______, _______, _______, ________,
            ____, ______, _____, _____, _____, ______, _______, _______, ________,
            ____, ______, _____, _____, _____, ______, _______, _______, ironExch
        );

        super._read();
    }

    SlotTemplate ironExch = slot()
        .type(IRON_INGOT)
        .amount(3)
        .price(GOLD, 1);


    ElementTemplate base = navigationElementFor(this)
        .type(NETHER_STAR)
        .openedType(STONE);

    ElementTemplate armor = navigationElementFor(CategoryArmor.class)
        .type(GOLDEN_SWORD)
        .openedType(GOLDEN_SWORD);

    ElementTemplate blocks = navigationElementFor(CategoryBlocks.class)
        .type(ORANGE_TERRACOTTA)
        .openedType(ORANGE_TERRACOTTA);

    ElementTemplate melee = navigationElementFor(CategoryMelee.class)
        .type(GOLDEN_SWORD)
        .openedType(GOLDEN_SWORD);

    ElementTemplate potions = navigationElementFor(CategoryPotions.class)
        .type(BREWING_STAND)
        .openedType(BREWING_STAND);

    ElementTemplate ranged = navigationElementFor(CategoryRanged.class)
        .type(BOW)
        .openedType(BOW);

    ElementTemplate tools = navigationElementFor(CategoryTools.class)
        .type(STONE_PICKAXE)
        .openedType(STONE_PICKAXE);

    ElementTemplate utility = navigationElementFor(CategoryUtility.class)
        .type(TNT)
        .openedType(TNT);
}
