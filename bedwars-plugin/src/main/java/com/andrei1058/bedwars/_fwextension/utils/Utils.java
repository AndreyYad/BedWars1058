package com.andrei1058.bedwars._fwextension.utils;

import lombok.NonNull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;

public class Utils {

    public static int[] times(int times) {
        return ints(1, times);
    }

    public static int[] indexes(Collection<?> collection) {
        return ints(0, collection.size() - 1);
    }

    public static int[] ints(int from, int to) {
        int[] intsArray = new int[to - from];
        for (int time = from; time <= to; time++) {
            intsArray[time - from] = time;
        }
        return intsArray;
    }

    public static void checkNull(String valueName, Object value) {
        if (value == null) {
            throw new RuntimeException("Значение %s не может быть null.".formatted(valueName));
        }
    }

    public static void checkZero(String valueName, int value) {
        if (value == 0) {
            throw new RuntimeException("Значение %s не может быть 0.".formatted(valueName));
        }
    }

    public static void checkEmpty(String valueName, Collection<?> collection) {
        if (collection.isEmpty()) {
            throw new RuntimeException("%s не может быть пустым.".formatted(valueName));
        }
    }

    public static ItemMeta takeMeta(@NonNull ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            throw new RuntimeException("Нельзя получить ItemMeta для %s".formatted(itemStack.getType()));
        }
        return meta;
    }
}
