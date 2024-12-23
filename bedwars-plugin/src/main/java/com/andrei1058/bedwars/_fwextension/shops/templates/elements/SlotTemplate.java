package com.andrei1058.bedwars._fwextension.shops.templates.elements;

import com.andrei1058.bedwars._fwextension.common.templates.ItemTemplate;
import com.andrei1058.bedwars._fwextension.common.templates.ItemViewTemplate;
import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.PriceTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.products.CommonThingTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.products.ProductTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.products.SpecialThingTemplate;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkEmpty;

@ToString
@SuppressWarnings("unused")
public class SlotTemplate extends ElementTemplate {

    @Getter private List<ProductTemplate> products = new ArrayList<>();

    @Getter private PriceTemplate price = new PriceTemplate();

    public void _read() {
        if (products.isEmpty()) {
            CommonThingTemplate product = new CommonThingTemplate();
            product.copyFromView(getView());
            products.add(product);
        }
        checkEmpty("price", price.getPrice().entrySet());
        super._read();
    }

    public SlotTemplate product(@NonNull Object thing) {
        SpecialThingTemplate product = new SpecialThingTemplate();
        product.thing(thing);
        products.add(product);
        return this;
    }

    public SlotTemplate product(@NonNull ItemTemplate thing) {
        CommonThingTemplate product = new CommonThingTemplate();
        product.thing(thing);
        products.add(product);
        return this;
    }

    public SlotTemplate price(@NonNull ExtMaterial currency, Integer cost) {
        price.price(currency, cost);
        return this;
    }
    public SlotTemplate price(@NonNull ExtMaterial currency1, int cost1, @NonNull ExtMaterial currency2, int cost2) {
        price.price(currency1, cost1, currency2, cost2);
        return this;
    }
    public SlotTemplate price(@NonNull ExtMaterial currency1, int cost1, @NonNull ExtMaterial currency2, int cost2, @NonNull ExtMaterial currency3, int cost3) {
        price.price(currency1, cost1, currency2, cost2, currency3, cost3);
        return this;
    }
    public SlotTemplate currency(@NonNull ExtMaterial currency) {
        price.currency(currency);
        return this;
    }
    public SlotTemplate cost(int cost) {
        price.cost(cost);
        return this;
    }
    
    /// копии методов родителя
    public SlotTemplate view(@NonNull ItemViewTemplate view) {return (SlotTemplate) super.view(view);}
    public SlotTemplate type(@NonNull ExtMaterial type) {return (SlotTemplate) super.type(type);}
    public SlotTemplate amount(int amount) {return (SlotTemplate) super.amount(amount);}
    public SlotTemplate enchanted() {return (SlotTemplate) super.enchanted();}
    public SlotTemplate name(@NonNull String name) {return (SlotTemplate) super.name(name);}
    public SlotTemplate lore(@NonNull List<String> lore) {return (SlotTemplate) super.lore(lore);}
    public SlotTemplate lore(@NonNull String lore) {return (SlotTemplate) super.lore(lore);}
    public SlotTemplate itemFlags(@NotNull @NonNull ItemFlag... itemFlags) {return (SlotTemplate) super.itemFlags(itemFlags);}
}
