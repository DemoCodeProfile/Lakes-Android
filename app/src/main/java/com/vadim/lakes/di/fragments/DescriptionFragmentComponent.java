package com.vadim.lakes.di.fragments;

import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.scopes.FragmentScope;
import com.vadim.lakes.ui.fragments.DescriptionFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {DescriptionFragmentModule.class})
public interface DescriptionFragmentComponent extends InjectorComponent<DescriptionFragment> {

    @Subcomponent.Builder
    interface Builder extends InjectorComponent.Builder<DescriptionFragmentComponent, DescriptionFragmentModule> {

    }

}
