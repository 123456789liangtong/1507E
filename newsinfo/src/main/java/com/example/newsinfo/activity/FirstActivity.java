package com.example.newsinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.newsinfo.MainActivity;
import com.example.newsinfo.R;

public class FirstActivity extends AppCompatActivity {

    private ImageView splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //获取组件id
        splash = (ImageView) findViewById(R.id.splash);

        AlphaAnimation animation = new AlphaAnimation(1,0);
                animation.setDuration(3000);
                splash.startAnimation(animation);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //动画结束跳转
                        Intent intent = new Intent(FirstActivity.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
    }

