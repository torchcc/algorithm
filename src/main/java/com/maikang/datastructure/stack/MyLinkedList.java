package com.maikang.datastructure.stack;

public class MyLinkedList {
    private ListNode head = new ListNode(Integer.MAX_VALUE, null);

    public MyLinkedList(int[] nums) {
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }

    }

    public ListNode getHead() {
        return head.next;
    }
}
