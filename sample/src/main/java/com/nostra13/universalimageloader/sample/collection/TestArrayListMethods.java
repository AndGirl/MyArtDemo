package com.nostra13.universalimageloader.sample.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 杨阳洋 on 2018/4/25.
 * 测试ArrayList中的方法
 */

public class TestArrayListMethods {

    public static void main(String[] args) {
        final int N = 10;
        Object obj = new Object();
        ArrayList list = new ArrayList();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= N; i++) {
            list.add(obj);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("没有调用ensureCapacity()方法所用时间：" + (endTime - startTime) + "ms");

        list = new ArrayList();
        startTime = System.currentTimeMillis();
        for (int i = 0; i <= N; i++) {
            list.add(obj);
        }
        endTime = System.currentTimeMillis();
        System.out.println("有调用ensureCapacity()方法所用时间：" + (endTime - startTime) + "ms");

        ArrayList copyList = new ArrayList();

        list.isEmpty();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            copyList.add(iterator.next());
        }

        list.lastIndexOf(obj);

        String [] str = new String[list.size()];


        list.retainAll(list);
        list.size();
        List subList = list.subList(0, 5);
//        subList.add(null);
//        list.add(null);

        subList.toString();
        Object[] objects = list.toArray();
        int a = objects.length;
        Object[] toArray = list.toArray(str);
        int b = toArray.length;
        list.trimToSize();
        list.toString();
        CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();//专门用于并发编程

    }

}
