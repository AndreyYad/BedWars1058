package com.andrei1058.bedwars.oldshop;

import com.andrei1058.bedwars.arena.team.TeamColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerVault {
    Player p;
    ItemStack pants = createArmor(Material.LEATHER_LEGGINGS), boots = createArmor(Material.LEATHER_BOOTS), chestplate = createArmor(Material.LEATHER_CHESTPLATE), helmet = createArmor(Material.LEATHER_HELMET);
    List<ItemStack> invItems = new ArrayList<>();

    private ItemStack createArmor(Material material) {
        ItemStack i = new ItemStack(material);
        LeatherArmorMeta lam = (LeatherArmorMeta) i.getItemMeta();
        lam.setColor(TeamColor.getColor(color));
        lam.spigot().setUnbreakable(true);
        i.setItemMeta(lam);
        return i;
    }

    public PlayerVault(Player p) {
        this.p = p;
        vaults.add(this);
    }

    public void addInvItem(ItemStack i) {
        invItems.add(i);
    }

    public void setPants(ItemStack pants) {
        this.pants = pants;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public List<ItemStack> getInvItems() {
        return invItems;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public ItemStack getPants() {
        return pants;
    }
}
