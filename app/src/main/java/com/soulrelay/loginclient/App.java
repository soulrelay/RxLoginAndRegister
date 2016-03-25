package com.soulrelay.loginclient;

import android.app.Application;

/**
 * Created by sushuai on 2016/3/25.
 */
public class App extends Application {
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
