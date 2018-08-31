package com.vadim.lakes.domain.interactors;

import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.interactors.base.AbstractInteractor;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.domain.repositories.ILakesRepository;

import java.util.List;

public class GetAllLakesInteractor extends AbstractInteractor implements IGetAllLakesInteractor  {


    private final ILakesRepository mLakesRepository;
    private final IGetAllLakesInteractor.Callback mCallback;


    public GetAllLakesInteractor(Executor threadExecutor, IMainThread mainThread, ILakesRepository mLakesRepository, Callback mCallback) {
        super(threadExecutor, mainThread);
        this.mLakesRepository = mLakesRepository;
        this.mCallback = mCallback;
    }

    private void sendResponse(final List<Lake> lakes){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLakesReceived(lakes);
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
            List<Lake> lakes = mLakesRepository.query(null);
            sendResponse(lakes);
        } catch (Exception e) {
            sendError(e.getMessage());
        }
    }
}
