package com.vadim.lakes.di.activities;

import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.scopes.ActivityScope;
import com.vadim.lakes.ui.activities.MainActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent extends InjectorComponent<MainActivity> {
    @Subcomponent.Builder
    interface Builder extends InjectorComponent.Builder<MainActivityComponent, MainActivityModule> {

    }
}
