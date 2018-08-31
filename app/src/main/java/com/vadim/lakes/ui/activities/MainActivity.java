package com.vadim.lakes.ui.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vadim.lakes.App;
import com.vadim.lakes.R;
import com.vadim.lakes.di.activities.MainActivityComponent;
import com.vadim.lakes.di.activities.MainActivityModule;
import com.vadim.lakes.ui.fragments.MapFragment;
import com.vadim.lakes.ui.presenters.activities.MainActivityPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainActivity.View {

    @Inject
    MainActivityPresenter mMainActivityPresenter;
    @Inject
    Picasso mPicasso;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.fragment)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.img)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainActivityComponent component = (MainActivityComponent) App.getInstance()
                .getAppHandler()
                .getInjectorComponent(MainActivity.class, new MainActivityModule(this));
        component.inject(this);
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setTitleEnabled(false);
        createFragment();
    }

    void createFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (fragment == null) {
            fragment = new MapFragment();
            addFragment(fragment, fragment.getClass().getSimpleName());
        }
    }

    public NestedScrollView getNestedScrollView() {
        return mNestedScrollView;
    }

    public void setImage(String url) {
        if (url != null) {
            mPicasso.load(url).into(mImageView);
        } else {
            mImageView.setImageResource(0);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainActivityPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMainActivityPresenter.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            App.getInstance().getAppHandler().releaseInjectorComponent(MainActivity.class);
        }
    }
}
