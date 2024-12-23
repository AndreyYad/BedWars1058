package com.andrei1058.bedwars._fwextension.common.templates;

import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.inventory.ItemFlag;

import java.util.List;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public class ItemViewTemplate implements ITemplate {

    @Getter private ExtMaterial type;
    @Getter private int amount = 0;
    @Getter private boolean isEnchanted = false;
    @Getter private String name;
    @Getter private List<String> lore;
    @Getter private ItemFlag[] itemFlags;

    public void _read() {
        checkNull("type", type);
        if (amount == 0 && !type.isEmpty()) {
            amount = 1;
        }
    }

    public ItemViewTemplate type(@NonNull ExtMaterial type) {
        this.type = type;
        return this;
    }
    public ItemViewTemplate amount(int amount) {
        this.amount = amount;
        return this;
    }
    public ItemViewTemplate enchanted(boolean isEnchanted) {
        this.isEnchanted = isEnchanted;
        return this;
    }
    public ItemViewTemplate enchanted() {
        return enchanted(true);
    }
    public ItemViewTemplate name(@NonNull String name) {
        this.name = name;
        return this;
    }
    public ItemViewTemplate lore(@NonNull List<String> lore) {
        this.lore = lore;
        return this;
    }
    public ItemViewTemplate lore(@NonNull String lore) {
        return lore(List.of(lore));
    }
    public ItemViewTemplate itemFlags(@NonNull ItemFlag... itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }
}
