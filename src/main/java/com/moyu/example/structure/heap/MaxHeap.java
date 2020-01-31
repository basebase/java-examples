package com.moyu.example.structure.heap;

import java.util.ArrayList;

/**
 * Created by Joker on 20/1/4.
 */
public class MaxHeap<E extends Comparable> {

    private ArrayList<E> data ;

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        this.data = new ArrayList<>();
    }


    public MaxHeap(ArrayList<E> data) {

        this.data = data;


        // 缩写, 直接获取到最后一个非叶子节点索引, 进行递减。
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }

        // 1. 获取到最后一个非叶子节点的元素索引位置
//        int p = parent(this.data.size() - 1);
        // 2. 对p从后往前执行, 依次递减进行下沉操作
//        while (p >= 0) {
//            // 2. 进行下沉
//            siftDown(p);
//            p--;
//        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    // 返回父元素在二叉堆中数组的索引位置
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("该索引没有父节点");
        }

        return (index - 1) / 2 ;
    }

    // 返回左孩子索引
    private int leftChild(int index) {
        return index * 2 + 1 ;
    }

    // 返回孩子索引
    private int rightChild(int index) {
        return index * 2 + 2 ;
    }

    // 添加元素
    public void add(E e) {
        data.add(e);
        // 将新加入的索引进行上滤, 添加是从最后添加的所以取最后元素索引位置
        siftUp(data.size() - 1);
    }

    // 上滤过程
    private void siftUp(int index) {
        // 如果当前index为0表示为根节点, 根节点是没有父元素的所以没法比较, 并且父节点是小于子节点的
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            // 如果子节点大于父节点进行交换
            swap(parent(index), index);
            // 继续判断, 是否还大于祖先节点, 直到满足堆的特性
            index = parent(index);
        }
    }


    // 查找出最大元素
    public E findMax() {
        if (data.size() == 0)
            throw new IllegalArgumentException("当前数组为空");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax() {
        // 1. 找到最大值
        E ret = findMax();

        // 2. 最后一个元素顶替最大值
        swap(0, data.size() - 1);

        // 3. 删除最后节点值
        data.remove(data.size() - 1);

        // 4. 下滤(下浮)过程
        siftDown(0);

        return ret ;
    }

    // 下滤节点
    private void siftDown(int index) {

        // 如果下滤到叶子节点, 在去获取当前索引位置左孩子肯定超出整个数组大小
        while (leftChild(index) < data.size()) {


            // 1. 获取到该索引的左右孩子
            int k = leftChild(index);

            // 需要判断是否存在右孩子
            // k + 1的话相当左孩子索引位置+1得到了右孩子, 如果不大于数组长度则包含右孩子
            if (k + 1 < data.size()) {

                // 获取左右孩子中最大的元素节点
                // 这里的判断是更新索引位置数据, 如果左孩子大于右孩子则不需要更新索引, 否则更新为右孩子的索引
                if (data.get(k).compareTo(data.get(k + 1)) < 0) {
//                    k = rightChild(index);
                    ++ k; // ++k 等价rightChild(k)
                }
            }

            if (data.get(index).compareTo(data.get(k)) >= 0)
                break;

            // 进行交换数据
            swap(index, k);
            // 将下滤后的索引继续进行判断
            index = k;
        }
    }


    public E replace(E e) {
        // 1. 找到最大元素
        E ret = findMax();

        // 2. 新插入的值替换堆顶元素
        data.set(0, e);

        // 3. 进行下沉操作
        siftDown(0);

        return ret;
    }



    // 位置交换
    private void swap(int p, int c) {
        E t = data.get(p);
        data.set(p, data.get(c));
        data.set(c, t);
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("[");
        for (int i = 0 ; i < data.size(); i ++) {
            if (i == data.size() - 1)
                buffer.append(data.get(i));
            else
                buffer.append(data.get(i)).append(",");
        }

        buffer.append("]");
        return buffer.toString();
    }
}
