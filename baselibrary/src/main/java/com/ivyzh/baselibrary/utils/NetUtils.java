package com.ivyzh.baselibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

public class NetUtils {
    static String TAG = "NetUtils";

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                Log.e(TAG, "网络可用");
                return mNetworkInfo.isAvailable();
            }
        }
        Log.e(TAG, "网络不用");
        return false;
    }
}
