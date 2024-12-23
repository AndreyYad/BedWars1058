package com.andrei1058.bedwars._fwextension.menus.templates;
import lombok.Getter;
import lombok.ToString;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkZero;

@ToString
@SuppressWarnings("unused")
///! добавить потом название
public abstract class MenuTemplate extends ElementsGroupTemplate {

    @Getter private int height;
    @Getter private int width;

    public void _read() {
        checkZero("height", height);
        checkZero("width", width);
        super._read();
    }

    public MenuTemplate height(int height) {
        this.height = height;
        return this;
    }

    public MenuTemplate width(int width) {
        this.width = width;
        return this;
    }
}
