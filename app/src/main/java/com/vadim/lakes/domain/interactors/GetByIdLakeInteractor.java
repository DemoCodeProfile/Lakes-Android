package com.vadim.lakes.domain.interactors;

import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.interactors.base.AbstractInteractor;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.domain.repositories.ILakesRepository;
import com.vadim.lakes.domain.specifications.BaseSpecification;

public class GetByIdLakeInteractor extends AbstractInteractor implements IGetByIdLakeInteractor {

    private final ILakesRepository mLakesRepository;
    private final IGetByIdLakeInteractor.Callback mCallback;
    private final BaseSpecification mBaseSpecification;

    public GetByIdLakeInteractor(Executor threadExecutor, IMainThread mainThread, ILakesRepository mLakesRepository, Callback mCallback, BaseSpecification mBaseSpecification) {
        super(threadExecutor, mainThread);
        this.mLakesRepository = mLakesRepository;
        this.mCallback = mCallback;
        this.mBaseSpecification = mBaseSpecification;
    }

    private void sendResponse(final Lake lake){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLakeReceived(lake);
            }
        });
    }

    private void sendError(final String error){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onError(error);
            }
        });
    }

    @Override
    public void run() {
        try {
            Lake lake = mLakesRepository.queryOne(mBaseSpecification);
            sendResponse(lake);
        } catch (Exception e){
            sendError(e.getMessage());
        }
    }
}
