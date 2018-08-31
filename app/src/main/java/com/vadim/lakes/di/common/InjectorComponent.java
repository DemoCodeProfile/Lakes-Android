package com.vadim.lakes.di.common;

public interface InjectorComponent<A> {
    void inject(A injector);


    interface Builder<A extends InjectorComponent, M extends InjectorComponent.Module> {
        A build();
        Builder<A, M> builder(M module);
    }

    interface Module {

    }
}