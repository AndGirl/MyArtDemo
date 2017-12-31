package com.ybj.mydagger2demo.anotation;

import android.util.Log;

import com.ybj.mydagger2demo.ano.Release;
import com.ybj.mydagger2demo.ano.Test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Module
public class AnotationModule {

    @Test
    @Provides
    public TestAnotatio provideTestAntatio_dev(){
        Log.e("TAG", "dev");
        return new TestAnotatio();
    }

    @Release
    @Provides
    public TestAnotatio provideTestAntatio_realease(){
        Log.e("TAG", "realease");
        return new TestAnotatio();
    }

}
