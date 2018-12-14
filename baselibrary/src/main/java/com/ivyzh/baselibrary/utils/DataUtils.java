package com.ivyzh.baselibrary.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivy on 2018/12/15.
 */

public class DataUtils {
    public static List<String> generateData() {
        return generateData(50);
    }

    public static List<String> generateData(int count) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add("data -> " + i);
        }
        return list;
    }
}
