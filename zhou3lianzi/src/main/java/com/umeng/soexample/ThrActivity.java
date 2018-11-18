package com.umeng.soexample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ThrActivity extends AppCompatActivity {

    private ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr);
        mImageView2 = findViewById(R.id.img1);

        Intent intent1 = getIntent();
        String ooo = intent1.getStringExtra("ooo");
        Picasso.with(ThrActivity.this).load(ooo).into(mImageView2);


        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(v,"translationY",200);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(v,"alpha",0,1);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(v,"rotation",360);
                ObjectAnimator animator4 = ObjectAnimator.ofFloat(v,"scaleX",2,1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator1,animator2,animator3,animator4);
                set.setDuration(3000);
                set.start();
            }
        });
    }
}
