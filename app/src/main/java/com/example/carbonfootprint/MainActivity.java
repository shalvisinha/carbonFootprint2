package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static int TIME_OUT=3000;
    Animation leftanim,rightanim , rotanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent start = new Intent(MainActivity.this,login.class);
                startActivity(start);
                finish();
            }
        },TIME_OUT);
        leftanim= AnimationUtils.loadAnimation(this,R.anim.left);
        rightanim= AnimationUtils.loadAnimation(this,R.anim.right);
        rotanim = AnimationUtils.loadAnimation(this,R.anim.spin);
        ImageView maintext = (ImageView)findViewById(R.id.name);
        TextView desc = findViewById(R.id.des);
        maintext.setAnimation(leftanim);
        desc.setAnimation(rightanim);
        ImageView earth = (ImageView)findViewById(R.id.imageView);
        earth.setAnimation(rotanim);

    }
}
