package com.vadim.lakes.domain.interactors.base;

import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;

public abstract class AbstractInteractor implements Interactor {

    protected Executor mThreadExecutor;
    protected IMainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor(Executor threadExecutor, IMainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute() {
        this.mIsRunning = true;
        mThreadExecutor.execute(this);
    }


}
