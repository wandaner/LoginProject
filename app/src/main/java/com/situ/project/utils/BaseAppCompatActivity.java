package com.situ.project.utils;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseAppCompatActivity<P extends IPresenter> extends AppCompatActivity implements IView<AppCompatActivity> {

    protected P Presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Presenter = createPresenter();
    }

    @Override
    public AppCompatActivity getActivity() {
        return null;
    }

    public abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(Presenter!=null){
            Presenter.detachView();
            Presenter = null;
        }
    }
}
