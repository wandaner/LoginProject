package com.situ.project.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.situ.project.R;
import com.situ.project.utils.BaseAppCompatActivity;

public class LoginActivity extends BaseAppCompatActivity<LoginPresenter> implements View.OnClickListener, LoginContacts.ILoginView {

    private EditText etPhoneNumber;
    private EditText etSmsCode;
    private TextView tvSendSmsCode;
    private Button btLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
        etPhoneNumber = findViewById(R.id.et_phoneNum);
        etSmsCode = findViewById(R.id.et_smsCode);
        tvSendSmsCode = findViewById(R.id.tv_sendSms);
        btLogin = findViewById(R.id.bt_login);

        btLogin.setOnClickListener(this);
        tvSendSmsCode.setOnClickListener(this);
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().startsWith("0")){
                    etPhoneNumber.setText("");
                    etPhoneNumber.setSelection(0);
                }else {
                    setSendSmsCodeButtonEnable(s.length()==11);
                }
            }
        });
        etSmsCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setLoginBtEnable(s.length()==4&&etPhoneNumber.getText().length()==11);
            }
        });
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sendSms:
                Presenter.sendSmsCode(etPhoneNumber.getText().toString());
                break;
            case R.id.bt_login:
                Presenter.doLogin(etPhoneNumber.getText().toString(),etSmsCode.getText().toString());
                break;
        }
    }


    @Override
    public void setSendSmsCodeButtonEnable(boolean b) {
        if(tvSendSmsCode.isEnabled()!=b){
            tvSendSmsCode.setEnabled(b);
            tvSendSmsCode.setTextColor(getResources().getColor(b?R.color.colorPrimary:R.color.colorHalfBlack));
        }
    }

    @Override
    public void resetSendSmsCodeButtonState() {
        tvSendSmsCode.setText(R.string.str_sendSmsCode);
        setSendSmsCodeButtonEnable(etPhoneNumber.getText().length()==11);
    }

    @Override
    public void setSendSmsCodeButtonText(String text) {
        tvSendSmsCode.setText(text);
    }

    @Override
    public void showLoadingDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(TextUtils.isEmpty(msg)?"加载中..":msg);    //设置内容
        progressDialog.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        progressDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        if (progressDialog != null){
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void toastMessage(String msg) {
        Toast.makeText(LoginActivity.this,TextUtils.isEmpty(msg)?"我只是个宝宝呀！":msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @SuppressLint("ResourceAsColor")
    public void setLoginBtEnable(boolean b){
        if(btLogin.isEnabled()!=b) {
            btLogin.setEnabled(b);
            btLogin.setTextColor(getResources().getColor(b?R.color.colorWhite:R.color.colorHalfWhite));
        }
    }

}
