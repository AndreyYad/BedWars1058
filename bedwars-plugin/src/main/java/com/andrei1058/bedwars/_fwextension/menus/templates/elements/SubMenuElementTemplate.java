package com.andrei1058.bedwars._fwextension.menus.templates.elements;

import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.menus.templates.MenuTemplate;
import lombok.NonNull;


public abstract class SubMenuElementTemplate extends ElementTemplate {

    public abstract MenuTemplate getAssociatedMenu();
    public abstract void setAssociatedMenu(@NonNull MenuTemplate menu);
}
