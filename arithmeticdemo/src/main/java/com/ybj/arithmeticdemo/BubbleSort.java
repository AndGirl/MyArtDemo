package com.ybj.arithmeticdemo;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by 杨阳洋 on 2018/1/24.
 * 冒泡排序：相邻两个数据做对比
 * 如果有n个数据需要（n  > 2）比较次数：(n * (n - 1)) / 2
 * 比较轮数：n - 1轮
 */

public class BubbleSort {

    public static void main(String [] args){
        int[] array = createArray(100);

        System.out.println("排序前");
        System.out.println(Arrays.toString(array));

        bubbleSort(array);

        System.out.println("排序后");
        System.out.println(Arrays.toString(array));
    }

    private static void bubbleSort(int [] arg){
        for (int i = 0 ; i < arg.length - 1 ;i ++){
            for (int j = 0 ; j < arg.length - 1 - i;j ++ ){
                if(arg[j] > arg[j + 1]) {
                    int temp = arg[j];
                    arg[j] = arg[j + 1];
                    arg[j + 1] = temp;
                }
            }
        }
    }

    private static int[] createArray(int length){
        int [] arr = new int[length];
        Random random = new Random(47);
        for (int i = 0 ; i < length ; i ++ ){
            arr[i] = random.nextInt(length);
        }
        return arr;
    }

}
