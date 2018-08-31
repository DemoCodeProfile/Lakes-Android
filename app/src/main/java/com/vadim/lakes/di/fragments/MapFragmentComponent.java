package com.vadim.lakes.di.fragments;

import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.scopes.FragmentScope;
import com.vadim.lakes.ui.fragments.MapFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {MapFragmentModule.class})
public interface MapFragmentComponent extends InjectorComponent<MapFragment> {
    @Subcomponent.Builder
    interface Builder extends InjectorComponent.Builder<MapFragmentComponent, MapFragmentModule> {

    }
}
