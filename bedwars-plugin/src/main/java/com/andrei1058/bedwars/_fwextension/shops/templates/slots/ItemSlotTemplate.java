package com.andrei1058.bedwars._fwextension.shops.templates.slots;

import com.andrei1058.bedwars._fwextension.common.templates.ItemStackTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.slots.SlotTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public abstract class ItemSlotTemplate<Price> extends SlotTemplate<Price> {

    protected ItemStackTemplate itemToSale;

    public void postInit() {
        checkNull("itemToSale", itemToSale);
        super.postInit();
    }
}
