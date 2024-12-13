package com.andrei1058.bedwars.shop;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.configuration.ConfigManager;
import com.andrei1058.bedwars.configuration.ConfigPath;
import com.andrei1058.bedwars.language.Messages;
import com.andrei1058.bedwars.shop.listeners.*;
import com.andrei1058.bedwars.shop.main.QuickBuyButton;
import com.andrei1058.bedwars.shop.main.ShopCategory;
import com.andrei1058.bedwars.shop.main.ShopIndex;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;

@SuppressWarnings("WeakerAccess")
public class ShopManager extends ConfigManager {

    public static ShopIndex shop;

    public ShopManager() {
        super(BedWars.plugin, "shop", BedWars.plugin.getDataFolder().getPath());
        saveDefaults();
        loadShop();
        registerListeners();
    }

    private void saveDefaults() {
        getYml().options().header("Shop with quick buy and tiers");

        //quick buy
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_MATERIAL, "NETHER_STAR");
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_AMOUNT, 1);
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_DATA, 0);
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_ENCHANTED, false);

        getYml().addDefault(ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_MATERIAL, "RED_STAINED_GLASS_PANE");
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_AMOUNT, 1);
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_DATA, 4);
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_ENCHANTED, false);

        //separator
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_MATERIAL, "GRAY_STAINED_GLASS_PANE");
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_AMOUNT, 1);
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_DATA, 7);
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_ENCHANTED, false);

        getYml().addDefault(ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_MATERIAL, "GREEN_STAINED_GLASS_PANE");
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_AMOUNT, 1);
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_DATA, 13);
        getYml().addDefault(ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_ENCHANTED, false);

        //specials
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_SILVERFISH_ENABLE, true);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_SILVERFISH_MATERIAL, "SNOWBALL");
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_SILVERFISH_DATA, 0);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_SILVERFISH_HEALTH, 8.0);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_SILVERFISH_DAMAGE, 4.0);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_SILVERFISH_SPEED, 0.25);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_SILVERFISH_DESPAWN, 15);

        getYml().addDefault(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_ENABLE, true);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_MATERIAL, "HORSE_SPAWN_EGG");
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_DATA, 0);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_HEALTH, 100.0);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_DESPAWN, 240);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_SPEED, 0.25);

        getYml().addDefault(ConfigPath.SHOP_SPECIAL_TOWER_ENABLE, true);
        getYml().addDefault(ConfigPath.SHOP_SPECIAL_TOWER_MATERIAL, "CHEST");

        if (isFirstTime()) {
            //quick buy defaults
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element1.path", "blocks-category.category-content.wool");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element1.slot", 19);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element2.path", "melee-category.category-content.stone-sword");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element2.slot", 20);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element3.path", "armor-category.category-content.chainmail");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element3.slot", 21);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element4.path", "ranged-category.category-content.bow1");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element4.slot", 23);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element5.path", "potions-category.category-content.speed-potion");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element5.slot", 24);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element6.path", "utility-category.category-content.tnt");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element6.slot", 25);

            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element7.path", "blocks-category.category-content.wood");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element7.slot", 28);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element8.path", "melee-category.category-content.iron-sword");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element8.slot", 29);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element9.path", "armor-category.category-content.iron-armor");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element9.slot", 30);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element10.path", "tools-category.category-content.shears");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element10.slot", 31);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element11.path", "ranged-category.category-content.arrow");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element11.slot", 32);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element12.path", "potions-category.category-content.jump-potion");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element12.slot", 33);
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element13.path", "utility-category.category-content.water-bucket");
            getYml().addDefault(ConfigPath.SHOP_QUICK_DEFAULTS_PATH + ".element13.slot", 34);
        }

        //save default shop categories if the file was just generated
        //so the user can remove categories or add new ones
        if (isFirstTime()) {
            //BLOCKS CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, 1, "ORANGE_TERRACOTTA", 1, 1, false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "wool", 19, "tier1",
                    "WHITE_WOOL", 0, 16, false, 4, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "wool", "tier1", "wool", "WHITE_WOOL",
                    0, 16, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "clay", 20, "tier1",
                    "ORANGE_TERRACOTTA", 1, 16, false, 12, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "clay", "tier1", "clay", "ORANGE_TERRACOTTA",
                    1, 16, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "glass", 21, "tier1",
                    "GLASS", 0, 4, false, 12, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "glass", "tier1", "glass", "GLASS",
                    0, 4, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "stone", 22, "tier1",
                    "END_STONE", 0, 16, false, 24, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "stone", "tier1", "stone", "END_STONE",
                    0, 16, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "ladder", 23, "tier1",
                    "LADDER", 0, 16, false, 4, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "ladder", "tier1", "ladder", "LADDER",
                    0, 16, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "wood", 24, "tier1",
                    "OAK_WOOD", 0, 16, false, 4, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "wood", "tier1", "wood", "OAK_WOOD",
                    0, 16, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "obsidian", 25, "tier1",
                    "OBSIDIAN", 0, 4, false, 4, "emerald", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "obsidian", "tier1", "obsidian", "OBSIDIAN",
                    0, 4, "", "", "", false);
            //

            //MELEE CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_MELEE, 2, "GOLDEN_SWORD", 0, 1, false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "stone-sword", 19, "tier1",
                    "STONE_SWORD", 0, 1, false, 10, "iron", false, false, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "stone-sword", "tier1", "sword", "STONE_SWORD",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "iron-sword", 20, "tier1",
                    "IRON_SWORD", 0, 1, false, 7, "gold", false, false, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "iron-sword", "tier1", "sword", "IRON_SWORD",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "diamond-sword", 21, "tier1",
                    "DIAMOND_SWORD", 0, 1, false, 4, "emerald", false, false, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "diamond-sword", "tier1", "sword", "DIAMOND_SWORD",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "stick", 22, "tier1",
                    "STICK", 0, 1, true, 10, "gold", false, false, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "stick", "tier1", "stick", "STICK",
                    0, 1, "KNOCKBACK 1", "", "", false);

            //ARMOR CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, 3, "CHAINMAIL_BOOTS", 0, 1, false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "chainmail", 19, "tier1",
                    "CHAINMAIL_BOOTS", 0, 1, false, 40, "iron", true, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "chainmail", "tier1", "boots", "CHAINMAIL_BOOTS",
                    0, 1, "", "", "", true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "chainmail", "tier1", "leggings", "CHAINMAIL_LEGGINGS",
                    0, 1, "", "", "", true);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "iron-armor", 20, "tier1",
                    "IRON_BOOTS", 0, 1, false, 12, "gold", true, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "iron-armor", "tier1", "boots", "IRON_BOOTS",
                    0, 1, "", "", "", true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "iron-armor", "tier1", "leggings", "IRON_LEGGINGS",
                    0, 1, "", "", "", true);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "diamond-armor", 21, "tier1",
                    "DIAMOND_BOOTS", 0, 1, false, 6, "emerald", true, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "diamond-armor", "tier1", "boots", "DIAMOND_BOOTS",
                    0, 1, "", "", "", true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "diamond-armor", "tier1", "leggings", "DIAMOND_LEGGINGS",
                    0, 1, "", "", "", true);

            //TOOLS CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, 4, "STONE_PICKAXE", 0, 1, false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "shears", 19, "tier1",
                    "SHEARS", 0, 1, false, 20, "iron", true, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "shears", "tier1", "shears", "SHEARS",
                    0, 1, "", "", "", false);

            //pickaxe
            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", 20, "tier1",
                    "WOODEN_PICKAXE", 0, 1, false, 10, "iron", true, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", "tier1", "wooden-pickaxe", "WOODEN_PICKAXE",
                    0, 1, "", "", "", false);
            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", 20, "tier2",
                    "IRON_PICKAXE", 0, 1, true, 10, "iron", true, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", "tier2", "iron-pickaxe", "IRON_PICKAXE",
                    0, 1, "DIG_SPEED 2", "", "", false);
            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", 20, "tier3",
                    "GOLDEN_PICKAXE", 0, 1, true, 3, "gold", true, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", "tier3", "gold-pickaxe", "GOLDEN_PICKAXE",
                    0, 1, "DIG_SPEED 3,DAMAGE_ALL 2", "", "", false);
            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", 20, "tier4",
                    "DIAMOND_PICKAXE", 0, 1, true, 6, "gold", true, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", "tier4", "diamond-pickaxe", "DIAMOND_PICKAXE",
                    0, 1, "DIG_SPEED 3", "", "", false);

            //axe
            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", 21, "tier1",
                    "WOODEN_AXE", 0, 1, false, 10, "iron", true, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", "tier1", "wooden-axe", "WOODEN_AXE",
                    0, 1, "DIG_SPEED 1", "", "", false);
            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", 21, "tier2",
                    "IRON_AXE", 0, 1, true, 10, "iron", true, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", "tier2", "iron-axe", "IRON_AXE",
                    0, 1, "DIG_SPEED 1", "", "", false);
            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", 21, "tier3",
                    "GOLDEN_AXE", 0, 1, true, 3, "gold", true, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", "tier3", "gold-axe", "GOLDEN_AXE",
                    0, 1, "DIG_SPEED 2", "", "", false);
            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", 21, "tier4",
                    "DIAMOND_AXE", 0, 1, true, 6, "gold", true, true);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", "tier4", "diamond-axe", "DIAMOND_AXE",
                    0, 1, "DIG_SPEED 3", "", "", false);

            //RANGED CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_RANGED, 5, "BOW", 0, 1, false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "arrow", 19, "tier1",
                    "ARROW", 0, 8, false, 2, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "arrow", "tier1", "arrows", "ARROW",
                    0, 8, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow1", 20, "tier1",
                    "BOW", 0, 1, false, 12, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow1", "tier1", "bow", "BOW",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow2", 21, "tier1",
                    "BOW", 0, 1, true, 24, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow2", "tier1", "bow", "BOW",
                    0, 1, "ARROW_DAMAGE 1", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow3", 22, "tier1",
                    "BOW", 0, 1, true, 6, "emerald", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow3", "tier1", "bow", "BOW",
                    0, 1, "ARROW_DAMAGE 1,ARROW_KNOCKBACK 1", "", "", false);

            //POTIONS CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, 6, "BREWING_STAND", 0, 1, false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "jump-potion", 20, "tier1",
                    "POTION", 0, 1, false, 1, "emerald", false, false);
            addBuyPotion(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "jump-potion", "tier1", "jump", "POTION",
                    0, 1, "", "JUMP 45 5", "Jump Potion");

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "speed-potion", 19, "tier1",
                    "POTION", 0, 1, false, 1, "emerald", false, false);
            addBuyPotion(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "speed-potion", "tier1", "speed", "POTION",
                    0, 1, "", "SPEED 45 2", "Speed Potion");

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "invisibility", 21, "tier1",
                    "POTION", 0, 1, false, 2, "emerald", false, false);
            addBuyPotion(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "invisibility", "tier1", "invisibility", "POTION",
                    0, 1, "", "INVISIBILITY 30 1", "Invisibility Potion");

            //UTILITY CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, 7, "TNT", 0, 1, false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "golden-apple", 19, "tier1",
                    "GOLDEN_APPLE", 0, 1, false, 3, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "golden-apple", "tier1", "apple", "GOLDEN_APPLE",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "bedbug", 20, "tier1",
                    "SNOWBALL", 0, 1, false, 40, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "bedbug", "tier1", "bedbug", "SNOWBALL",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "dream-defender", 21, "tier1",
                    "HORSE_SPAWN_EGG", 0, 1, false, 120, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "dream-defender", "tier1", "defender", "HORSE_SPAWN_EGG",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "fireball", 22, "tier1",
                    "FIRE_CHARGE", 0, 1, false, 40, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "fireball", "tier1", "fireball", "FIRE_CHARGE",
                    0, 1, "", "", "Fireball", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "tnt", 23, "tier1",
                    "TNT", 0, 1, false, 4, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "tnt", "tier1", "tnt", "TNT",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "ender-pearl", 24, "tier1",
                    "ENDER_PEARL", 0, 1, false, 4, "emerald", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "ender-pearl", "tier1", "ender-pearl", "ENDER_PEARL",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "water-bucket", 25, "tier1",
                    "WATER_BUCKET", 0, 1, false, 4, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "water-bucket", "tier1", "water-bucket", "WATER_BUCKET",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "bridge-egg", 28, "tier1",
                    "EGG", 0, 1, false, 3, "emerald", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "bridge-egg", "tier1", "egg", "EGG",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "magic-milk", 29, "tier1",
                    "MILK_BUCKET", 0, 1, false, 4, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "magic-milk", "tier1", "milk", "MILK_BUCKET",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "sponge", 30, "tier1",
                    "SPONGE", 0, 1, false, 3, "gold", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "sponge", "tier1", "sponge", "SPONGE",
                    0, 1, "", "", "", false);

            adCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "tower", 31, "tier1",
                    "CHEST", 0, 1, false, 24, "iron", false, false);
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "tower", "tier1", "tower", "CHEST",
                    0, 1, "", "", "", false);

        }

        if (getYml().get(ConfigPath.SHOP_PATH_CATEGORY_ARMOR + ConfigPath.SHOP_CATEGORY_CONTENT_PATH + ".diamond-armor") != null) {
            getYml().addDefault(ConfigPath.SHOP_PATH_CATEGORY_ARMOR + ConfigPath.SHOP_CATEGORY_CONTENT_PATH + ".diamond-armor" + "." + ConfigPath.SHOP_CATEGORY_CONTENT_WEIGHT, 2);
        }
        if (getYml().get(ConfigPath.SHOP_PATH_CATEGORY_ARMOR + ConfigPath.SHOP_CATEGORY_CONTENT_PATH + ".iron-armor") != null) {
            getYml().addDefault(ConfigPath.SHOP_PATH_CATEGORY_ARMOR + ConfigPath.SHOP_CATEGORY_CONTENT_PATH + ".iron-armor" + "." + ConfigPath.SHOP_CATEGORY_CONTENT_WEIGHT, 1);
        }

        //try materials
        try {
            String material = getYml().getString(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_MATERIAL);
            BedWars.debug(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_MATERIAL + " is set to: " + material);
            Material.valueOf(material);
        } catch (Exception ex) {
            BedWars.plugin.getLogger().severe("Invalid material at " + ConfigPath.SHOP_SPECIAL_IRON_GOLEM_MATERIAL);
        }
        try {
            String material = getYml().getString(ConfigPath.SHOP_SPECIAL_SILVERFISH_MATERIAL);
            BedWars.debug(ConfigPath.SHOP_SPECIAL_SILVERFISH_MATERIAL + " is set to: " + material);
            Material.valueOf(material);
        } catch (Exception ex) {
            BedWars.plugin.getLogger().severe("Invalid material at " + ConfigPath.SHOP_SPECIAL_SILVERFISH_MATERIAL);
        }

        getYml().options().copyDefaults(true);
        save();
    }

    private void loadShop() {
        //Quick Buy Button
        ItemStack button = BedWars.nms.createItemStack(getYml().getString(ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_MATERIAL),
                getYml().getInt(ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_AMOUNT));
        if (getYml().getBoolean(ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_ENCHANTED)) button = enchantItem(button);
        QuickBuyButton qbb = new QuickBuyButton(0, button, Messages.SHOP_QUICK_BUY_NAME, Messages.SHOP_QUICK_BUY_LORE);

        //Separator
        ItemStack separatorStandard = BedWars.nms.createItemStack(getYml().getString(ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_MATERIAL),
                getYml().getInt(ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_AMOUNT));
        if (getYml().getBoolean(ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_ENCHANTED))
            separatorStandard = enchantItem(separatorStandard);
        ItemStack separatorSelected = BedWars.nms.createItemStack(getYml().getString(ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_MATERIAL),
                getYml().getInt(ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_AMOUNT));
        if (getYml().getBoolean(ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_ENCHANTED))
            separatorSelected = enchantItem(separatorSelected);

        shop = new ShopIndex(Messages.SHOP_INDEX_NAME, qbb, Messages.SHOP_SEPARATOR_NAME, Messages.SHOP_SEPARATOR_LORE, separatorSelected, separatorStandard);

        for (String s : getYml().getConfigurationSection("").getKeys(false)) {
            if (s.equalsIgnoreCase(ConfigPath.SHOP_SETTINGS_PATH)) continue;
            if (s.equals(ConfigPath.SHOP_QUICK_DEFAULTS_PATH)) continue;
            if (s.equalsIgnoreCase(ConfigPath.SHOP_SPECIALS_PATH)) continue;
            ShopCategory sc = new ShopCategory(s, getYml());
            if (sc.isLoaded()) shop.addShopCategory(sc);
        }
    }

    /**
     * Hide an item details
     */
    public static ItemMeta hideItemStuff(ItemMeta im) {
        if (im != null) {
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_PLACED_ON);
        }
        return im;
    }

    /**
     * Enchant item stack and hide details
     */
    public static ItemStack enchantItem(ItemStack itemStack) {
        ItemStack i = new ItemStack(itemStack);
        ItemMeta im = i.getItemMeta();
        if (im != null) {
            im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            i.setItemMeta(hideItemStuff(im));
        }
        return i;
    }

    /**
     * Initialize a shop category to config
     */
    @SuppressWarnings("SameParameterValue")
    private void addDefaultShopCategory(String path, int slot, String material, int data, int amount, boolean enchant) {
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_SLOT, slot);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_ITEM_MATERIAL, material);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_ITEM_DATA, data);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_ITEM_AMOUNT, amount);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_ITEM_ENCHANTED, enchant);
    }

    /**
     * Create a tier for a shop content
     */
    public void adCategoryContentTier(String path, String contentName, int contentSlot, String tierName, String tierMaterial, int tierData, int amount, boolean enchant, int tierCost, String tierCurrency, boolean permanent,
                                      boolean downgradable) {
        path += ConfigPath.SHOP_CATEGORY_CONTENT_PATH + "." + contentName + ".";
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_SLOT, contentSlot);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_CONTENT_IS_PERMANENT, permanent);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_CONTENT_IS_DOWNGRADABLE, downgradable);
        path += ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_TIERS + "." + tierName;
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_MATERIAL, tierMaterial);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_DATA, tierData);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_AMOUNT, amount);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_ENCHANTED, enchant);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_COST, tierCost);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_CURRENCY, tierCurrency);
    }

    /**
     * Create a tier for a shop content (unbreakable)
     * Comment: Reason I made a new function; not all items can be unbreakable, thus shouldn't have the option.
     */
    public void adCategoryContentTier(String path, String contentName, int contentSlot, String tierName, String tierMaterial, int tierData, int amount, boolean enchant, int tierCost, String tierCurrency, boolean permanent,
                                      boolean downgradable, boolean unbreakable) {
        path += ConfigPath.SHOP_CATEGORY_CONTENT_PATH + "." + contentName + ".";
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_SLOT, contentSlot);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_CONTENT_IS_PERMANENT, permanent);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_CONTENT_IS_DOWNGRADABLE, downgradable);
        getYml().addDefault(path + ConfigPath.SHOP_CATEGORY_CONTENT_IS_UNBREAKABLE, unbreakable);
        path += ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_TIERS + "." + tierName;
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_MATERIAL, tierMaterial);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_DATA, tierData);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_AMOUNT, amount);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_ENCHANTED, enchant);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_COST, tierCost);
        getYml().addDefault(path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_CURRENCY, tierCurrency);
    }

    /**
     * Add buy items to a content tier
     */
    public void addBuyItem(String path, String contentName, String tierName, String item, String material, int data, int amount, String enchant, String potion, String itemName, boolean autoEquip) {
        path += ConfigPath.SHOP_CATEGORY_CONTENT_PATH + "." + contentName + "." + ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_TIERS + "." + tierName + "." + ConfigPath.SHOP_CONTENT_BUY_ITEMS_PATH + "." + item + ".";
        getYml().addDefault(path + "material", material);
        getYml().addDefault(path + "data", data);
        getYml().addDefault(path + "amount", amount);
        if (!enchant.isEmpty()) {
            getYml().addDefault(path + "enchants", enchant);
        }
        if (!potion.isEmpty()) {
            getYml().addDefault(path + "potion", potion);
        }
        if (autoEquip) {
            getYml().addDefault(path + "auto-equip", true);
        }
        if (!itemName.isEmpty()) {
            getYml().addDefault(path + "name", itemName);
        }
    }

    public void addBuyPotion(String path, String contentName, String tierName, String item, String material, int data, int amount, String enchant, String potion, String itemName) {
        path += ConfigPath.SHOP_CATEGORY_CONTENT_PATH + "." + contentName + "." + ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_TIERS + "." + tierName + "." + ConfigPath.SHOP_CONTENT_BUY_ITEMS_PATH + "." + item + ".";
        getYml().addDefault(path + "material", material);
        getYml().addDefault(path + "data", data);
        getYml().addDefault(path + "amount", amount);
        if (!enchant.isEmpty()) {
            getYml().addDefault(path + "enchants", enchant);
        }
        if (!potion.isEmpty()) {
            getYml().addDefault(path + "potion", potion);
        }
        getYml().addDefault(path + "potion-color", "");
        if (!itemName.isEmpty()) {
            getYml().addDefault(path + "name", itemName);
        }
    }

    public static ShopIndex getShop() {
        return shop;
    }

    /**
     * Register shop related listeners
     */
    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryListener(), BedWars.plugin);
        pm.registerEvents(new ShopCacheListener(), BedWars.plugin);
        pm.registerEvents(new QuickBuyListener(), BedWars.plugin);
        pm.registerEvents(new ShopOpenListener(), BedWars.plugin);
        pm.registerEvents(new PlayerDropListener(), BedWars.plugin);
        pm.registerEvents(new SpecialsListener(), BedWars.plugin);
    }
}
