package com.poc.belong.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class AppUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager ConnectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (ConnectMgr == null)
            return false;
        NetworkInfo NetInfo = ConnectMgr.getActiveNetworkInfo();
        if (NetInfo == null)
            return false;

        return NetInfo.isConnected();
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        int height = 0;
        if (activity != null && !activity.isFinishing()) {
            activity.getWindowManager().getDefaultDisplay()
                    .getMetrics(displayMetrics);

            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                height = displayMetrics.heightPixels;
            } else {
                height = displayMetrics.widthPixels;
            }
            return height;
        }
        return height;
    }


    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        int width = 0;
        if (activity != null && !activity.isFinishing()) {
            activity.getWindowManager().getDefaultDisplay()
                    .getMetrics(displayMetrics);
            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                width = displayMetrics.widthPixels;
            } else {
                width = displayMetrics.heightPixels;
            }
        }
        return width;
    }
}
