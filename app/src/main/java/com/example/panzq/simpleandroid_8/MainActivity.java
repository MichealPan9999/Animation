package com.example.panzq.simpleandroid_8;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
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
               overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
            }
        });
        btn4 = findViewById(R.id.button4);
        final AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.attrs_anim);
        //set.setTarget(btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set.start();
                //performAnimate();
                //performAnimate();
                performAnimate(btn4,btn4.getWidth(),500);
            }
        });

    }
    /*private void performAnimate() {
        ObjectAnimator.ofInt(btn4,"width",1000).setDuration(5000).start();
    }*/

    private void performAnimate() {
        ViewWrapper wrapper = new ViewWrapper(btn4);
        ObjectAnimator.ofInt(wrapper,"width",500).setDuration(5000).start();
    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            this.mTarget = target;
        }
        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }
        public void setWidth(int width){
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
    private void performAnimate(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1,100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个IntEvaluator对象，下面方便估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                //获得当前动画的进度值，整数，1~100之间
                int currentValue = (Integer)animator.getAnimatedValue();
                Log.d("panzqww", "current value: " + currentValue);

                //获得当前进度栈整个动画过程的比例，浮点型，0~1之间
                float fraction = animator.getAnimatedFraction();

                //直接用用整型估值器，通过比例计算出宽度，然后再设给Button
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(5000).start();
    }

    /*@Override
    public void onClick(View v) {
        if (v == mButton){
            performAnimate(mButton, mButton.getWidth(), 500);
        }
    }*/

}
