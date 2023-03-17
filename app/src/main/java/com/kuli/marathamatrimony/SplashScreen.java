package com.kuli.marathamatrimony;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kuli.marathamatrimony.Activity.Onboarding;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN=4000;
    Animation topAnim, bottomAnim;
    ImageView image1,image2,image3,image4,image5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.splash_screen);

        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_animation);
        image1=findViewById(R.id.imageview1);
        image2=findViewById(R.id.imageview2);
        image3=findViewById(R.id.imageview3);
        image1.setAnimation(topAnim);
        image2.setAnimation(topAnim);
        image3.setAnimation(topAnim);


        bottomAnim= AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        image4=findViewById(R.id.imageview4);
        image5=findViewById(R.id.imageview5);
        image4.setAnimation(bottomAnim);
        image5.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(SplashScreen.this, Onboarding.class);
                startActivity(intent);
                finish();
            }

        },SPLASH_SCREEN);

    }
}
