package com.vadim.lakes.ui.fragments;

import com.google.android.gms.maps.model.Marker;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.ui.base.BaseActivity;

import java.util.List;

public interface IMapFragment {
    interface View extends BaseActivity.View{
        void setMarkers(List<Lake> lakes);
        void setErrorLoadingData(String error);
    }

    interface Presenter extends BaseActivity.Presenter {
        void onMapReady();
        void onMarkerCreated(Marker marker, Lake lake);
        Lake onMarkerClick(Marker marker);
        void bindView(IMapFragment.View view);
        void unbindView();
    }
}
