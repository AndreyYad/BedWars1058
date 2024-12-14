package com.andrei1058.bedwars._fwextension.menus.templates.elements;

import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.sheets.Cell;
import com.andrei1058.bedwars._fwextension.menus.realiz.ElementClickHandler;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@Getter
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public class ElementTemplate {

    /// дефолтные значения. А вообще значения задаются в блоке инициации наследника
    protected ExtMaterial type = ExtMaterial.EMPTY;
    protected int amount = 0;
    protected boolean isEnchanted = false;
    protected String name;
    protected List<String> lore;
    protected Set<ItemFlag> itemFlags;

    ///! убедиться что эт вообще норм по производительности, юзать классы вместо обьектов
    protected Class<? extends ElementClickHandler> clickHandler =
        ((ElementClickHandler) event -> {
            /// по дефолту никак не обрабатывается
        }).getClass();

    private final List<Cell<ElementTemplate>> cells = new ArrayList<>();

    ///! вынести это мб в интерфейс потом
    public void postInit() {
        checkNull("type", type);
        if (amount == 0 && !type.isEmpty()) {
            amount = 1;
        }
    }

    public void placeToCell(@NonNull Cell<ElementTemplate> cell) {
        cell.setContent(this);
        this.getCells().add(cell);
    }
}
