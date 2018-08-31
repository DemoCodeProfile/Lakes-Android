package com.vadim.lakes.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.vadim.lakes.App;
import com.vadim.lakes.di.common.InjectorComponent;

public abstract class BaseFragment<T extends InjectorComponent> extends Fragment {

    protected abstract InjectorComponent.Module getModule();

    protected abstract void setComponent(T component);

    T mComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        mComponent = (T) App.getInstance().getAppHandler().getInjectorComponent(this.getClass(), getModule());
        setComponent(mComponent);
        mComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        App.getInstance().getAppHandler().releaseInjectorComponent(this.getClass());
    }


}
