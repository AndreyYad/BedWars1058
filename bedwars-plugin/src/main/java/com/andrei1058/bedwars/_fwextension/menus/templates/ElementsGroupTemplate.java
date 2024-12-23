package com.andrei1058.bedwars._fwextension.menus.templates;

import com.andrei1058.bedwars._fwextension.common.templates.ITemplate;
import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.NavigationElementTemplate;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.andrei1058.bedwars._fwextension.menus.templates.MenusTemplatesHelper.element;
import static com.andrei1058.bedwars._fwextension.utils.Utils.checkEmpty;

@ToString(of = {"elements", "filler"})
@SuppressWarnings({"unused", "ConstantValue"})
public abstract class ElementsGroupTemplate implements ITemplate {

    @Getter private List<ElementTemplate> elements = new ArrayList<>();

    @Getter private ElementTemplate filler;

    public void _read() {
        ///! проверить в остальных местах
        checkEmpty("elements", elements);
        for (ElementTemplate element : elements) {
            if (element instanceof NavigationElementTemplate navigationTemplate) {
                if (this.filler != null && navigationTemplate.getDestination().filler == null) {
                    navigationTemplate.getDestination().filler = this.filler;
                }
            }
            if (element != null) {
                element._read();
            }
        }
        if (filler == null) {
            filler = element().type(ExtMaterial.EMPTY).amount(0);
        }
    }

    public ElementsGroupTemplate elements(@NonNull ElementTemplate... elements) {
        this.elements.addAll(Arrays.asList(elements));
        return this;
    }
    public ElementsGroupTemplate filler(@NonNull ElementTemplate filler) {
        this.filler = filler;
        return this;
    }
    
    /*
    .elements(
                ______, ______, ______, ______, ______, ______, ______, ______, ______,
                ______, ______, ______, ______, ______, ______, ______, ______, ______,
                ______, ______, ______, ______, ______, ______, ______, ______, ______,
                ______, ______, ______, ______, ______, ______, ______, ______, ______,
                ______, ______, ______, ______, ______, ______, ______, ______, ______
            )
     */


    protected ElementTemplate __ = filler;
    protected ElementTemplate ___ = filler;
    protected ElementTemplate ____ = filler;
    protected ElementTemplate _____ = filler;
    protected ElementTemplate ______ = filler;
    protected ElementTemplate _______ = filler;
    protected ElementTemplate ________ = filler;
    protected ElementTemplate _________ = filler;
    protected ElementTemplate __________ = filler;
    protected ElementTemplate ___________ = filler;
    protected ElementTemplate ____________ = filler;
    protected ElementTemplate _____________ = filler;
    protected ElementTemplate ______________ = filler;
    protected ElementTemplate _______________ = filler;
    protected ElementTemplate ________________ = filler;
    protected ElementTemplate _________________ = filler;
    protected ElementTemplate __________________ = filler;
    protected ElementTemplate ___________________ = filler;
    protected ElementTemplate ____________________ = filler;
    protected ElementTemplate _____________________ = filler;
    protected ElementTemplate ______________________ = filler;
    protected ElementTemplate _______________________ = filler;
    protected ElementTemplate ________________________ = filler;
    protected ElementTemplate _________________________ = filler;
    protected ElementTemplate __________________________ = filler;
    protected ElementTemplate ___________________________ = filler;
    protected ElementTemplate ____________________________ = filler;
    protected ElementTemplate _____________________________ = filler;
    protected ElementTemplate ______________________________ = filler;

    protected ElementTemplate xx = null;
    protected ElementTemplate xxx = null;
    protected ElementTemplate xxxx = null;
    protected ElementTemplate xxxxx = null;
    protected ElementTemplate xxxxxx = null;
    protected ElementTemplate xxxxxxx = null;
    protected ElementTemplate xxxxxxxx = null;
    protected ElementTemplate xxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxxxxxxxxx = null;
    protected ElementTemplate xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = null;

}
