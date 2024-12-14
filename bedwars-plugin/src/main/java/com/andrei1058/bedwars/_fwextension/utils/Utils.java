package com.andrei1058.bedwars._fwextension.utils;

public class Utils {

    ///? мб ints?
    public static int[] times(int times) {
        int[] timesArray = new int[times];
        for (int time = 1; time <= times; time++) {
            timesArray[time-1] = time;
        }
        return timesArray;
    }

    public static void checkNull(String valueName, Object value) {
        if (value == null) {
            throw new RuntimeException("Значение %s не может быть null.".formatted(valueName));
        }
    }
}
