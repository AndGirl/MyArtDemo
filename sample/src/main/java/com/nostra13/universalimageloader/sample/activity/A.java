package com.nostra13.universalimageloader.sample.activity;

/**
 * Created by 杨阳洋 on 2018/5/10.
 */

public class A implements AutoCloseable{

    protected String qq;

    protected String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public A() {
        System.out.println("I am A");
    }

    public void say() throws Exception{
        throw new Exception("Exception A");
    }

    @Override
    public void close() throws Exception {
        System.out.println("A is CLOSED in the close.");
        throw new Exception("Unable to close the cage!");
    }
}
