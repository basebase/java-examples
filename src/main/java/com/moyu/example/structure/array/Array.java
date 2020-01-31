package com.moyu.example.structure.array;

/**
 * Created by Joker on 19/9/3.
 *
 * 在设计一个数组的时候
 */
public class Array<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] data; // 数组容量
    private int size; // 存放了多少元素


    /***
     * 提供初始化大小的数组
     * @param capacity
     */
    public Array(int capacity) {
        // 需要注意的就是, 泛型数组需要先用Object创建然后强转为泛型
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * 提供默认初始化大小数组
     */
    public Array() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @return 实际元素个数
     */
    public int getSize() {
        return size;
    }

    /***
     *
     * @return 数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0 ;
    }

    /**
     * 向数组头部添加元素
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 向数组末尾添加元素
     * @param e 实际值
     */
    public void addLast(E e) {
//        if (size >= data.length) {
//            throw new IllegalArgumentException("数组已满.");
//        }
//
//        // 把元素添加到数组末尾
//        data[size] = e;
//        // 实际元素加1, 否则数组一直覆盖当前下标的值
//        size++;
        add(size, e);
    }

    /***
     * 向数组指定位置添加元素
     * @param index 指定下标的位置
     * @param e 元素值
     */
    public void add(int index, E e) {



        if (index < 0 || index > size) {
            throw new IllegalArgumentException("请输入正确的下标位置");
        }

        if (size >= data.length) {
//            throw new IllegalArgumentException("数组已满.");
            // 动态扩容数组
            resize(data.length * 2);
        }

        // 将数组的元素向后面移动
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        // 元素移动完毕后, 直接指定对应的下标值赋值
        data[index] = e;
        size ++;
    }



    /**
     * 删除数组头部
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /***
     * 删除数组尾部
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定元素
     * @param e
     */
    public void removeElement(E e) {
        int removeElementIndex = find(e);
        remove(removeElementIndex);
    }

    /***
     * 删除指定位置元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("请输入正确的下标值");
        }

        E removeElement = get(index);
        // 把后面的元素值写入到前一个元素位置
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size --;

        // 如果当前元素个数小到当前数组的一半, 整个数组只有一半被利用, 将数组容量缩小
        if (size == data.length / 2) {
            resize(data.length / 2);
        }

        return removeElement;
    }

    /***
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("输入正确的下标");
        }

        return data[index];
    }

    // 获取第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获取最后一个元素
    public E getLast() {
        return get(size - 1);
    }


    /***
     * 修改index元素的值为e
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("输入正确的下标");
        }

        data[index] = e;
    }

    /**
     * 判断数组是否包含元素
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 查找元素, 如果找到元素返回对应的下标, 否则返回-1
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0 ; i < size; i ++) {
            if (data[i].equals(e)) {
                return i;
            }
        }

        return -1;
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (int i = 0; i < size; i ++) {
            if (i < size - 1) {
                buffer.append(data[i]).append(",");
            } else {
                buffer.append(data[i]);
            }
        }

        buffer.append("]");

        return String.format("数组的元素为: %s 数组容量为: %s\n%s", size, getCapacity(), buffer.toString());
    }


    /***
     * 更新数组大小
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[i];
        }

        data = newData;
    }
}
