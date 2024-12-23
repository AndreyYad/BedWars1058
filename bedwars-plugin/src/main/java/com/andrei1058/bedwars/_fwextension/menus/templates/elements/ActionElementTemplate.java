package com.andrei1058.bedwars._fwextension.menus.templates.elements;

import com.andrei1058.bedwars._fwextension.menus.realiz.ElementClickHandler;
import com.andrei1058.bedwars._fwextension.menus.templates.MenuTemplate;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@ToString
@SuppressWarnings("unused")
public class ActionElementTemplate extends ElementTemplate {

    ///! убедиться что эт вообще норм по производительности, юзать классы вместо обьектов
    ///! проверить что это вообще работает
    @Getter private Class<? extends ElementClickHandler> action =
        ((ElementClickHandler) event -> {
            /// по дефолту никак не обрабатывается
        }).getClass();

    public void _read() {
        checkNull("action", action);
        super._read();
    }

    public ActionElementTemplate action(@NonNull Class<? extends ElementClickHandler> action) {
        this.action = action;
        return this;
    }
}
