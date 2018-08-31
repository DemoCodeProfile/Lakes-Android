package com.vadim.lakes.ui.presenters.fragments;

import android.util.Log;

import com.google.android.gms.maps.model.Marker;
import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.interactors.GetAllLakesInteractor;
import com.vadim.lakes.domain.interactors.IGetAllLakesInteractor;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.domain.repositories.ILakesRepository;
import com.vadim.lakes.ui.fragments.IMapFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class MapFragmentPresenter implements IMapFragment.Presenter {

    private Map<Marker, Lake> mLakeMap;

    private IMapFragment.View mView;
    private final IMainThread mMainThread;
    private final Executor mExecutor;
    private final ILakesRepository mLakesRepository;


    @Inject
    public MapFragmentPresenter(IMapFragment.View view, ILakesRepository lakesRepository, IMainThread mainThread, Executor executor) {
        this.mView = view;
        this.mMainThread = mainThread;
        this.mExecutor = executor;
        this.mLakesRepository = lakesRepository;
        this.mLakeMap = new HashMap<>();
    }


    @Override
    public void onMapReady() {
        IGetAllLakesInteractor allLakesInteractor = new GetAllLakesInteractor(mExecutor, mMainThread, mLakesRepository, new IGetAllLakesInteractor.Callback() {
            @Override
            public void onLakesReceived(List<Lake> lakes) {
                if (mView != null)
                    mView.setMarkers(lakes);
            }

            @Override
            public void onError(String error) {
                if (mView != null)
                    mView.setErrorLoadingData(error);
            }
        });
        allLakesInteractor.execute();
    }


    @Override
    public void onMarkerCreated(Marker marker, Lake lake) {
        mLakeMap.put(marker, lake);
    }

    @Override
    public Lake onMarkerClick(Marker marker) {
        return mLakeMap.get(marker);
    }

    @Override
    public void bindView(IMapFragment.View view) {
        this.mView = view;
    }

    @Override
    public void unbindView() {
        this.mView = null;
    }


    @Override
    public void onStop() {
        Log.i("Info", "onStop");
    }

    @Override
    public void onStart() {
        Log.i("Info", "onStart");
    }
}
