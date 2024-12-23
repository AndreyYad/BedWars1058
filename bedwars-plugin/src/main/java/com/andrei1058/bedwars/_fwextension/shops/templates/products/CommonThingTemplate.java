package com.andrei1058.bedwars._fwextension.shops.templates.products;

import com.andrei1058.bedwars._fwextension.common.templates.ItemTemplate;
import com.andrei1058.bedwars._fwextension.common.templates.ItemViewTemplate;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@ToString
@SuppressWarnings("unused")
public class CommonThingTemplate extends ProductTemplate {

    ///! разобраться с тем, что есть итемы, а есть то и не итемы (блоки например)
    @Getter private ItemTemplate thing;


    public void _read() {
        checkNull("thing", thing);
        thing._read();
        super._read();
    }

    public CommonThingTemplate thing(@NonNull ItemTemplate thing) {
        this.thing = thing;
        return this;
    }

    public CommonThingTemplate copyFromView(@NonNull ItemViewTemplate viewTemplate) {
        thing = (ItemTemplate) new ItemTemplate().
            type(viewTemplate.getType()).
            amount(viewTemplate.getAmount()).
            enchanted(viewTemplate.isEnchanted()).
            name(viewTemplate.getName()).
            itemFlags(viewTemplate.getItemFlags());
        return this;
    }
}
