package com.vadim.lakes.domain.executors;

import com.vadim.lakes.domain.interactors.base.AbstractInteractor;

public interface Executor {
    void execute(final AbstractInteractor interactor);
}
