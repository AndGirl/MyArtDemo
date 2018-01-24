package com.ybj.arithmeticdemo;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by 杨阳洋 on 2018/1/24.
 * 选择排序:每一轮选出最轻量级的数据
 * 比较次数和轮数同冒泡排序
 *
 */

public class SelectSort {

    public static void main(String [] args){
        int[] array = createArray(10);

        System.out.println("排序前");
        System.out.println(Arrays.toString(array));

        selectSort(array);

        System.out.println("排序后");
        System.out.println(Arrays.toString(array));
    }

    private static void selectSort(int [] arg){

        for (int i = 0 ; i < arg.length -1 ; i ++){
            for (int j = i + 1 ; j < arg.length ; j ++){
                if(arg[i] > arg[j]) {
                    int temp = arg[j];
                    arg[j] = arg[i];
                    arg[i] = temp;
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
