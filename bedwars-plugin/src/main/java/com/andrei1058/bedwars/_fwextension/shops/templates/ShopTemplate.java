package com.andrei1058.bedwars._fwextension.shops.templates;

import com.andrei1058.bedwars._fwextension.menus.templates.MenuTemplate;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import com.andrei1058.bedwars._fwextension.sheets.Cell;
import com.andrei1058.bedwars._fwextension.shops.templates.slots.SlotTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public abstract class ShopTemplate<Price> extends MenuTemplate {
    protected ShopTemplate(int height, int width) {
        super(height, width);
    }

    protected ShopTemplate<Price> slot(SlotTemplate<Price> slot) {
        super.element(slot);
        return this;
    }

    protected ShopTemplate<Price> slot() {
        element();
        return this;
    }
}
