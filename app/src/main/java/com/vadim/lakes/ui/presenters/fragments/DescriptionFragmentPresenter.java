package com.vadim.lakes.ui.presenters.fragments;

import android.util.Log;

import com.vadim.lakes.data.specifications.LakeByIdSpecification;
import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.interactors.GetByIdLakeInteractor;
import com.vadim.lakes.domain.interactors.IGetByIdLakeInteractor;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.domain.repositories.ILakesRepository;
import com.vadim.lakes.ui.fragments.IDescriptionFragment;

import javax.inject.Inject;

public class DescriptionFragmentPresenter implements IDescriptionFragment.Presenter {

    private IDescriptionFragment.View mView;
    private final IMainThread mMainThread;
    private final Executor mExecutor;
    private final ILakesRepository mLakesRepository;

    @Inject
    public DescriptionFragmentPresenter(IDescriptionFragment.View view, IMainThread mainThread, Executor executor, ILakesRepository lakesRepository) {
        this.mView = view;
        this.mMainThread = mainThread;
        this.mExecutor = executor;
        this.mLakesRepository = lakesRepository;
    }

    @Override
    public void onStop() {
        Log.i("Info", "onStop");
    }

    @Override
    public void onStart() {
        Log.i("Info", "onStart");
    }

    @Override
    public void onLoadData(int id) {
        GetByIdLakeInteractor lakeInteractor = new GetByIdLakeInteractor(mExecutor, mMainThread, mLakesRepository, new IGetByIdLakeInteractor.Callback() {
            @Override
            public void onLakeReceived(Lake lake) {
                mView.setView(lake);
            }
            @Override
            public void onError(String error) {
                mView.setErrorLoadingData(error);
            }
        }, new LakeByIdSpecification(id));
        lakeInteractor.execute();
    }

    @Override
    public void bindView(IDescriptionFragment.View view) {
        this.mView = view;
    }

    @Override
    public void unbindView() {
        this.mView = null;
    }

}
