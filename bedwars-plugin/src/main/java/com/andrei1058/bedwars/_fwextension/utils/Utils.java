package com.andrei1058.bedwars._fwextension.utils;

import java.util.List;

public class Utils {

    ///? мб ints?
    public static int[] times(int times) {
        int[] timesArray = new int[times];
        for (int time = 1; time <= times; time++) {
            timesArray[time-1] = time;
        }
        return timesArray;
    }
}
