package com.andrei1058.bedwars.shop.templates.ordinary;

import com.andrei1058.bedwars._fwextension.common.templates.ItemStackTemplate;
import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.ShopTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.slots.CategorySlotTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.slots.ItemSlotTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.andrei1058.bedwars.shop.templates.ordinary.OrdinaryPrice.OrdinaryCurrency.*;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrdinaryShopTemplate extends ShopTemplate {

    ///! переделать мб в init
    private OrdinaryShopTemplate( ) {
        super(5, 9);
    }

    {
        filler = new ElementTemplate() {{
            type = ExtMaterial.GRAY_STAINED_GLASS_PANE;
        }};


        var back = new CategorySlotTemplate() {{
            type = ExtMaterial.NETHER_STAR;
        }};

        var blocks = new CategorySlotTemplate() {{
            type = ExtMaterial.ORANGE_TERRACOTTA;
            associatedMenu = new ShopTemplate(5, 9) {{

                var wool = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.WHITE_WOOL;
                    amount = 16;

                    price = new OrdinaryPrice(IRON, 4);
                    itemToSale = new ItemStackTemplate(){{
                        type = ExtMaterial.WHITE_WOOL;
                        amount = 16;
                    }};
                }};

                var clay = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.ORANGE_TERRACOTTA;
                    amount = 16;

                    price = new OrdinaryPrice(IRON, 12);
                    itemToSale = new ItemStackTemplate(){{
                        type = ExtMaterial.ORANGE_TERRACOTTA;
                        amount = 16;
                    }};
                }};

                var glass = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.LIME_STAINED_GLASS;
                    amount = 4;

                    price = new OrdinaryPrice(IRON, 8);
                    itemToSale = new ItemStackTemplate(){{
                        type = ExtMaterial.LIME_STAINED_GLASS;
                        amount = 4;
                    }};
                }};

                var stone = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.END_STONE;
                    amount = 16;

                    price = new OrdinaryPrice(GOLD, 4);
                    itemToSale = new ItemStackTemplate(){{
                        type = ExtMaterial.END_STONE;
                        amount = 16;
                    }};
                }};

                var ladder = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.LADDER;
                    amount = 16;

                    price = new OrdinaryPrice(IRON, 4);
                    itemToSale = new ItemStackTemplate(){{
                        type = ExtMaterial.LADDER;
                        amount = 16;
                    }};
                }};

                var obsidian = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.OBSIDIAN;
                    amount = 4;

                    price = new OrdinaryPrice(EMERALD, 4);
                    itemToSale = new ItemStackTemplate(){{
                        type = ExtMaterial.OBSIDIAN;
                        amount = 4;
                    }};
                }};

                var iron3 = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.IRON_INGOT;
                    amount = 3;

                    price = new OrdinaryPrice(GOLD, 1);
                    itemToSale = new ItemStackTemplate() {{
                        type = ExtMaterial.IRON_INGOT;
                        amount = 3;
                    }};
                }};

                var iron18 = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.IRON_INGOT;
                    amount = 18;

                    price = new OrdinaryPrice(GOLD, 6);
                    itemToSale = new ItemStackTemplate() {{
                        type = ExtMaterial.IRON_INGOT;
                        amount = 18;
                    }};
                }};

                var iron27 = new ItemSlotTemplate<OrdinaryPrice>() {{
                    type = ExtMaterial.IRON_INGOT;
                    amount = 27;

                    price = new OrdinaryPrice(GOLD, 9);
                    itemToSale = new ItemStackTemplate() {{
                        type = ExtMaterial.IRON_INGOT;
                        amount = 27;
                    }};
                }};

                String ___ = "";
                String ____ = "";
                String _____ = "";
                String ______ = "";
                String _______ = "";
                String ________ = "";

                this.
                    e(back).e(____).e(____).e(_____).e(_____).e(______).e(________).e(______).e(______).
                    e(____).e(____).e(____).e(_____).e(_____).e(______).e(________).e(______).e(______).
                    e(____).e(wool).e(clay).e(glass).e(stone).e(ladder).e(obsidian).e(______).e(______).
                    e(____).e(____).e(____).e(_____).e(_____).e(______).e(________).e(______).e(______).
                    e(____).e(____).e(____).e(_____).e(_____).e(______).e(iron3   ).e(iron18).e(iron27);

                this.
                    e(back    ).e(________).e(________).e(________).e(________).e(________).e(________).e(________).e(________).
                    e(________).e(________).e(________).e(________).e(________).e(________).e(________).e(________).e(________).
                    e(________).e(wool    ).e(clay    ).e(glass   ).e(stone   ).e(ladder  ).e(obsidian).e(________).e(________).
                    e(________).e(________).e(________).e(________).e(________).e(________).e(________).e(________).e(________).
                    e(________).e(________).e(________).e(________).e(________).e(________).e(iron3   ).e(iron18  ).e(iron27  );

                elements(
                    back, ____, ____, _____, _____, ______, ________, ______, ______,
                    ____, ____, ____, _____, _____, ______, ________, ______, ______,
                    ____, wool, clay, glass, stone, ladder, obsidian, ______, ______,
                    ____, ____, ____, _____, _____, ______, ________, ______, ______,
                    ____, ____, ____, _____, _____, ______, iron3   , iron18, iron27
                    );

                var iron3___ = iron3;
                var iron18__ = iron18;
                var iron27__ = iron27;
                elements(
                    back    , ________, ________, ________, ________, ________, ________, ________, ________,
                    ________, ________, ________, ________, ________, ________, ________, ________, ________,
                    ________, wool    , clay    , glass   , stone   , ladder  , obsidian, ________, ________,
                    ________, ________, ________, ________, ________, ________, ________, ________, ________,
                    ________, ________, ________, ________, ________, ________, iron3___, iron18__, iron27__
                );
                
            }};
        }};

        var melee = new CategorySlotTemplate() {{
            type = ExtMaterial.GOLDEN_SWORD;
            associatedMenu = new ShopTemplate(5, 9) {{
                this.
                    e(back).e().e().e().e().e().e().e().e().

                    r().

                    e().e().e().e().e().e().e().e().e().

                    e().e().e().e().e().e().e().e().e().

                    r();
            }};
        }};

        var armor = new CategorySlotTemplate() {{
            type = ExtMaterial.CHAINMAIL_BOOTS;
            associatedMenu = new ShopTemplate(5, 9) {{
                this.
                    e(back).e().e().e().e().e().e().e().e().

                    r().

                    e().e().e().e().e().e().e().e().e().

                    e().e().e().e().e().e().e().e().e().

                    r();
            }};
        }};

        var tools = new CategorySlotTemplate() {{
            type = ExtMaterial.STONE_PICKAXE;
            associatedMenu = new ShopTemplate(5, 9) {{
                this.
                    e(back).e().e().e().e().e().e().e().e().

                    r().

                    e().e().e().e().e().e().e().e().e().

                    e().e().e().e().e().e().e().e().e().

                    r();
            }};
        }};

        var ranged = new CategorySlotTemplate() {{
            type = ExtMaterial.BOW;
            associatedMenu = new ShopTemplate(5, 9) {{
                this.
                    e(back).e().e().e().e().e().e().e().e().

                    r().

                    e().e().e().e().e().e().e().e().e().

                    e().e().e().e().e().e().e().e().e().

                    r();
            }};
        }};

        var potions = new CategorySlotTemplate() {{
            type = ExtMaterial.BREWING_STAND;
            associatedMenu = new ShopTemplate(5, 9) {{
                this.
                    e(back).e().e().e().e().e().e().e().e().

                    r().

                    e().e().e().e().e().e().e().e().e().

                    e().e().e().e().e().e().e().e().e().

                    r();
            }};
        }};

        var utility = new CategorySlotTemplate() {{
            type = ExtMaterial.TNT;
            associatedMenu = new ShopTemplate(5, 9) {{
                this.
                    e(back).e().e().e().e().e().e().e().e().

                    r().

                    e().e().e().e().e().e().e().e().e().

                    e().e().e().e().e().e().e().e().e().

                    r();
            }};
        }};


        this.
            e( ).e(blocks).e(melee).e(armor).e(tools).e(ranged).e(potions).e(utility).e( ).

            r(                                                                           ).
            
            e( ).e(      ).e(     ).e(     ).e(     ).e(      ).e(       ).e(       ).e( ).
            
            e( ).e(      ).e(     ).e(     ).e(     ).e(      ).e(       ).e(       ).e( ).

            r(                                                                            );
    }
}
