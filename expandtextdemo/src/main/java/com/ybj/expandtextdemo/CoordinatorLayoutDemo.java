package com.ybj.expandtextdemo;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * CoordinatorLayout的简介
 * 1.功能
 * 作为顶层布局
 * 调度协调子布局
 * CoordinatorLayout使用新的思路通过协调调度子布局的形式实现触摸影响布局的形式产生动画效果。
 * CoordinatorLayout通过设置子View的 Behaviors来调度子View。
 * 系统（Support V7）提供了AppBarLayout.Behavior, AppBarLayout.ScrollingViewBehavior, FloatingActionButton.Behavior, SwipeDismissBehavior<V extends View> 等。
 *
 */

/**
 * 案列1：CoordinatorLayout与FloatingActionButton的使用
 */

public class CoordinatorLayoutDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_demo);

        findViewById(R.id.fab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(v,"FAB", BaseTransientBottomBar.LENGTH_SHORT)
                                .setAction("cancel", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(CoordinatorLayoutDemo.this, "消失了", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                    }
                });

    }
}
