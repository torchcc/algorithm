package com.maikang.datastructure.linkedlist;

import com.maikang.datastructure.stack.ListNode;

import java.util.ArrayList;
import java.util.List;

// 利用数据结构就可以解决的问题。
public class Josephus {
    public int[] arrayResolution(int n, int k, int m) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }
        int cur = k-1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                while (true) {
                    cur = (cur + 1) % n;
                    if (nums[cur] != 0) {
                        break;
                    }
                }
            }
            list.add(nums[cur]);
            nums[cur] = 0;
            while (i!=n-1) {
                cur = (cur+1)%n;
                if (nums[cur] !=0) {
                    break;
                }
            }
        }
        System.out.println(list);
        return list.stream().mapToInt(Integer::intValue).toArray();

    }

    public int[] resolution(int n, int k, int m) {
        ListNode dummy = new ListNode(0, null);
        ListNode cur = dummy;
        for (int i = 1; i <= n; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        cur.next = dummy.next;

        cur = dummy;
        ListNode rear = null;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            rear = cur;
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                rear = rear.next;
                cur = cur.next;
            }
            list.add(cur.val);
            rear.next = cur.next;
            cur = cur.next;
        }
        System.out.println(list);
        return list.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        new Josephus().resolution(10, 1, 3);
        new Josephus().arrayResolution(10, 1, 3);
    }
}
