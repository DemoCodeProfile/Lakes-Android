package com.vadim.lakes.di.app;

import android.content.Context;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vadim.lakes.data.JsonData;
import com.vadim.lakes.data.repositories.LakesRepositoryJson;
import com.vadim.lakes.di.activities.MainActivityComponent;
import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.fragments.DescriptionFragmentComponent;
import com.vadim.lakes.di.fragments.MapFragmentComponent;
import com.vadim.lakes.di.qualifiers.AppQualifier;
import com.vadim.lakes.di.scopes.AppScope;
import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.executors.MainThread;
import com.vadim.lakes.domain.executors.ThreadExecutor;
import com.vadim.lakes.domain.repositories.ILakesRepository;
import com.vadim.lakes.ui.activities.MainActivity;
import com.vadim.lakes.ui.fragments.DescriptionFragment;
import com.vadim.lakes.ui.fragments.MapFragment;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MainActivityComponent.class, DescriptionFragmentComponent.class, MapFragmentComponent.class})
public class AppModule {

    Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @AppScope
    @AppQualifier
    Context provideContext() {
        return mContext;
    }

    @Provides
    @IntoMap
    @ClassKey(MainActivity.class)
    InjectorComponent.Builder provideMainActivityComponentBuilder(MainActivityComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(DescriptionFragment.class)
    InjectorComponent.Builder provideDescriptionFragmentComponent(DescriptionFragmentComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(MapFragment.class)
    InjectorComponent.Builder provideMapFragmentComponent(MapFragmentComponent.Builder builder) {
        return builder;
    }

    @Provides
    @AppScope
    ILakesRepository provideLakesRepository(LakesRepositoryJson lakesRepositoryJson) {
        return lakesRepositoryJson;
    }

    @Provides
    @AppScope
    IMainThread provideMainThread(MainThread mainThread) {
        return mainThread;
    }

    @Provides
    @AppScope
    Executor provideExecutor(ThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @AppScope
    JsonData provideJsonData() {
        return new JsonData();
    }

    @Provides
    @AppScope
    Picasso providePicasso(@AppQualifier Context context) {
        return new Picasso.Builder(context).build();
    }
}
