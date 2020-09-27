package com.moyu.example.structure.array;

/***
 *      设计并创建属于一个自己的数组类
 */

public class Array<E> {

    // 实际存放元素个数
    private int size;
    // 数组类型
    private E[] datas;

    /***
     * 创建数组类, 指定数组大小
     * @param capacity   数组容量
     */
    public Array(int capacity) {
        this.datas = (E[]) new Object[capacity];
        this.size = 0;      // 创建数组时没有任何有效数据
    }

    /***
     * 使用默认数组大小, 创建数组对象
     */
    public Array() {
        this(10);
    }

    /**
     * 动态扩容数组容量
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newDatas = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newDatas[i] = datas[i];
        }
        this.datas = newDatas;
    }


    /**
     * 向数组最后一个位置添加元素
     * @param e   添加元素
     */
    public void addLast(E e) {
        this.add(size, e);
//        if (size == datas.length)
//            throw new IllegalArgumentException("addLast添加失败, 请检查数组大小");
//        this.datas[this.size] = e;
//        this.size ++; // 添加元素后, 维护size大小, 新添加元素的索引位置
    }

    public void addFirst(E e) {
        this.add(0, e);
    }

    /***
     * 指定索引位置添加元素
     * @param index     索引位置
     * @param e         元素
     */
    public void add(int index, E e) {


        // 1. 判断要插入的索引是否合理, 这里我们限制为size, 我们不希望中间出现间隙
        if (index < 0 || index > size)
            throw new IllegalArgumentException("请输入正确的索引位置");

        // 2. 判断数组还有没有容量
        if (size == datas.length)
            resize(datas.length * 2);
//            throw new IllegalArgumentException("添加失败, 请检查数组大小");

        // 将size - 1的元素移动后size位置
        // 循环结束到要添加指定的位置
        for (int i = size - 1; i >= index; i--) {
            datas[i + 1] = datas[i];
        }

        // 移动完成后, 覆盖旧值
        datas[index] = e;
        size ++; // 维护数组元素大小
    }

    /**
     * 删除最后一个位置元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /***
     * 删除第一个元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除指定元素
     * @param e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    /**
     * 删除指定索引位置的元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("请输入正确的索引位置");

        E ret = datas[index];

        for (int i = index + 1; i < size; i++) {
            datas[i - 1] = datas[i];
        }

        size -- ; // 从新维护元素个数

        // datas[size] = null;

        // 如果删除的元素过多使用量减少后也可以重新构建我们的数组, 减少浪费内存空间
        if (size == datas.length / 2)
            resize(datas.length / 2);

        return ret;
    }

    /**
     * 判断数组中是否包含该元素
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (datas[i].equals(e))
                return true;
        }

        return false;
    }

    /**
     * 查找数组中的元素, 返回索引位置
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (datas[i].equals(e))
                return i;
        }

        return -1;
    }

    /**
     * 更新数组中的元素值
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        /**
         * 注意看这里使用的>= size, 这是因为在add之后, size会向后移动一位
         * 假设我们使用的是> size则会访问到没有被赋值的空间数据, 这是不合理的, 因此需要注意
         */
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("请输入正确的索引位置");
        datas[index] = e;
    }

    /**
     * 获取指定位置的元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("请输入正确的索引位置");
        return datas[index];
    }


    /***
     *   在stack章节学习中添加获取第一个元素和最后一个元素
     */

    // 获取数组中第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获取数组中最后一个元素
    public E getLast() {
        // 这里使用get方法来返回数据而不直接使用datas[size - 1]返回
        // 主要怕数组中没有任何元素进而返回一个-1导致一个不合法的索引数据被返回
        return get(size - 1);
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

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("数组元素个数为: %d 数组容量为: %d \n", size, datas.length));
        buffer.append("[");
        for (int i = 0; i < size; i++) {        // 这里使用实际存在多个元素数量, 其余的空间没有被赋值不需要被输出
            buffer.append(datas[i]);
            if (i != size - 1)
                buffer.append(", ");
        }

        buffer.append("]");
        return buffer.toString();
    }
}
