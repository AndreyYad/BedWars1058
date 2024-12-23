package com.andrei1058.bedwars._fwextension.shops.templates;

import com.andrei1058.bedwars._fwextension.common.templates.ItemTemplate;
import com.andrei1058.bedwars._fwextension.common.templates.ItemViewTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.elements.SlotTemplate;
import com.andrei1058.bedwars._fwextension.shops.templates.elements.TierTemplate;

public class ShopsTemplatesHelper {

    public static ItemTemplate thing() {
        return new ItemTemplate();
    }
    public static ItemTemplate t() {
        return thing();
    }

    public static ItemViewTemplate view() {
        return new ItemViewTemplate();
    }
    public static ItemViewTemplate v() {
        return view();
    }

    public static SlotTemplate slot() {
        return new SlotTemplate();
    }
    public static SlotTemplate s() {
        return slot();
    }

    public static TierTemplate tier() {
        return new TierTemplate();
    }
}
