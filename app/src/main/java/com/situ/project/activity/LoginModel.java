package com.situ.project.activity;

import com.situ.project.utils.ICallBack;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LoginModel implements LoginContacts.ILoginModel {
    @Override
    public void sendSmsCode(String phoneNumber, final ICallBack callBack) {
        Observable
                .just(1)
                .delay(1500L, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        callBack.success();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.failure();
                    }
                });
    }

    @Override
    public void doLogin(String phoneNumber, String smsCode, final ICallBack callBack) {
        Observable
                .just(1)
                .delay(1500L, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        callBack.success();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.failure();
                    }
                });
    }
}
