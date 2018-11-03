package com.example.panzq.simpleandroid_8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class LayoutAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        ListView list_view = ((ListView)findViewById(R.id.list_view));
        String[] content = new String[]{"Android","C","C++","Java","Python"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, content);
        list_view.setAdapter(adapter);
        /*list_view.setAdapter(getAdapter());
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_item);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        list_view.setLayoutAnimation(controller);*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
        overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
    }
}
