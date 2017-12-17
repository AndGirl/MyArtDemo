package com.ybj.artdemo;

/**
 * Created by 杨阳洋 on 2017/12/11.
 */

public class TestVampire {

    static int a;
    static int b;
    static int c;
    static int d;

    public static void main(String [] args){

        funSum();

    }

    private static void funSum() {

        for (int i = 1000 ; i < 10000; i++){
            a = i / 1000;
            b = i /100 % 10;
            c = i / 10 % 10;
            d = i % 10;

            if(c == 0 && d == 0) {
                continue;
            }

            int ab = funNum(a,b); int cd = funNum(c,d);
            int ba = funNum(b,a); int dc =funNum(d,c);
            int ac = funNum(a,c); int bd = funNum(b,d);
            int ca = funNum(c,a); int db = funNum(d,b);
            int ad = funNum(a,d); int bc = funNum(b,c);
            int da = funNum(d,a); int cb = funNum(c,b);

            if(i == funNum(a,b) * funNum(c,d)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(a,b) * funNum(d,c)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(b,a) * funNum(d,c)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(b,a) * funNum(c,d)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(a,c) * funNum(b,d)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(a,c) * funNum(d,b)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(c,a) * funNum(b,d)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(c,a) * funNum(d,b)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(a,d) * funNum(b,c)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(a,d) * funNum(c,b)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(d,a) * funNum(b,c))  {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }else if(i == funNum(d,a) * funNum(c,b)) {
                System.out.print("吸血鬼数字 :" + i + "\n");
            }
            
        }

    }

    private static int funNum(int a, int b) {
        int sum = 0;
        if(a != 0) {
            sum = a * 10 + b;
        }else if(b != 0) {
            sum = b * 10 + a;
        }

        return sum;
    }


}
