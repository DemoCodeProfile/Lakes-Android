package com.vadim.lakes.ui.fragments;

import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.ui.base.BaseActivity;

public interface IDescriptionFragment {

    interface View extends BaseActivity.View{
        void setView(Lake lake);
        void setErrorLoadingData(String error);
    }

    interface Presenter extends BaseActivity.Presenter {
        void onLoadData(int id);
        void bindView(IDescriptionFragment.View view);
        void unbindView();
    }

}
