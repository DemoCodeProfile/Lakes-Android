package com.vadim.lakes.di.app;

import android.content.Context;

import com.vadim.lakes.di.common.InjectorComponent;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;


public class AppHandler {

    private final Context mContext;

    @Inject
    Map<Class<?>, Provider<InjectorComponent.Builder>> mBuilders;

    private Map<Class<?>, InjectorComponent> mComponents;

    private AppComponent mAppComponent;

    public AppHandler(Context context) {
        this.mContext = context;
    }

    public void init() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(mContext)).build();
        mAppComponent.inject(this);
        mComponents = new HashMap<>();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    public InjectorComponent getInjectorComponent(Class<?> cls) {
        return getInjectorComponent(cls, null);
    }

    public InjectorComponent getInjectorComponent(Class<?> cls, InjectorComponent.Module module) {
        InjectorComponent component = mComponents.get(cls);
        if (component == null) {
            InjectorComponent.Builder builder = mBuilders.get(cls).get();
            if (module != null) {
                builder.builder(module);
            }
            component = builder.build();
            mComponents.put(cls, component);
        }
        return component;
    }

    public void releaseInjectorComponent(Class<?> cls) {
        mComponents.put(cls, null);
    }


}
