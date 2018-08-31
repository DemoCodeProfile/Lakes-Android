package com.vadim.lakes.di.app;

import com.vadim.lakes.di.scopes.AppScope;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(AppHandler appHandler);
}
