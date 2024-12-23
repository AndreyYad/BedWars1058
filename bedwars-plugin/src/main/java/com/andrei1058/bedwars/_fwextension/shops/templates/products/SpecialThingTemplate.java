package com.andrei1058.bedwars._fwextension.shops.templates.products;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@ToString
@SuppressWarnings("unused")
public class SpecialThingTemplate extends ProductTemplate {

    ///! мб поменять на какой-то интерфейс
    @Getter private Object thing;

    public void _read() {
        checkNull("thing", thing);
        super._read();
    }

    public SpecialThingTemplate thing(@NonNull Object thing) {
        this.thing = thing;
            return this;
    }

}
