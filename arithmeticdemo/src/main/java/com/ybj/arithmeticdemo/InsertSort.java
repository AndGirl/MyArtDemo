package com.ybj.arithmeticdemo;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by 杨阳洋 on 2018/1/24.
 * 插入排序:从下标为1的位置开始跟前面的值做对比
 * 至少比较n-1轮，如果值比下标值大，则当前位置与下一个位置的值交换。否则当前位置的值等于下标值
 */

public class InsertSort {

    public static void main(String [] args){
        int[] array = createArray(10);

        System.out.println("排序前");
        System.out.println(Arrays.toString(array));

        insertSort(array);

        System.out.println("排序后");
        System.out.println(Arrays.toString(array));
    }

    private static void insertSort(int [] arr){
        for (int i = 1 ; i < arr.length ; i ++){

            //和前面的值进行对比，判断谁大
            if(arr[i - 1] > arr[i]) {
                //更换位置
                int temp = arr[i];
                //k表示前面的值
                int k = i - 1;
                while (k >= 0 && arr[k] > temp){
                    arr[k + 1] = arr[k];
                    k -= 1;
                }
                arr[k + 1] = temp;
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
