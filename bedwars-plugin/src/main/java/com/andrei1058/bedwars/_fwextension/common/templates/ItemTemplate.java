package com.andrei1058.bedwars._fwextension.common.templates;

import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.shops.templates.elements.SlotTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public class ItemTemplate extends ItemViewTemplate{

    @Getter private Map<Enchantment, Integer> enchants = new HashMap<>();
    @Getter private boolean permanent = false;

    ///! доделать остальные

    public void _read() {
        super._read();
    }

    public ItemTemplate enchant(@NotNull Enchantment ench, int level) {
        enchants.put(ench, level);
        return this;
    }

    public boolean isEnchanted() {
        return super.isEnchanted() || !enchants.isEmpty();
    }

    public ItemTemplate permanent(boolean permanent) {
        this.permanent = permanent;
        return this;
    }
    public ItemTemplate permanent() {
        return permanent(true);
    }
    
    /// копии методов родителя
    public ItemTemplate type(@NonNull ExtMaterial type) {return (ItemTemplate) super.type(type);}
    public ItemTemplate amount(int amount) {return (ItemTemplate) super.amount(amount);}
    public ItemTemplate name(@NonNull String name) {return (ItemTemplate) super.name(name);}
    public ItemTemplate lore(@NonNull List<String> lore) {return (ItemTemplate) super.lore(lore);}
    public ItemTemplate lore(@NonNull String lore) {return (ItemTemplate) super.lore(lore);}
    public ItemTemplate itemFlags(@NotNull @NonNull ItemFlag... itemFlags) {return (ItemTemplate) super.itemFlags(itemFlags);}
}
