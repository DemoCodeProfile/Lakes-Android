package com.vadim.lakes.ui.presenters.activities;

import com.vadim.lakes.ui.activities.IMainActivity;

public class MainActivityPresenter implements IMainActivity.Presenter {

    IMainActivity.View view;

    public MainActivityPresenter(IMainActivity.View view) {
        this.view = view;
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

}
