package com.nostra13.universalimageloader.sample.copypackage;

/**
 * Created by 杨阳洋 on 2018/5/16.
 */

public class Person {

    private String name;
    private String sex;
    private int age;

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Person(Person person){
        this.name = person.name;
        this.sex = person.sex;
        this.age = person.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
