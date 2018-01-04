package com.ybj.guidedemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

/**
 * https://github.com/apl-devs/AppIntro
 */

public class MainActivity extends AppIntro2{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setZoomAnimation();

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("Welcome!");
        sliderPage1.setDescription("This is a demo of the AppIntro library, using the second layout.");
        sliderPage1.setImageDrawable(R.drawable.guide_1);
        sliderPage1.setBgColor(Color.RED);
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("Clean App Intros");
        sliderPage2.setDescription("This library offers developers the ability to add clean app intros at the start of their apps.");
        sliderPage2.setImageDrawable(R.drawable.guide_2);
        sliderPage2.setBgColor(Color.BLUE);
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Simple, yet Customizable");
        sliderPage3.setDescription("The library offers a lot of customization, while keeping it simple for those that like simple.");
        sliderPage3.setImageDrawable(R.drawable.guide_3);
        sliderPage3.setBgColor(Color.GREEN);
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("Explore");
        sliderPage4.setDescription("Feel free to explore the rest of the library demo!");
        sliderPage4.setImageDrawable(R.drawable.guide_1);
        sliderPage4.setBgColor(Color.YELLOW);
        addSlide(AppIntroFragment.newInstance(sliderPage4));

//        addSlide(new GamingFragment());
//        addSlide(new CategoryFragment());
//        addSlide(new RankingFragment());
//
//        setBarColor(Color.parseColor("#ffffffff"));
//
//        // Hide Skip/Done button.
        setIndicatorColor(getResources().getColor(R.color.holo_red_dark),getResources().getColor(R.color.colorAccent));
        showStatusBar(false);
        showSkipButton(false);
        setProgressButtonEnabled(false);
//
//        setVibrate(true);
//        setVibrateIntensity(30);
//        setFadeAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        Log.e("TAG", "onSkipPressed");
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        Log.e("TAG", "onDonePressed");
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
        Log.e("TAG", "onSlideChanged");
    }

}
