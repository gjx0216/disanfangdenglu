
package com.umeng.soexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.urlmvp.Urlpresenter;
import com.umeng.soexample.urlmvp.Urlview;

import java.util.List;

public class TwoActivity extends AppCompatActivity implements Urlview {

    private String path = "https://gank.io/api/data/福利/10/2";
    private ImageView mIcon;
    private TextView mNickname;
    private RecyclerView mRecyclerView;
    private Button mInsert;
    private Button mRemove;
    private Urlpresenter mUrlpresenter;
    private Entity mEntity;
    private List<Entity.ResultsBean> mList;
    private RecyAdapter mRecyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
        initData();
    }

    private void initView() {
        mIcon = findViewById(R.id.head_por);
        mNickname = findViewById(R.id.nickname);
        mRecyclerView = findViewById(R.id.recycle);
        mInsert = findViewById(R.id.Add);
        mRemove = findViewById(R.id.del);
    }

    private void initData() {
        mUrlpresenter = new Urlpresenter(this);
        mUrlpresenter.getUrls(path);
        //2.声名为瀑布流的布局方式: 3列,垂直方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        //3.为recyclerView设置布局管理器
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyAdapter.add(0);
            }
        });
        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mList.size() > 1) {
                    mRecyAdapter.remove(0);
                } else {
                    Toast.makeText(getApplicationContext(), "这是最后一条了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Entity mEntity = gson.fromJson(result, Entity.class);
        mList = mEntity.getResults();

        mRecyAdapter = new RecyAdapter(getApplicationContext(), mList);
        mRecyclerView.setAdapter(mRecyAdapter);
        mRecyAdapter.setItemClikListener(new RecyAdapter.onItenClicklistener() {
            @Override
            public void onItemClik(String data) {
                Intent intent = new Intent(TwoActivity.this, ThrActivity.class);
                intent.putExtra("ooo",data);
                startActivity(intent);
            }
        });


    }

    @Override
    public void Failer(String msg) {
        Toast.makeText(getApplicationContext(), "没有数据", Toast.LENGTH_SHORT).show();
    }
}
