package com.vadim.lakes.di.fragments;

import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.scopes.FragmentScope;
import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.repositories.ILakesRepository;
import com.vadim.lakes.ui.fragments.IMapFragment;
import com.vadim.lakes.ui.presenters.fragments.MapFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MapFragmentModule implements InjectorComponent.Module {

    IMapFragment.View mView;

    public MapFragmentModule(IMapFragment.View view) {
        this.mView = view;
    }

    @Provides
    @FragmentScope
    MapFragmentPresenter provideMapFragmentPresenter(ILakesRepository lakesRepository, IMainThread iMainThread, Executor executor){
        return new MapFragmentPresenter(mView, lakesRepository, iMainThread, executor);
    }


}
