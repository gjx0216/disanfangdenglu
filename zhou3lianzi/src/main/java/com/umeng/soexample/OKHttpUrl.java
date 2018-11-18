package com.umeng.soexample;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OKHttpUrl {

    private static final String METHOD_GET = "GET";
    private static OkHttpClient okHttpClient;


    //私有化构造方法
    private OKHttpUrl() {

    }

    //初始化
    public static void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(3000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(3000, TimeUnit.MILLISECONDS);
        okHttpClient = new OkHttpClient();
    }

    //创建一个方法
    public static Request createRequest(String url, String method) {
        Request.Builder builder = new Request.Builder().url(url);
        Request request = null;
        switch (method) {
            case METHOD_GET:
                request = builder.build();
                break;
        }
        return request;
    }

    //异步的方法
    public static void enquestSet(String url, Callback callback) {
        Request request = createRequest(url, METHOD_GET);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
