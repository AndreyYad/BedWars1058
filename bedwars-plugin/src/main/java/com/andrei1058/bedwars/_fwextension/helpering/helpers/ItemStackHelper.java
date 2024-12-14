//package com.andrei1058.bedwars._fwextension.configs.utils;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.attribute.Attribute;
//import org.bukkit.attribute.AttributeModifier;
//import org.bukkit.inventory.meta.ItemMeta;
//
//import java.util.Collection;
//
/////! разобраться с тем как это все организовать
//public abstract class ItemStackHelper {
//
//    protected abstract static class ItemStackAdapter {
//        public abstract Material getType();
//
//        public abstract void setType(Material type);
//
//        public abstract int getAmount();
//
//        public abstract void setAmount(int amount);
//
//        public abstract ItemMeta getMeta();
//
//        public abstract void setMeta(ItemMeta meta);
//    }
//
//    private final ItemStackAdapter itemStack;
//    private boolean isEmpty = true;
//
//    protected ItemStackController(ItemStackAdapter itemStack) {
//        this.itemStack = itemStack;
//    }
//
//    protected void init(Material type, int amount, ItemMeta meta) {
//        itemStack.setType(type);
//        itemStack.setAmount(amount);
//        itemStack.setMeta(meta);
//        syncEmptyState();
//    }
//
//    protected void init(Material type, int amount) {
//        itemStack.setType(type);
//        itemStack.setAmount(amount);
//        itemStack.setMeta(Bukkit.getItemFactory().getItemMeta(type));
//        syncEmptyState();
//    }
//
//    protected void init(Material type) {
//        itemStack.setType(type);
//        itemStack.setAmount(1);
//        itemStack.setMeta(Bukkit.getItemFactory().getItemMeta(type));
//        syncEmptyState();
//    }
//
//    protected void init() {
//        itemStack.setType(Material.AIR);
//        itemStack.setAmount(0);
//        itemStack.setMeta(Bukkit.getItemFactory().getItemMeta(Material.AIR));
//        syncEmptyState();
//    }
//
//    private void syncEmptyState() {
//        if (itemStack.getType() == Material.AIR) {
//            isEmpty = true;
//            itemStack.setAmount(0);
//        } else {
//            isEmpty = false;
//            if (itemStack.getAmount() == 0) {
//                itemStack.setAmount(1);
//            }
//        }
//    }
//
//    public boolean isEmpty() {
//        return isEmpty;
//    }
//
//    public void changeType(Material newType) {
//        Material oldType = itemStack.getType();
//        if (oldType != newType) {
//            ///! посмотреть потом, че оно выдаст для воздуха
//            ItemMeta oldMeta = itemStack.getMeta();
//            setMeta(Bukkit.getItemFactory().getItemMeta(type));
//            if (oldMeta != null && meta != null) {
//                meta.setLore(oldMeta.getLore());
//                meta.setDisplayName(oldMeta.getDisplayName());
//                ///! превратить в систему, заменяющую NBT и более быструю
//                Collection<AttributeModifier> technicalAttributes = oldMeta.getAttributeModifiers(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS);
//                if (technicalAttributes != null) {
//                    technicalAttributes.forEach(attribute ->
//                        meta.addAttributeModifier(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, attribute));
//                }
//            }
//        }
//        setType(newType);
//    }
//}
