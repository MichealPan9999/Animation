package com.example.panzq.simpleandroid_8;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        imageView1 = findViewById(R.id.imageview1);
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.startAnimation(animation);
            }
        });
        btn2 = findViewById(R.id.button2);
        btn2.setBackgroundResource(R.drawable.frameanimation);
        final AnimationDrawable drawable = (AnimationDrawable) btn2.getBackground();
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawable.start();
            }
        });
        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,LayoutAnimationActivity.class);
               startActivity(intent);
            }
        });
    }
}
