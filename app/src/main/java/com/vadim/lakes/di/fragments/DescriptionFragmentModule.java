package com.vadim.lakes.di.fragments;

import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.scopes.FragmentScope;
import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.repositories.ILakesRepository;
import com.vadim.lakes.ui.fragments.IDescriptionFragment;
import com.vadim.lakes.ui.presenters.fragments.DescriptionFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DescriptionFragmentModule implements InjectorComponent.Module {

    IDescriptionFragment.View mView;

    public DescriptionFragmentModule(IDescriptionFragment.View view) {
        this.mView = view;
    }


    @Provides
    @FragmentScope
    DescriptionFragmentPresenter provideDescriptionFragmentPresenter(ILakesRepository lakesRepository, IMainThread iMainThread, Executor executor){
        return new DescriptionFragmentPresenter(mView, iMainThread, executor, lakesRepository);
    }
}
