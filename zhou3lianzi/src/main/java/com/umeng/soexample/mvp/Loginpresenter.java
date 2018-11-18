package com.umeng.soexample.mvp;

import com.umeng.soexample.CallBack;

public class Loginpresenter {

    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public Loginpresenter(LoginView loginView) {
        this.mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    public void login(String name1, String pwd1) {
        mLoginModel.login(name1, pwd1, new CallBack() {
            @Override
            public void LoginonSuccess(String result) {
                mLoginView.onSuccess(result);
            }

            @Override
            public void onLoginFailer(String msg) {
                mLoginView.onFailer(msg);
            }
        });
    }

}
