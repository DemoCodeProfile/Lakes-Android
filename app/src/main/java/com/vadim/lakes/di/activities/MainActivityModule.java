package com.vadim.lakes.di.activities;

import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.scopes.ActivityScope;
import com.vadim.lakes.ui.activities.IMainActivity;
import com.vadim.lakes.ui.presenters.activities.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule implements InjectorComponent.Module {

    IMainActivity.View mView;

    public MainActivityModule(IMainActivity.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter(mView);
    }



}
