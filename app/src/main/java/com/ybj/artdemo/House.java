package com.ybj.artdemo;

/**
 * Created by 杨阳洋 on 2017/12/13.
 */

public class House {

    Window w1 = new Window(1);

    House() {
        System.out.print("House()");
        w3 = new Window(33);
    }

    Window w2 = new Window(2);

    void f() {
        System.out.print("f()");
    }

    Window w3 = new Window(3);

}
