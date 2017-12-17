package com.cniao5.svgdemo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.cniao5.svgdemo.typeface.Cniao5Font;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.ionicons_typeface_library.Ionicons;

public class IconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);


        Drawable drawable =  new IconicsDrawable(this)
                .icon(Cniao5Font.Icon.cniao_cart)
                .color(Color.BLACK)
                .sizeDp(50);



        ((ImageView)this.findViewById(R.id.img)).setImageDrawable(drawable);







    }
}
