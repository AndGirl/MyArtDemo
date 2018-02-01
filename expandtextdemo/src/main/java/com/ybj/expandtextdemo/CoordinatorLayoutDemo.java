package com.ybj.expandtextdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * CoordinatorLayout的简介
 * 1.功能
 * 作为顶层布局
 * 调度协调子布局
 * CoordinatorLayout使用新的思路通过协调调度子布局的形式实现触摸影响布局的形式产生动画效果。
 * CoordinatorLayout通过设置子View的 Behaviors来调度子View。
 * 系统（Support V7）提供了AppBarLayout.Behavior, AppBarLayout.ScrollingViewBehavior, FloatingActionButton.Behavior, SwipeDismissBehavior<V extends View> 等。
 */

/**
 * 案列1：CoordinatorLayout与FloatingActionButton的使用
 *
 * 案列2：AppBarLayout嵌套TabLayout
 * 效果显示，视图滚动时，Toolbar会隐藏，这个效果是Android Support Library里面,
 * 新增的CoordinatorLayout, AppBarLayout实现的。通过AppBarLayout的子视图的属性控制。
 * 观察AppBarLayout的子布局，Toobar有app:layout_scrollFlags属性，这就是控制滑动时视图效果的属性。
 * app:layout_scrollFlags有四个值：
 * scroll: 所有想滚动出屏幕的view都需要设置这个flag， 没有设置这个flag的view将被固定在屏幕顶部。例如，TabLayout 没有设置这个值，将会停留在屏幕顶部。
 * enterAlways: 设置这个flag时，向下的滚动都会导致该view变为可见，启用快速“返回模式”。
 * enterAlwaysCollapsed: 当你的视图已经设置minHeight属性又使用此标志时，你的视图只能已最小高度进入，只有当滚动视图到达顶部时才扩大到完整高度。
 * exitUntilCollapsed: 滚动退出屏幕，最后折叠在顶端。
 *
 * 为了使得Toolbar有滑动效果，必须做到如下三点:
 1. CoordinatorLayout作为布局的父布局容器。
 2. 给需要滑动的组件设置 app:layout_scrollFlags=”scroll|enterAlways” 属性。
 3. 给滑动的组件设置app:layout_behavior属性

 我查阅了网上资料说是只能适用于recyclerView和nestScrollView。我决定去看原因
 http://blog.csdn.net/xyz_lmn/article/details/48055919

 （重点补充）
  CoordinatorLayout之所以能够协调Children View之间的交互行为，主要就是依赖于NestedScrolling这个东邪，
 这里涉及到两个接口类NestedScrollingParent和NestedScrollingChild。CoordinatorLayout实现了前者，而
 CoordinatorLayout的Children核心之一，滑动性控件，实现了后者，所以才能够做出交互行为。

 RecyclerView实现的功能
 public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild

 *
 */

public class CoordinatorLayoutDemo extends AppCompatActivity {

    private String[] mListStr = {"姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com",
            "姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com",
            "姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com",
            "姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com",
            "姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_demo);

        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.appbar);

        /**
         * 滑动编译监听改变
         */
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("TAG", "滑动改变了");
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                Log.e("TAG", "滑动的最大偏移量" + totalScrollRange);
            }
        });

        findViewById(R.id.fab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(v, "FAB", BaseTransientBottomBar.LENGTH_SHORT)
                                .setAction("cancel", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(CoordinatorLayoutDemo.this, "消失了", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                    }
                });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);

        //API Level21及更高版本，为了支持NestedScrolling，所有控件的基类对外新增了一个方法
        //setNestedScrollingEnabled(boolean enabled)，所以，我们可以对ListView
        //稍作处理，就能在Android L及以上版本的系统中使用了。
        ListView listView = new ListView(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            listView.setNestedScrollingEnabled(true);
            //或者
            ViewCompat.setNestedScrollingEnabled(listView,true);
        }

//        listView.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, mListStr));
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new DemoFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tab 1";
                case 1:
                    return "Tab 2";
                case 2:
                    return "Tab 3";
            }
            return null;
        }
    }

}
