package com.moyu.example.leetcode.doublepointer;

/**
 * Created by Joker on 20/7/4.
 */
public class LeetCode61 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null)
            return head;

        ListNode t = head;

        ListNode slow = head;
        ListNode fast = head.next;
        while (k > 0) {
            if (slow.next == fast && fast.next == null) {
                k --;
                fast.next = t;
                t = fast;
                slow.next = null;
                slow = fast;
                fast = t.next;
            } else {
                fast = fast.next;
                slow = slow.next;
            }
        }

        return t;

    }

    private static void print(ListNode node) {
        StringBuffer buffer = new StringBuffer();
        while (node != null) {
            buffer.append(node.val).append(" -> ");
            node = node.next;
        }

        System.out.println(buffer.toString());
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = null;

        ListNode listNode = new LeetCode61().rotateRight(node1, 4);
        print(listNode);
    }


    // 1->2->3->4->5->NULL

    /***
     *      f -> t
     *          5 -> 1 -> 2 -> 3 -> 4 -> NULL
     *          4 -> 5 -> 1 -> 2 -> 3 -> NULL
     */
}
