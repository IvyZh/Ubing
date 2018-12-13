package com.ivyzh.baselibrary.log;

import android.util.Log;

public class L {
    private final static String TAG = "way";
    private static boolean isDebug = true;

    private L() {
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

}
