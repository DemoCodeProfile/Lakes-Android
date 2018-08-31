package com.vadim.lakes.domain.executors;

import android.os.Handler;
import android.os.Looper;

import com.vadim.lakes.di.scopes.AppScope;

import javax.inject.Inject;

@AppScope
public class MainThread implements IMainThread {
    private static MainThread sMainThread;

    private Handler mHandler;

    @Inject
    public MainThread() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

}
