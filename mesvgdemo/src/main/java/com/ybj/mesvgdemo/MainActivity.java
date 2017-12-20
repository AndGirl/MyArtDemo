package com.ybj.mesvgdemo;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.eftimoff.androipathview.PathView;

/**
 * SVG是基于XML的描述
 * https://github.com/geftimov/android-pathview
 * http://www.iconfont.cn
 * http://oss.chengxingyao.cn/svg2android/index.html
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PathView pathView = (PathView) findViewById(R.id.pathView);

        TextView text = (TextView)findViewById(R.id.text);

        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");

        text.setTypeface(iconfont);

//        final Path path = new Path();
//
//        pathView.setPath(path);
//        final Path path = makeConvexArrow(50, 100);
//        pathView.setPath(path);
        //       pathView.setFillAfter(true);
        //  pathView.useNaturalColors();
//        pathView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pathView.getPathAnimator().
//                        //pathView.getSequentialPathAnimator().
//                                delay(100).
//                        duration(1500).
//                        interpolator(new AccelerateDecelerateInterpolator()).
//                        start();
//            }
//        });

        //开启图片
//        pathView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pathView.getPathAnimator()
//                        .start();
//            }
//        });
//
//        //使用svg提供的默认图片
//        //pathView.useNaturalColors();
//
//        //执行完动画是否需要保持
//        pathView.setFillAfter(true);


    }
}
