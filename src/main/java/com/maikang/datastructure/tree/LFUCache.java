package com.maikang.datastructure.tree;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

 // implements Comparable<CacheNode>


class CacheNode implements Comparable<CacheNode>{
    public long tick;
    public int freq;
    public int key;
    public int val;
    public CacheNode(int freq, long tick, int key, int val) {
        this.freq = freq;
        this.tick = tick;
        this.key = key;
        this.val = val;
    }
    
    public int compareTo(CacheNode o) {
        if (this.freq == o.freq) {
            return Long.compare(this.tick, o.tick);
        }
        return this.freq - o.freq;
    }
}

class LFUCache {
    
    private Map<Integer, CacheNode> map = new HashMap<>();
    private TreeSet<CacheNode> set = new TreeSet<> ();
    private int cap ;
    private long tick;

    public LFUCache(int capacity) {
        cap = capacity;
        tick = 0L;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        CacheNode node = map.get(key);
        touch(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (cap == 0) return ;
        if (map.containsKey(key)) {
            CacheNode node = map.get(key);
            node.val = value;
            touch(node);
            return;
        }
        if (map.size() == cap) {
            CacheNode node = set.pollFirst();
            map.remove(node.key);
        }
        CacheNode node = new CacheNode(1, ++tick, key, value);
        map.put(key, node);
        set.add(node);
    }
    
    private void touch(CacheNode node) {
        set.remove(node);
        node.freq++;
        node.tick = ++this.tick;
        set.add(node);
    }

    public static void main(String[] args) {
        LFUCache obj = new LFUCache(2);
        obj.put(1,1);
        obj.put(2,2);
        System.out.println(obj.get(1) + " should be 1");
        System.out.println(obj.get(2) + " should be 2");
        obj.put(3,3);
        System.out.println(obj.get(1) + " should be -1");

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */