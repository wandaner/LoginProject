package com.situ.project.activity;

import com.situ.project.Tools.Tools;
import com.situ.project.utils.BasePresenter;
import com.situ.project.utils.ICallBack;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class LoginPresenter extends BasePresenter<LoginContacts.ILoginView> implements LoginContacts.ILoginPresenter {
    private LoginModel model;
    public LoginPresenter(LoginContacts.ILoginView view) {
        super(view);
        model = new LoginModel();
    }

    @Override
    public void sendSmsCode(String phoneNumber) {
        if(!Tools.CheckMobilePhoneNum(phoneNumber)){
            if(isViewAttached()) {
                getView().toastMessage("请填写正确的手机号码");
            }
        }else {
            if(isViewAttached()) {
                getView().showLoadingDialog("验证码发送中...");
            }
            model.sendSmsCode(phoneNumber, new ICallBack() {
                @Override
                public void success() {
                    if(isViewAttached()) {
                        getView().hideLoadingDialog();
                        getView().toastMessage("验证码发送成功");
                        updateSendSmsCodeBt();
                    }
                }

                @Override
                public void failure() {
                    if(isViewAttached()) {
                        getView().hideLoadingDialog();
                        getView().toastMessage("验证码发送失败");
                    }
                }
            });
        }
    }

    @Override
    public void doLogin(String phoneNumber, String smsCode) {
        if(isViewAttached()) {
            getView().toastMessage("登录中...");
        }
        model.doLogin(phoneNumber, smsCode, new ICallBack() {
            @Override
            public void success() {
                if(isViewAttached()) {
                    getView().hideLoadingDialog();
                    getView().toastMessage("登录成功");
                    getView().finishActivity();
                }
            }

            @Override
            public void failure() {
                if(isViewAttached()) {
                    getView().hideLoadingDialog();
                    getView().toastMessage("登录失败");
                }
            }
        });
    }

    public void updateSendSmsCodeBt() {
        final int count = 60;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count - aLong-1;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if(isViewAttached()){
                            getView().setSendSmsCodeButtonEnable(false);
                        }
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if(isViewAttached()) {
                            getView().setSendSmsCodeButtonText(aLong + "s");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().resetSendSmsCodeButtonState();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(isViewAttached()) {
                            getView().resetSendSmsCodeButtonState();
                        }
                    }
                });
    }
}
