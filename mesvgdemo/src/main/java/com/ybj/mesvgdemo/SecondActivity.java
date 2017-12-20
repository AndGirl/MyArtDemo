package com.ybj.mesvgdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;

/**
 * https://github.com/mikepenz/Android-Iconic
 * 参考demo中进行自定义
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1.定义IconLayoutInflater
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //3代码google-material
        TextView text = (TextView) findViewById(R.id.text);

//        文本
//        TextView tv2 = findViewById(R.id.test5);
//        SpannableString sb = new SpannableString(tv2.getText());
//        IconicsDrawable d = new IconicsDrawable(this, FontAwesome.Icon.faw_android).sizeDp(48).paddingDp(4);
//        sb.setSpan(new ImageSpan(d, DynamicDrawableSpan.ALIGN_BOTTOM), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv2.setText(sb);

        //制作Emoji
//        SpannableString sb = new SpannableString(text.getText());
//        IconicsDrawable iconicsDrawable = new IconicsDrawable(this)
//                .icon(CustomFont.Icon.fon_test1)
//                .color(Color.RED)
//                .sizeDp(40);
//        sb.setSpan(new ImageSpan(iconicsDrawable, DynamicDrawableSpan.ALIGN_BOTTOM) ,0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        text.setText(sb);

        //文本添加
//        text.setText("{gmd-favorite}我是最爱");
//        text.setText("{uat_fon_test1}uart自定义");

//        //Set the icon of an ImageView (or something else) as drawable
//        ImageView iv2 = findViewById(R.id.test2);
//        iv2.setImageDrawable(new IconicsDrawable(this, FontAwesome.Icon.faw_thumbs_o_up)
//          .sizeDp(48).color(Color.parseColor("#aaFF0000")).contourWidthDp(1));
//
//        //Set the icon of an ImageView (or something else) as bitmap
//        ImageView iv3 = findViewById(R.id.test3);
//        iv3.setImageBitmap(new IconicsDrawable(this, FontAwesome.Icon.faw_android)
//          .sizeDpX(48).sizeDpY(32).paddingDp(4).roundedCornersDp(8).color(Color.parseColor("#deFF0000")).toBitmap());
//

        //有宽度的线条
//        ImageView imageVIew = (ImageView) findViewById(R.id.image);
//        imageVIew.setImageDrawable(new IconicsDrawable(this)
//                .icon(GoogleMaterial.Icon.gmd_keyboard_arrow_right)
//                .color(Color.RED)
//                .sizeDp(40).roundedCornersDp(8));



    }

    //2
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }
}
