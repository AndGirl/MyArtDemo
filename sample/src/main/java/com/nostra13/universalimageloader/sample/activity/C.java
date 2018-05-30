package com.nostra13.universalimageloader.sample.activity;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by 杨阳洋 on 2018/5/12.
 */

public class C implements Closeable {

    public C() {
        System.out.println("I am C");
    }
    public void say() throws Exception{
        throw new Exception("Exception C");
    }
    @Override
    public void close() throws IOException {
        System.out.println("C is CLOSED in the close.");
    }
}
