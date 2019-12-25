package com.situ.project.utils;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IView> implements IPresenter {

    protected Reference<V> ViewReference;

    public BasePresenter(V view){
        attachView(view);
    }

    public void attachView(V view) {
        ViewReference = new WeakReference<>(view);
    }

    protected V getView(){
        if(ViewReference!=null){
            return ViewReference.get();
        }
        return null;
    }

    public boolean isViewAttached() {
        return ViewReference != null && ViewReference.get() != null;
    }

    @Override
    public void detachView() {
        if (ViewReference != null) {
            ViewReference.clear();
            ViewReference = null;
        }
    }
}
