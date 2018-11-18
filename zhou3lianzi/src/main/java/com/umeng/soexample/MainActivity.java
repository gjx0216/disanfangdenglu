package com.umeng.soexample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.mvp.LoginView;
import com.umeng.soexample.mvp.Loginpresenter;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();
    private EditText mName;
    private EditText mPwd;
    private ImageView mQq;
    private ImageView mWeixin;
    private Loginpresenter mLoginpresenter;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.editname);
        mPwd = findViewById(R.id.editpwd);
        mLogin = findViewById(R.id.login);
        mQq = findViewById(R.id.qq);
        mWeixin = findViewById(R.id.weixin);

        mQq.setOnClickListener(this);
        mWeixin.setOnClickListener(this);
        mLoginpresenter = new Loginpresenter(this);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = mName.getText().toString().trim();
                String pwd1 = mPwd.getText().toString().trim();
                mLoginpresenter.login(name1, pwd1);
            }
        });
    }
//==============================================================================

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qq:
                authorization(SHARE_MEDIA.QQ);
                //startActivity(new Intent(MainActivity.this, TwoActivity.class));
                break;
            case R.id.weixin:
                authorization(SHARE_MEDIA.WEIXIN);

                break;
        }
    }

    //授权方法
    public void authorization(SHARE_MEDIA share_media) {
        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.d(TAG, "onstart" + "授权开始");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                //Toast.makeText(getApplicationContext(), "12156456", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onstart" + "123456465464");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");
                Toast.makeText(getApplicationContext(), "name=" + name + ",gender = " + gender, Toast.LENGTH_SHORT).show();
                tiao();
            }

            public void tiao() {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });

    }

    //分享监听
    public UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(getApplicationContext(), "成功了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    //===============================================================================
    @Override
    public void onSuccess(String result) {
        startActivity(new Intent(MainActivity.this, TwoActivity.class));
        finish();

    }

    @Override
    public void onFailer(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


}
