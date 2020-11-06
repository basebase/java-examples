package com.moyu.example.structure.recursion;

/***
 *      使用递归删除链表中重复元素
 */
public class RemoveElement {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }



    public static ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;        // 基本情况, 链表为空

        /**
         * 更新状态, 移动头结点位置, 新的头结点后面挂载的子节点会越来越小, 直到没有任何元素
         */
        ListNode node = removeElements(head.next, val);
        head.next = node;       // 如果当前头结点不是被删除的结点, 则挂载其余没有被删除的子节点, 形成新的链表
        return head.val == val ? node : head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        ListNode node = removeElements(head, 5);
        StringBuffer buffer = new StringBuffer();
        while (node != null) {
            buffer.append(node.val).append("->");
            node = node.next;
        }

        buffer.append("NULL");

        System.out.println(buffer.toString());
    }
}
