package com.andrei1058.bedwars._fwextension.menus.templates.elements;
import com.andrei1058.bedwars._fwextension.menus.templates.MenuTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public abstract class SubMenuElementTemplate extends ElementTemplate {

    protected MenuTemplate associatedMenu;

    public void postInit() {
        checkNull("associatedMenu", associatedMenu);
        super.postInit();
    }
}
