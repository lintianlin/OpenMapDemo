package com.sinfeeloo.openmap;

import android.app.Application;

/**
 * @author: mhj
 * @date: 2018/4/4 11:37
 * @desc:
 */

public class App extends Application {
    private static App instance;

    public static App instance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
