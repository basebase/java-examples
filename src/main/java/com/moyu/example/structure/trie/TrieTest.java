package com.moyu.example.structure.trie;

/**
 * Created by Joker on 20/1/26.
 */
public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String s = "cat";
        trie.add(s);

        s = "dog";
        trie.addRE(s);

        s = "panda";
        trie.add(s);

//        s = "pan";
//        trie.addRE(s);

        System.out.println(trie.contains("dog"));
        System.out.println(trie.contains("cat"));
        System.out.println(trie.contains("pan"));
        System.out.println(trie.isPrefix("pan"));
        System.out.println(trie.contains("panda"));

        System.out.println("=======================");

        System.out.println(trie.containsRE("dog"));
        System.out.println(trie.containsRE("cat"));
        System.out.println(trie.containsRE("pan"));
        System.out.println(trie.isPrefixRE("pan"));
        System.out.println(trie.containsRE("panda"));

        System.out.println("=======================");

        System.out.println(trie.match("c."));
        System.out.println(trie.match("ca."));

    }
}
