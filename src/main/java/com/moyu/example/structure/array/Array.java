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
     * 向数组最后一个位置添加元素
     * @param e   添加元素
     */
    public void addLast(int e) {
        this.add(size, e);
//        if (size == datas.length)
//            throw new IllegalArgumentException("addLast添加失败, 请检查数组大小");
//        this.datas[this.size] = e;
//        this.size ++; // 添加元素后, 维护size大小, 新添加元素的索引位置
    }

    public void addFirst(int e) {
        this.add(0, e);
    }

    /***
     * 指定索引位置添加元素
     * @param index     索引位置
     * @param e         元素
     */
    public void add(int index, int e) {
        // 1. 判断数组还有没有容量
        if (size == datas.length)
            throw new IllegalArgumentException("添加失败, 请检查数组大小");

        // 2. 判断要插入的索引是否合理, 这里我们限制为size, 我们不希望中间出现间隙
        if (index < 0 || index > size)
            throw new IllegalArgumentException("请输入正确的索引位置");

        // 将size - 1的元素移动后size位置
        // 循环结束到要添加指定的位置
        for (int i = size - 1; i >= index; i--) {
            this.datas[i + 1] = this.datas[i];
        }

        // 移动完成后, 覆盖旧值
        this.datas[index] = e;
        size ++; // 维护数组元素大小
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
