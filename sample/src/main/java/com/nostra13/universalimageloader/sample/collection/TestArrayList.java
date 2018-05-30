package com.nostra13.universalimageloader.sample.collection;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by 杨阳洋 on 2018/5/14.
 */

public class TestArrayList {

    /**
     * ArrayList 默认的数组容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 用于空实例的共享空数组实例
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 另一个共享空数组实例，用的不多,用于区别上面的EMPTY_ELEMENTDATA
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * ArrayList底层的容器
     */
    transient Object[] elementData; // non-private to simplify nested class access

    //当前存放了多少个元素   并非数组大小
    private int size;

    /**
     * 1.先判断是否需要扩容（如果是一个空数组则自动扩容为10）
     * 2.判断是否溢出，如果溢出则扩容为原来的1.5倍
     * 3.通过Arrays.copyOf()复制
     * 4.添加元素到末尾
     */
    public boolean add(Object e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        //如果是以ArrayList()构造方法初始化,那么数组指向的是DEFAULTCAPACITY_EMPTY_ELEMENTDATA.第一次add()元素会进入if内部,
        //且minCapacity为1,那么最后minCapacity肯定是10,所以ArrayList()构造方法上面有那句很奇怪的注释.
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    //扩容
    private void grow(int minCapacity) {
        // overflow-conscious code
        //1. 记录之前的数组长度
        int oldCapacity = elementData.length;
        //2. 新数组的大小=老数组大小+老数组大小的一半
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //3. 判断上面的扩容之后的大小newCapacity是否够装minCapacity个元素
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        if (newCapacity - 10000 > 0)
            newCapacity = hugeCapacity(minCapacity);
        //5. 复制数组,注意,这里是浅复制
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > 10000) ?
                Integer.MAX_VALUE :
                10000;
    }

    /**
     * 1.判断index是否在限定范围内
     * 2.判断是否需要扩容
     * 3.将elementData元素从index开始的size - index个元素移动到elementData的index + 1处。
     * 4.在移动的位置放入element
     * @param index
     * @param element
     */
    public void add(int index,Object element){
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);

        //从elementData的indesx开始的size-index长度的元素复制到size+1的位置；
        //相当于Index处开始，后面的元素向后移动了一位
        System.arraycopy(elementData,index,elementData,index + 1 ,size - index);

        elementData[index] = element;

        size ++;

    }

    /**
     * 1.判断是否需要扩容
     * 2.集合c从0开始的number个元素移动到elementData元素的位置
     * 3.记录元素长度
     * @param c
     * @return
     */
    public boolean addAll(Collection<? extends Object> c){
        Object[] array = c.toArray();
        int number = array.length;
        ensureCapacityInternal(size + number);
        System.arraycopy(c,0,elementData,size,number);

        size += number;

        return number != 0;

    }

    /**
     * 1.检查Index是否在指定范围
     * 2.是否需要扩容
     * 3.elementData从index开始的size - index个元素移动到elementData的index + length处
     * 4.c从0开始的length个元素移动到elementData移动到Index元素的位置
     * @param index
     * @param c
     * @return
     */
    public boolean addAll(int index,Collection<? extends Object> c){
        rangeCheckForAdd(index);
        Object[] array = c.toArray();
        int length = array.length;
        ensureCapacityInternal(size + length);
        
        if(size > index)
            System.arraycopy(elementData,index,elementData,index + length,size - index);
        System.arraycopy(c,0,elementData,index,length);
        size += length;
        return length != 0;

    }

    private void rangeCheckForAdd(int index) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
    }

    public Object remove(int index){
        rangeCheck(index);
        Object oldValue = elementData[index - 1];
        int numMoved = size - index - 1;
        if(numMoved > 0)
            System.arraycopy(elementData,index + 1,elementData,index,numMoved);
        elementData[--size] = null;
        return oldValue;
    }


    public boolean remove(Object obj){
        if(obj == null) {
            for (int i = 0 ; i < size ; i ++){
                if(elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        }else{
            for (int i = 0 ; i < size ; i ++){
                if(obj.equals(elementData[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index){
        int numMoved = size - index - 1;
        if(numMoved > 0)
            System.arraycopy(elementData,index + 1,elementData,index,numMoved);
        elementData[--size] = null;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("out of bounds");
    }
}
