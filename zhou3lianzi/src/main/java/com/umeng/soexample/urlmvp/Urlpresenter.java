package com.umeng.soexample.urlmvp;

import com.umeng.soexample.mvp.LoginModel;
import com.umeng.soexample.mvp.LoginView;

public class Urlpresenter {
    private Urlview mUrlview;
    private final UrlModel mUrlModel;


    public Urlpresenter(Urlview urlview) {
        this.mUrlview = urlview;
        mUrlModel = new UrlModel();
    }

    public void getUrls(String url) {
        mUrlModel.url(url, new UrlModel.HttpUrl() {
            @Override
            public void getUrl(String s) {
                if (s != null) {
                    mUrlview.onSuccess(s);
                } else {
                    mUrlview.onSuccess("没有数据");
                }
            }
        });


    }
}
