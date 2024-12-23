package com.andrei1058.bedwars._fwextension.shops.templates.elements;

import com.andrei1058.bedwars._fwextension.common.templates.ItemViewTemplate;
import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@ToString
@SuppressWarnings("unused")
public class TierTemplate extends ElementTemplate {

    @Getter protected List<SlotTemplate> tiers = new ArrayList<>();

    public void _read() {
        if (tiers.size() < 2) {
            throw new RuntimeException("Количество слотов в тире не может быть меньше двух.");
        }
        view(tiers.get(0).getView());
        super._read();
    }

    ///! мб добавить варики с first, second и тд
    public TierTemplate slot(@NonNull SlotTemplate slot) {
        tiers.add(slot);
        return this;
    }
    
    /// копии методов родителя
    public TierTemplate view(@NonNull ItemViewTemplate view) {return (TierTemplate) super.view(view);}
    public TierTemplate type(@NonNull ExtMaterial type) {return (TierTemplate) super.type(type);}
    public TierTemplate amount(int amount) {return (TierTemplate) super.amount(amount);}
    public TierTemplate enchanted() {return (TierTemplate) super.enchanted();}
    public TierTemplate name(@NonNull String name) {return (TierTemplate) super.name(name);}
    public TierTemplate lore(@NonNull List<String> lore) {return (TierTemplate) super.lore(lore);}
    public TierTemplate lore(@NonNull String lore) {return (TierTemplate) super.lore(lore);}
    public TierTemplate itemFlags(@NotNull @NonNull ItemFlag... itemFlags) {return (TierTemplate) super.itemFlags(itemFlags);}
}
