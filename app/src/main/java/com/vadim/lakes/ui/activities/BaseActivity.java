package com.vadim.lakes.ui.activities;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.vadim.lakes.R;

public abstract class BaseActivity extends AppCompatActivity {


    public void addFragment(@NonNull Fragment fragment,
                            @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    public void replaceFragment(@NonNull Fragment fragment,
                                @NonNull String fragmentTag,
                                @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

    public void backButton(Boolean isShow){
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
    }

    public void setTitle(String title){
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @SuppressLint("ResourceType")
    public void setTitle(@IdRes int idString){
        getSupportActionBar().setTitle(idString);
    }

}
