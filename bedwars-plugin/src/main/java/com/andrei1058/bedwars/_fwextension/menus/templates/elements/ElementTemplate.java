package com.andrei1058.bedwars._fwextension.menus.templates.elements;

import com.andrei1058.bedwars._fwextension.common.templates.ItemViewTemplate;
import com.andrei1058.bedwars._fwextension.common.templates.ITemplate;
import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.inventory.ItemFlag;

import java.util.List;

@ToString
@SuppressWarnings("unused")
public class ElementTemplate implements ITemplate {

    @Getter private ItemViewTemplate view = new ItemViewTemplate();

    public void _read() {
        view._read();
    }


    public ElementTemplate view(@NonNull ItemViewTemplate view) {
        this.view = view;
        return this;
    }


    public ElementTemplate type(@NonNull ExtMaterial type) {
        view.type(type);
        return this;
    }
    public ElementTemplate amount(int amount) {
        view.amount(amount);
        return this;
    }
    public ElementTemplate enchanted() {
        view.enchanted();
        return this;
    }
    public ElementTemplate name(@NonNull String name) {
        view.name(name);
        return this;
    }
    public ElementTemplate lore(@NonNull List<String> lore) {
        view.lore(lore);
        return this;
    }
    public ElementTemplate lore(@NonNull String lore) {
        return lore(List.of(lore));
    }
    public ElementTemplate itemFlags(@NonNull ItemFlag... itemFlags) {
        view.itemFlags(itemFlags);
        return this;
    }
}
