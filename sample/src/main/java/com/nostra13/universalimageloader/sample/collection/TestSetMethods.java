package com.nostra13.universalimageloader.sample.collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by 杨阳洋 on 2018/5/8.
 */

public class TestSetMethods {

    public static void main(String [] args){
        HashSet<A> hashSet = new HashSet<>();

        hashSet.add(new A("a"));
        hashSet.add(new A("b"));
        hashSet.add(new A("c"));
        hashSet.add(new A("d"));

        System.out.println(hashSet.toString());

        System.out.println(hashSet);
        hashSet.clone();
        HashSet clone = (HashSet) hashSet.clone();


        hashSet.add(new A("e"));
        System.out.println(hashSet.toString());
        System.out.println(clone);
        clone.add("123");
        System.out.println(clone);

        HashSet<A> hashSetCopy = new HashSet<>(hashSet.size());

        Iterator<A> iterator =
                hashSet.iterator();
        while (iterator.hasNext()){
            hashSetCopy.add(iterator.next().clone());
        }
        System.out.println(hashSet.toString());
        System.out.println(hashSetCopy);

        hashSet.contains(new A("a"));
        hashSet.isEmpty();


        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("e");
        treeSet.add("g");
        treeSet.add("f");
        treeSet.add("h");
        System.out.println(treeSet.toString());

        HashSet<String> hashSetC = new HashSet<>();

        hashSetC.add("a");
        hashSetC.add("d");
        hashSetC.add("c");
        hashSetC.add("b");

        treeSet.addAll(hashSetC);
        System.out.println(treeSet.toString());

        String d = treeSet.ceiling("d13");
        System.out.println(d);

        Comparator<? super String> comparator = treeSet.comparator();
        System.out.println(comparator != null);

        hashSet.contains("a");
        System.out.println(treeSet.contains("a"));
    }

    static class A implements Cloneable{
        private String name;

        public A(String name) {
            this.name = name;
        }

        @Override
        protected A clone() {
            A a = null;
            try {
                a = (A) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return a;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
