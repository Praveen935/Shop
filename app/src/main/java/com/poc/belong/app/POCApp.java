package com.poc.belong.app;

import android.app.Application;

import com.poc.belong.net.VolleyHelper;

/**
 * Created by Praveen on 10/24/2015.
 */
public class POCApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.getInstance(getApplicationContext());
    }
}
