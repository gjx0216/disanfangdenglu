package com.umeng.soexample.urlmvp;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.umeng.soexample.OKHttpUrl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UrlModel {

    private Context mContext;

    public void url(String url, final HttpUrl httpUrl) {
        //调用封装好的okhttp的静态方法
        OKHttpUrl.enquestSet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }

            //运行在子线程里  做网络请求的,不能更新UI
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取请求的数据
                int code = response.code();
                if (code == 200) {
                    ResponseBody body = response.body();
                    final String s = body.string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //回调
                            httpUrl.getUrl(s);
                        }
                    });
                }
            }
        });
    }

    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public interface HttpUrl {
        void getUrl(String s);
    }
}
