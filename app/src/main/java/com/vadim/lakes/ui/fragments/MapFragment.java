package com.vadim.lakes.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vadim.lakes.R;
import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.fragments.MapFragmentComponent;
import com.vadim.lakes.di.fragments.MapFragmentModule;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.ui.activities.MainActivity;
import com.vadim.lakes.ui.base.BaseFragment;
import com.vadim.lakes.ui.presenters.fragments.MapFragmentPresenter;
import com.vadim.lakes.ui.widgets.ScrollGoogleMap;

import java.util.List;

import javax.inject.Inject;

public class MapFragment extends BaseFragment<MapFragmentComponent> implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, IMapFragment.View {

    @Inject
    MapFragmentPresenter mPresenter;

    private ScrollGoogleMap mMapFragment;
    private GoogleMap mMap;

    public final static String BUNDLE_ID_LAKE = "id_lake";

    @Override
    protected InjectorComponent.Module getModule() {
        return new MapFragmentModule(this);
    }

    @Override
    protected void setComponent(MapFragmentComponent component) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.bindView(this);
        mMapFragment = (ScrollGoogleMap) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mMapFragment.setListener(new ScrollGoogleMap.OnTouchListener() {
            @Override
            public void onTouch() {
                ((MainActivity)getActivity()).getNestedScrollView().requestDisallowInterceptTouchEvent(true);
            }
        });
        mMapFragment.getMapAsync(this);
        backButton(false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).setTitle(R.string.map);
        ((MainActivity)getActivity()).setImage(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbindView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        mPresenter.onMapReady();
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Lake lake = mPresenter.onMarkerClick(marker);
        if (lake != null) {
            DescriptionFragment fragment = new DescriptionFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(BUNDLE_ID_LAKE, lake.getId());
            fragment.setArguments(bundle);
            ((MainActivity) getActivity()).replaceFragment(fragment, fragment.getClass().getSimpleName(), "DescriptionSegue");
        }
    }

    @Override
    public void setMarkers(List<Lake> lakes) {
        for (Lake lake : lakes) {
            Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lake.getLat(), lake.getLon())).title(lake.getTitle()));
            mPresenter.onMarkerCreated(marker, lake);
        }
    }

    @Override
    public void setErrorLoadingData(String error) {
        Log.e("Error Loading:", error);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    public void backButton(Boolean isShow) {
        setHasOptionsMenu(isShow);
        ((MainActivity)getActivity()).backButton(isShow);
    }
}
