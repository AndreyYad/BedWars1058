package com.andrei1058.bedwars._fwextension.shops.templates;

import com.andrei1058.bedwars._fwextension.menus.templates.MenuTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public class ShopTemplate extends MenuTemplate {
    public ShopTemplate(int height, int width) {
        super(height, width);
    }
}
