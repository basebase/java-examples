package com.moyu.example.structure.array;

/***
 *      设计并创建属于一个自己的数组类
 */

public class Array {

    // 实际存放元素个数
    private int size;
    // 数组类型
    private int[] datas;

    /***
     * 创建数组类, 指定数组大小
     * @param capacity   数组容量
     */
    public Array(int capacity) {
        this.datas = new int[capacity];
        this.size = 0;      // 创建数组时没有任何有效数据
    }

    /***
     * 使用默认数组大小, 创建数组对象
     */
    public Array() {
        this(10);
    }

    /**
     * @return    返回数组元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * @return   返回数组大小
     */
    public int getCapacity() {
        return datas.length;
    }

    /**
     * @return   返回数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
