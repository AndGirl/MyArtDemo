package com.ybj.coordinatorlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * CoordinatorLayout作为布局
 * 1.顶层布局
 * 2.协调子布局
 *
 * CoordinatorLayout使用新的思路通过协调调度子布局的形式实现触摸影响布局的形式产生动画效果。
 * CoordinatorLayout通过设置子View的 Behaviors来调度子View。
 * 系统（Support V7）提供了AppBarLayout.Behavior,
 * AppBarLayout.ScrollingViewBehavior,
 * FloatingActionButton.Behavior,
 * SwipeDismissBehavior<V extends View> 等
 *
 * 为了使得Toolbar有滑动效果，必须做到如下三点:
 * 1. CoordinatorLayout作为布局的父布局容器。
 * 2. 给需要滑动的组件设置 app:layout_scrollFlags=”scroll|enterAlways” 属性。
 * 3. 给滑动的组件设置app:layout_behavior属性
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v,"FloatinActionButton",Snackbar.LENGTH_LONG)
//                        .setAction("cancle", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(MainActivity.this, "cancle", Toast.LENGTH_SHORT).show();
//                            }
//                        }).show();
//            }
//        });

    }
}
