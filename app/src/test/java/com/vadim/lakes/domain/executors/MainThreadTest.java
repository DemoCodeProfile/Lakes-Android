package com.vadim.lakes.domain.executors;

public class MainThreadTest implements IMainThread {

    @Override
    public void post(Runnable runnable) {
        runnable.run();
    }
}
