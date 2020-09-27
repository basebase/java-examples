package com.moyu.example.structure.stack;

import com.moyu.example.structure.array.Array;

/***
 * 基于数组实现的栈
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    // 引入我们自定义的数组
    Array<E> array = null;

    /**
     * 明确自己的栈大小可以采用此方式构建栈
     * @param capacity
     */
    public ArrayStack(int capacity) {
        this.array = new Array<>(capacity);
    }

    public ArrayStack() {
        this.array = new Array<>();
    }


    /**
     * 将元素压入栈顶, 通过数组来实现, 但是要了解数组数据是横着排列, 也就是说最后一个元素是栈顶
     * @param e
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /**
     * 将元素从栈中弹出, 同理删除元素也是从最后一个删除, 最后一个元素是栈顶元素
     * @return
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 返回栈顶元素, 可以通过数组的get(size - 1)方法获取, 可以通过更新Array类提供接口的形式来获取
     * @return
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    /**
     * 该方法是数组实现特有的实现, 返回栈的容量大小
     * @return
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i < array.getSize() - 1)
                res.append(", ");
        }

        res.append("] Top");
        return res.toString();
    }
}
