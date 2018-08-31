package com.vadim.lakes.domain.interactors;

import com.vadim.lakes.domain.models.Lake;

public interface IGetByIdLakeInteractor {
    interface Callback {
        void onLakeReceived(Lake lake);
        void onError(String error);
    }
}
