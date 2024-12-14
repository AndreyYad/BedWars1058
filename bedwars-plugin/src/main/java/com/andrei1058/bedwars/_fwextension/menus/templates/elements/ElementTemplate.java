package com.andrei1058.bedwars._fwextension.menus.templates.elements;

import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.sheets.Cell;
import com.andrei1058.bedwars._fwextension.menus.realiz.ElementClickHandler;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public abstract class ElementTemplate {

    /// дефолтные значения. А вообще значения задаются в блоке инициации наследника
    protected ExtMaterial type = ExtMaterial.EMPTY;
    protected int amount = 0;
    protected boolean isEnchanted = false;
    protected String name;
    protected List<String> lore;
    protected Set<ItemFlag> itemFlags;

    private final List<Cell<ElementTemplate>> cells = new ArrayList<>();

    public ElementTemplate() {
        
    }

    public ExtMaterial getType() {
        return null;
    }
    public void setType(@NonNull ExtMaterial type) {}

    public int getAmount() { return getType().isEmpty() ? 0 : 1; }
    public void setAmount(int amount) {}

    public boolean isEnchanted() { return false; }
    public void setEnchanted(boolean enchanted) { }

    public String getName() { return null; }
    public void setName(@NotNull String name) { }

    public List<String> getLore() { return null; }
    public void setLore(@NotNull List<String> lore) { }
    public void setLore(@NotNull String lore) {
        setLore(List.of(lore));
    }

    public Set<ItemFlag> getItemFlags() { return null; }
    public void setItemFlags(@NotNull Set<ItemFlag> itemFlags) { }
    public void setItemFlags(@NotNull ItemFlag... itemFlags) {
        setItemFlags(Set.of(itemFlags));
    }

    ///! убедиться что эт вообще норм по производительности, юзать классы вместо обьектов
    public Class<? extends ElementClickHandler> getClickHandler() {
        return ((ElementClickHandler) event -> {
            /// по дефолту никак не обрабатывается
        }).getClass();
    }

    public void placeToCell(@NonNull Cell<ElementTemplate> cell) {
        cell.setContent(this);
        this.getCells().add(cell);
    }
}
