//package com.andrei1058.bedwars._fwextension.menus.templates.elements;
//
//import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
//import lombok.*;
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.inventory.ItemFlag;
//import org.bukkit.inventory.meta.ItemMeta;
//
//import java.util.List;
//import java.util.Set;
//
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode(callSuper = true)
//@SuppressWarnings("unused")
//public class CommonElementTemplate extends ElementTemplate {
//
//    {
//        type = ExtMaterial.STONE;
//        amount = 10;
//    }
//
//    public CommonElementTemplate() {
//        setType(ExtMaterial.EMPTY);
//    }
//
//    public CommonElementTemplate withType(@NonNull ExtMaterial type) {
//        setType(type);
//        return this;
//    }
//    public CommonElementTemplate withAmount(int amount) {
//        setAmount(amount);
//        return this;
//    }
//    public CommonElementTemplate withEnchanted(boolean isEnchanted) {
//        setEnchanted(isEnchanted);
//        return this;
//    }
//    public CommonElementTemplate withEnchanted() {
//        return withEnchanted(true);
//    }
//    public CommonElementTemplate withName(@NonNull String name) {
//        setName(name);
//        return this;
//    }
//    public CommonElementTemplate withLore(@NonNull List<String> lore) {
//        setLore(lore);
//        return this;
//    }
//    public CommonElementTemplate withLore(@NonNull String lore) {
//        setLore(lore);
//        return this;
//    }
//    public CommonElementTemplate withItemFlags(@NonNull Set<ItemFlag> itemFlags) {
//        setItemFlags(itemFlags);
//        return this;
//    }
//    public CommonElementTemplate withItemFlags(@NonNull ItemFlag... itemFlags) {
//        setItemFlags(itemFlags);
//        return this;
//    }
//}
