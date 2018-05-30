package com.nostra13.universalimageloader.sample.collection;

/**
 * Created by 杨阳洋 on 2018/5/9.
 */

public class SongMiaoMiao {

    private String name ;
    private int age ;
    private HiAnd7C mHiAnd7C;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return age;
    }

    public void setSex(String age) {
        int ageInt = Integer.parseInt(age);
        if(ageInt > 18) {
            this.age = 18;
        }else{
            this.age = ageInt;
        }
    }



}
