package com.moyu.example.jbase.innerclasses.example03;

/****
 * 链接外部类
 *   从上面两个例子中, 内部类似乎还只是一种名字隐藏和组织代码的模式。虽然有用, 但不是最引人注目的,
 *   它还有其它的用途。
 *
 *   当生成一个内部类的对象时, 此对象与制造它的外围对象之间就有了一种联系, 所以它能访问其外围对象的所有成员,
 *   而不需要任何特殊条件。此外, 内部类还拥有其外围类的所有元素的访问权。
 */

interface Selector {
    boolean end();
    Object current();
    void next();
}

public class Sequence {

    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next ++] = x;
    }

    private class SequenceSelector implements Selector {

        private int i = 0;

        public boolean end() {
            return i == items.length;
        }

        public Object current() {
            return items[i];
        }

        public void next() {
            if (i < items.length)
                i ++;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);

        // 在数组末尾添加obj, 如果还有空间的话
        for (int i = 0; i < 10; i ++)
            sequence.add(Integer.toString(i));
        Selector selector = sequence.selector();

        /***
         * 这里开始就比较有意思:
         *   SequenceSelector是提供Selector功能的private类, 我们创建Sequence并向其添加一个对象数据,
         *   然后通过调用selector方法获取一个Selector, 并用它在 Sequence 中移动和选择每一个元素。
         *
         *   最初看到SequenceSelector可能觉得就是一个内部类而已。但仔细观察end(), curretn(), next()三个方法都用到了items,
         *   这是一个引用, 它并不是SequenceSelector的一部分, 而是外围类中的一个private字段。然而内部类可以访问其外围类的方法和字段,
         *   就像自己拥有它们一样, 带来很大的便利。
         *
         *   总结:
         *     内部类自动拥有外围类所有成员的访问权限。
         */
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
