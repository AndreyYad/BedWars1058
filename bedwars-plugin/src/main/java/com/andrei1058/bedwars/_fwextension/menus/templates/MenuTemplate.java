package com.andrei1058.bedwars._fwextension.menus.templates;

import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import com.andrei1058.bedwars._fwextension.sheets.Cell;
import com.andrei1058.bedwars._fwextension.sheets.Sheet;
import lombok.Getter;
import lombok.NonNull;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

import static java.lang.String.format;

///! подумать над тем, чтобы сделать еще подменю, которые бы при открытии занимали бы не всю область
@SuppressWarnings("unused")
public abstract class MenuTemplate extends Sheet<ElementTemplate> {

    protected abstract ElementTemplate getDefaultElement();


    protected MenuTemplate(int height, int width) {
        super(height, width);
    }

    protected MenuTemplate element(ElementTemplate element) {
        addCell(new Cell<>(element));
        return this;
    }

    protected MenuTemplate element() {
        return element(getDefaultElement());
    }
}
