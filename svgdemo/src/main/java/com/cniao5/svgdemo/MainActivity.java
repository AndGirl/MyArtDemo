package com.cniao5.svgdemo;

import android.graphics.Path;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"iconfont.ttf");

        TextView textView = (TextView) this.findViewById(R.id.text_icon);

        textView.setTypeface(typeface);






    }
}
