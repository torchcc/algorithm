package com.maikang.datastructure.queue;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private int capacity;
    private int length = 0;
    private DoublyListNode head = null;
    private DoublyListNode tail = null;
    private Map<Integer, DoublyListNode> map = new HashMap();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DoublyListNode(-1, -1, null, null);
        tail = new DoublyListNode(0, null, head);
        head.next = tail;
    }


    public int get(int key) {
        DoublyListNode node = getNode(key);
        if (node == null) return -1;
        return node.val;
    }

    private DoublyListNode getNode(int key) {
        if (!map.containsKey(key)) return null;


        DoublyListNode node = map.get(key);
        if (node == head.next) return node;

        DoublyListNode prevOfNode = node.prev;
        DoublyListNode nextOfNode = node.next;

        DoublyListNode tmp = head.next;
        head.next = node;
        node.prev = head;
        node.next = tmp;
        tmp.prev = node;
        prevOfNode.next = nextOfNode;
        nextOfNode.prev = prevOfNode;
        return node;
    }

    private void evict() {
        if (length == 0) return;

        DoublyListNode toEvict = tail.prev;
        DoublyListNode lastNode = tail.prev.prev;
        lastNode.next = tail;
        tail.prev = lastNode;
        map.remove(toEvict.key);
        length--;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (length == capacity) evict();
            add(key, value);
        } else {
            DoublyListNode node = getNode(key);
            node.val = value;
        }
    }

    private void add(int key, int val) {
        DoublyListNode node = new DoublyListNode(key, val, head, head.next);
        map.put(key, node);
        head.next.prev = node;
        head.next = node;
        length++;
    }

    public static void main(String[] args) {
        /*
        ["LRUCache","put","put","get","get","put","get","get","get"]
            [[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
         */
        LRUCache obj = new LRUCache(2);
        obj.put(2,1);
        obj.put(3,2);
        System.out.println(obj.get(3) + " should be 2");
        System.out.println(obj.get(2) + " should be 1");
        obj.put(4,3);

    }
}

