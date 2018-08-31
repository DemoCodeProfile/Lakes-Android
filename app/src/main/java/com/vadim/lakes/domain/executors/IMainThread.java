package com.vadim.lakes.domain.executors;

public interface IMainThread {
    void post(final Runnable runnable);
}
