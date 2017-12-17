package com.ybj.draweraddnavigationdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * 参考文献
 * http://www.jianshu.com/p/d2b1689a23bf
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView text = (TextView) toolbar.findViewById(R.id.titlebar);
        setSupportActionBar(toolbar);
        //去除默认显示的title
        if (text != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.title_name, R.string.title_name);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //去除navigationView的滚动条效果
        NavigationView slidingmenu = (NavigationView) findViewById(R.id.slidingmenu);
        View childAt = slidingmenu.getChildAt(0);
        if (childAt != null && childAt instanceof NavigationMenuView) {
            childAt.setVerticalScrollBarEnabled(false);
        }

        slidingmenu.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
