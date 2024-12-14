package com.andrei1058.bedwars._fwextension.menus.realiz;
import lombok.NonNull;
import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface ElementClickHandler {

    void handleClick(@NonNull InventoryClickEvent event);

}
