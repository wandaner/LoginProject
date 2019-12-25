package com.situ.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.situ.project.utils.ICallBack;
import com.situ.project.utils.IModel;
import com.situ.project.utils.IPresenter;
import com.situ.project.utils.IView;

public class LoginContacts {

    public interface ILoginView extends IView<AppCompatActivity>{
        void setSendSmsCodeButtonEnable(boolean b);
        void resetSendSmsCodeButtonState();
        void setSendSmsCodeButtonText(String text);
        void showLoadingDialog(String msg);
        void hideLoadingDialog();
        void toastMessage(String msg);
        void finishActivity();
    }

    public interface ILoginPresenter extends IPresenter{
        void sendSmsCode(String phoneNumber);
        void doLogin(String phoneNumber,String smsCode);

    }

    public interface ILoginModel extends IModel{
        void sendSmsCode(String phoneNumber, ICallBack callBack);
        void doLogin(String phoneNumber,String smsCode,ICallBack callBack);
    }
}
