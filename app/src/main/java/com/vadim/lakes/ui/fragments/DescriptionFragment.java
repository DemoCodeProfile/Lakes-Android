package com.vadim.lakes.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vadim.lakes.R;
import com.vadim.lakes.di.common.InjectorComponent;
import com.vadim.lakes.di.fragments.DescriptionFragmentComponent;
import com.vadim.lakes.di.fragments.DescriptionFragmentModule;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.ui.activities.MainActivity;
import com.vadim.lakes.ui.base.BaseFragment;
import com.vadim.lakes.ui.presenters.fragments.DescriptionFragmentPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DescriptionFragment extends BaseFragment<DescriptionFragmentComponent> implements IDescriptionFragment.View {

    @Inject
    DescriptionFragmentPresenter mPresenter;

    Unbinder mUnbinder;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.description)
    TextView mDescription;


    private Integer mId = 1;

    @Override
    protected InjectorComponent.Module getModule() {
        return new DescriptionFragmentModule(this);
    }

    @Override
    protected void setComponent(DescriptionFragmentComponent component) {

    }

    @SuppressLint("ResourceType")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).setTitle(R.string.lake);
        Bundle bundle = getArguments();
        if (bundle != null)
            mId = bundle.getInt(MapFragment.BUNDLE_ID_LAKE, 1);
        mPresenter.onLoadData(mId);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        backButton(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setView(Lake lake) {
        if(lake!=null){
            mTitle.setText(lake.getTitle());
            mDescription.setText(lake.getDescription());
            ((MainActivity)getActivity()).setImage(lake.getImg());
            ((MainActivity)getActivity()).setTitle(lake.getTitle());

        }
    }

    @Override
    public void setErrorLoadingData(String error) {
        Log.e("Error Loading:", error);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }


    public void backButton(Boolean isShow) {
        setHasOptionsMenu(isShow);
        ((MainActivity) getActivity()).backButton(isShow);
    }

}
