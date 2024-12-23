package com.andrei1058.bedwars._fwextension.menus.templates.elements;
import com.andrei1058.bedwars._fwextension.common.templates.ItemViewTemplate;
import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import com.andrei1058.bedwars._fwextension.menus.templates.ElementsGroupTemplate;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@ToString
@SuppressWarnings("unused")
public class NavigationElementTemplate extends ElementTemplate {

    @Getter private ElementsGroupTemplate destination;

    ///! сделать потом возможность менять не ток тип
    @Getter private ExtMaterial openedType;

    public void _read() {
        if (openedType == null) {
            openedType = super.getView().getType();
        }
        checkNull("subMenu", destination);
        destination._read();
        super._read();
    }

    public NavigationElementTemplate destination(@NonNull ElementsGroupTemplate destination) {
        this.destination = destination;
        return this;
    }
    public NavigationElementTemplate destination(@NonNull Class<? extends ElementsGroupTemplate> destinationClass) {
        ElementsGroupTemplate destination = null;
        try {
            destination = destinationClass.getDeclaredConstructor().newInstance();
        } catch (Exception ignore) {}
        this.destination = destination;
        return this;
    }
    public NavigationElementTemplate openedType(@NonNull ExtMaterial openedType) {
        this.openedType = openedType;
        return this;
    }

    /// копии методов родителя
    public NavigationElementTemplate view(@NonNull ItemViewTemplate view) {return (NavigationElementTemplate) super.view(view);}
    public NavigationElementTemplate type(@NonNull ExtMaterial type) {return (NavigationElementTemplate) super.type(type);}
    public NavigationElementTemplate amount(int amount) {return (NavigationElementTemplate) super.amount(amount);}
    public NavigationElementTemplate enchanted() {return (NavigationElementTemplate) super.enchanted();}
    public NavigationElementTemplate name(@NonNull String name) {return (NavigationElementTemplate) super.name(name);}
    public NavigationElementTemplate lore(@NonNull List<String> lore) {return (NavigationElementTemplate) super.lore(lore);}
    public NavigationElementTemplate lore(@NonNull String lore) {return (NavigationElementTemplate) super.lore(lore);}
    public NavigationElementTemplate itemFlags(@NotNull @NonNull ItemFlag... itemFlags) {return (NavigationElementTemplate) super.itemFlags(itemFlags);}
}
