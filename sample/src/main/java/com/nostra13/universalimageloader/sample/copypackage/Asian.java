package com.nostra13.universalimageloader.sample.copypackage;

/**
 * Created by 杨阳洋 on 2018/5/16.
 */

public class Asian {

    private String skin;
    Person mPerson;

    public Asian(String skin, Person person) {
        this.skin = skin;
        mPerson = person;
    }

    public Asian(Asian asian){
        this(asian.skin,asian.mPerson);
    }

    public static void main(String [] args){
        Person person = new Person("Uart", "men", 23);
        Person person1 = new Person(person);

        System.out.println(person.toString());
        System.out.println(person1.toString());
        System.out.println(person1 == person);

        Asian a1 = new Asian("yellow", new Person("Uart", "men", 23));
        Asian a2 = new Asian(a1);

        System.out.println(a1.toString());
        System.out.println(a2.toString());
        System.out.println(a1 == a2);

        System.out.println(person.getName());
        System.out.println(person1.getName());
        System.out.println(person.getName() == person1.getName());

        person.setName("Jhon");

        //String是不可变对象，在拷贝的时候会被复制
        System.out.println(person.getName());
        System.out.println(person1.getName());
        System.out.println(person.getName() == person1.getName());

    }
}
