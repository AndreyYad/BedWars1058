package com.andrei1058.bedwars.oldshop;

import com.andrei1058.bedwars.arena.Misc;
import com.andrei1058.bedwars.language.Language;
import com.andrei1058.bedwars.language.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.andrei1058.bedwars.language.Language.*;

public class ShopCategory {

    private static List<ShopCategory> shopCategories = new ArrayList<>();

    private int invSize = 27;
    private List<CategoryContent> content = new ArrayList<>();
    private String name;
    private ShopCategory parent;

    public ShopCategory(int invSize, String name) {
        this.invSize = invSize;
        this.name = name;
        shopCategories.add(this);
    }

    public void addContent(CategoryContent content) {
        this.content.add(content);
    }

    public List<CategoryContent> getContent() {
        return content;
    }

    public int getInvSize() {
        return invSize;
    }

    public String getName() {
        return name;
    }

    public void openToPlayer(Player p) {
        String path = Misc.replaceFirst((getName().replace("main.", Messages.SHOP_PATH + ".") + ".name"), ".invContents", "");
        if (getName().equals("main")) path = Messages.SHOP_PATH + ".name";
        String name = "§cName not set :(";
        if (getPlayerLanguage(p).exists(path)) {
            name = getMsg(p, path);
        } else {
            getPlayerLanguage(p).set(path, name);
        }
        Inventory inv = Bukkit.createInventory(p, invSize, name);
        for (CategoryContent cc : getContent()) {
            ItemStack i = cc.getItemStack().clone();
            ItemMeta im = i.getItemMeta();
            String ccNameP = getName().replace("main.", Messages.SHOP_PATH + ".") + "." + cc.getName() + ".name";
            String ccLoreP = getName().replace("main.", Messages.SHOP_PATH + ".") + "." + cc.getName() + ".lore";
            if (getPlayerLanguage(p).exists(ccNameP)) {
                im.setDisplayName(getMsg(p, ccNameP));
            } else {
                im.setDisplayName("§7Name not set");
                getPlayerLanguage(p).set(ccNameP, "§7Name not set");
            }
            List<String> lore = new ArrayList<>();
            if (getPlayerLanguage(p).exists(ccLoreP)) {
                for (String s : getList(p, ccLoreP)) {
                    lore.add(s.replace("{cost}", String.valueOf(cc.getContentAction().getCost())).replace("{currency}",
                            getCurrencyMsg(p, cc.getContentAction().getCurrency(), cc.getContentAction().getCost())));
                }
            } else {
                getPlayerLanguage(p).set(ccLoreP, new ArrayList<>());
            }
            im.setLore(lore);
            i.setItemMeta(im);
            inv.setItem(cc.getSlot(), i);
        }
        p.openInventory(inv);
    }

    public String getCurrencyMsg(Player p, String currency, int cost) {
        String c = "";

        switch (currency) {
            case "iron":
                c = cost == 1 ? Messages.MEANING_IRON_SINGULAR : Messages.MEANING_IRON_PLURAL;
                break;
            case "gold":
                c = cost == 1 ? Messages.MEANING_GOLD_SINGULAR : Messages.MEANING_GOLD_PLURAL;
                break;
            case "emerald":
                c = cost == 1 ? Messages.MEANING_EMERALD_SINGULAR : Messages.MEANING_EMERALD_PLURAL;
                break;
            case "diamond":
                c = cost == 1 ? Messages.MEANING_DIAMOND_SINGULAR : Messages.MEANING_DIAMOND_PLURAL;
                break;
            case "vault":
                c = cost == 1 ? Messages.MEANING_VAULT_SINGULAR : Messages.MEANING_VAULT_PLURAL;
                break;
        }

        return getMsg(p, c);
    }

    public static List<ShopCategory> getShopCategories() {
        return shopCategories;
    }

    public String getDisplayName(Player p) {
        return getMsg(p, Misc.replaceFirst((getName().replace("main.", Messages.SHOP_PATH + ".") + ".name"), ".invContents", ""));
    }

    public ShopCategory getParent() {
        return parent;
    }

    public void setParent(ShopCategory parent) {
        this.parent = parent;
    }

    public static ShopCategory getByName(String name) {
        for (ShopCategory sc : shopCategories) {
            if (sc.getName().equalsIgnoreCase(name)) {
                return sc;
            }
        }
        return null;
    }
}
