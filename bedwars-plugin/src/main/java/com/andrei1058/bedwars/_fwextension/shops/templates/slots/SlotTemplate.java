package com.andrei1058.bedwars._fwextension.shops.templates.slots;

import com.andrei1058.bedwars._fwextension.common.templates.ItemStackTemplate;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public abstract class SlotTemplate<Price> extends ElementTemplate {

    protected Price price;

    public void postInit() {
        checkNull("price", price);
        super.postInit();
    }
}
