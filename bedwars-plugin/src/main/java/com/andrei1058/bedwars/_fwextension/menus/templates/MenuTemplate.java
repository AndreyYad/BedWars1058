package com.andrei1058.bedwars._fwextension.menus.templates;

import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import com.andrei1058.bedwars._fwextension.sheets.Cell;
import com.andrei1058.bedwars._fwextension.sheets.Sheet;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

///! подумать над тем, чтобы сделать еще подменю, которые бы при открытии занимали бы не всю область
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public abstract class MenuTemplate extends Sheet<ElementTemplate> {

    protected ElementTemplate filler;

    protected MenuTemplate(int height, int width) {
        super(height, width);
    }

    public void postInit() {
        checkNull("filler", filler);
        super.postInit();
    }

    ///! подумать над тем, чтобы в итоговом темплейте магаза не было доступно и addCell() и element() и slot()
    protected MenuTemplate element(ElementTemplate element) {
        Cell<ElementTemplate> newCell = new Cell<>();
        element.placeToCell(newCell);
        cell(newCell);
        return this;
    }

    protected MenuTemplate element() {
        return element(filler);
    }
}
