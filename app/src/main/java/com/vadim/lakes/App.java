package com.vadim.lakes;

import android.app.Application;

import com.vadim.lakes.di.app.AppHandler;

public class App extends Application {

    AppHandler mAppHandler;

    private static App sApp;

    public static App getInstance() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        mAppHandler = new AppHandler(this);
        mAppHandler.init();
    }

    public AppHandler getAppHandler(){
        return mAppHandler;
    }
}
