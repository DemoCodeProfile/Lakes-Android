package com.vadim.lakes.domain.interactors;

import com.vadim.lakes.domain.interactors.base.Interactor;
import com.vadim.lakes.domain.models.Lake;

import java.util.List;

public interface IGetAllLakesInteractor extends Interactor {
    interface Callback {
        void onLakesReceived(List<Lake> lakes);
        void onError(String error);
    }
}
