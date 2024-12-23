package com.andrei1058.bedwars._fwextension.menus.templates;

import com.andrei1058.bedwars._fwextension.menus.templates.elements.ElementTemplate;
import com.andrei1058.bedwars._fwextension.menus.templates.elements.NavigationElementTemplate;
import lombok.NonNull;

public class MenusTemplatesHelper {

    public static ElementTemplate element() {
        return new ElementTemplate();
    }

    public static NavigationElementTemplate navigationElementFor(@NonNull ElementsGroupTemplate destination) {
        return new NavigationElementTemplate()
            .destination(destination);
    }
    public static NavigationElementTemplate navigationElementFor(@NonNull Class<? extends ElementsGroupTemplate> destinationClass) {
        return new NavigationElementTemplate()
            .destination(destinationClass);
    }
}
