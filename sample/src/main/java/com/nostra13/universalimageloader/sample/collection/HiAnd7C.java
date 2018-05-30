package com.nostra13.universalimageloader.sample.collection;

import com.nostra13.universalimageloader.sample.activity.A;
import com.nostra13.universalimageloader.sample.activity.C;

/**
 * Created by 杨阳洋 on 2018/5/9.
 */

public class HiAnd7C extends A {

    private String name;
    private String age;
    private SongMiaoMiao smm;

    public static void main(String [] args){
        try(A a = new A();C c = new C()){
            a.say();
            c.say();
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            System.out.println("finally");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(int age) {
        String ageString = String.valueOf(age);
        this.age = ageString;
    }
}
