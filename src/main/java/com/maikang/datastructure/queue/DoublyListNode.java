package com.maikang.datastructure.queue;

import java.util.HashMap;
import java.util.Map;


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class DoublyListNode {
    Integer val;
    Integer key;
    DoublyListNode next = null;
    DoublyListNode prev = null;

    DoublyListNode() {
    }

    ;

    DoublyListNode(Integer key, Integer val, DoublyListNode prev, DoublyListNode next) {
        this.key = key;
        this.val = val;
        this.prev = prev;
        this.next = next;
    }

    DoublyListNode(Integer val, DoublyListNode next, DoublyListNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}

