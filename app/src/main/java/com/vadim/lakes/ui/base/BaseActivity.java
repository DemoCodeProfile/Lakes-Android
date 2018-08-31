package com.vadim.lakes.ui.base;

public interface BaseActivity {

    interface View {

    }

    interface Presenter {
        void onStop();
        void onStart();
    }
}
