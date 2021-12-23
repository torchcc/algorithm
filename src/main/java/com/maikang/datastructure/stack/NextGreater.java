package com.maikang.datastructure.stack;

import java.util.*;
import java.util.stream.Collectors;

public class NextGreater {
    public int[] nextLargerNodes(ListNode head) {
        Stack<ListNode> s = new Stack();
        Map<ListNode, ListNode> map = new HashMap();
        s.push(new ListNode(Integer.MAX_VALUE));
        for (ListNode cur = head; cur != null; ) {
            while (cur != null && cur.val <= s.peek().val) {
                s.push(cur);
                cur = cur.next;
            }
            while (cur !=null && cur.val > s.peek().val) {
                map.put(s.pop(), cur);
            }
        }
        while (s.peek().val != Integer.MAX_VALUE) {
            map.put(s.pop(), new ListNode(0));
        }
        List<Integer> list = new ArrayList();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            list.add(map.get(cur).val);
        }
        int[] ans = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.stream(ans).boxed().collect(Collectors.toList()));
        return ans;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList(new int[]{2,1,5});
        NextGreater nextGreater = new NextGreater();
        nextGreater.nextLargerNodes(list.getHead());
    }
}

