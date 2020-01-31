package com.moyu.example.structure.trie;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Joker on 20/1/26.
 */
public class Trie {

    /**
     * 更具上面所述, 构建我们的Node
     */
    private class Node {
        public boolean isWord;
        public Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    // 根节点
    private Node root ;
    private int size ;

    // 初始化节点信息
    public Trie() {
        this.root = new Node();
        this.size = 0;
    }


    /**
     * 向Trie中添加一个新的单词word
     * @param word
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0 ; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) // 如果下一个节点不存在字符就添加, 如果存在不做任何操作
                cur.next.put(c, new Node());

            cur = cur.next.get(c); // 重新赋值, 这样就到叶子节点但是有可能是某个非叶子节点
        }

        // 结束之后, 不能直接就size++, 需要判断是否之前就添加过该单词了, 就判断尾巴是否为true
        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }


    public void addRE(String word) {
        addRE(word, 0, root);
    }

    private void addRE(String word, int index, Node node) {

        if (index == word.length()) {
            if (!node.isWord) {
                node.isWord = true;
                size ++;
            }

            return ;
        }

        char c = word.charAt(index);
        if (node.next.get(c) == null)
            node.next.put(c, new Node());
        addRE(word, ++index, node.next.get(c));
    }


    /**
     * 查询单词是否在trie中
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0 ; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) // 如果不存在查找的单词字母, 则直接返回
                return false;

            cur = cur.next.get(c);
        }

        // 记住, 这里计算遍历出来后也不能直接返回true, 比如一开说的pan是panda前缀, 如果我们没有添加pan却返回了true就有问题了
        //        return true;
        return cur.isWord; // 正确的方式直接返回当前节点的标识
    }

    // 查询单词是否在trie中, 递归写法
    public boolean containsRE(String word) {
        return containsRE(word, 0, root);
    }

    private boolean containsRE(String word, int index, Node node) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        return node.next.get(c) == null ? false : containsRE(word, ++index, node.next.get(c));
    }


    // 查询Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i ++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;

            cur = cur.next.get(c);
        }

        return true;
    }

    // 查询Trie中有单词以prefix为前缀(递归写法)
    public boolean isPrefixRE(String prefix) {
        return isPrefixRE(prefix, 0, root);
    }

    private boolean isPrefixRE(String prefix, int index, Node node) {
        if (index == prefix.length()) {
            return true;
        }
        char c = prefix.charAt(index);
        return node.next.get(c) == null ? false : isPrefixRE(prefix, ++index, node.next.get(c));
    }


    public boolean match(String word) {
        return match(word, 0, root);
    }

    private boolean match(String word, int index, Node node) {

        if (index == word.length())
            return node.isWord;

        char c = word.charAt(index);
        if (c != '.') {
            return node.next.get(c) == null ? false : match(word, ++index, node.next.get(c));
        } else {
            // 如果是"."需要把所有节点遍历进行匹配
            for (Character nextChar : node.next.keySet()) {
                return node.next.get(nextChar) == null ? false : match(word, ++index, node.next.get(nextChar));
            }

            return false;
        }
    }

    public int getSize() {
        return size;
    }

}
