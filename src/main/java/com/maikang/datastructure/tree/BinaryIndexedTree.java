package com.maikang.datastructure.tree;

// https://www.youtube.com/watch?v=WbafSgetDDk&list=PLLuMmzMTgVK5Hy1qcWYZcd7wVQQ1v0AjX&index=21
public class BinaryIndexedTree {
    private int[] sums;

    public BinaryIndexedTree(int n) {
        this.sums = new int[n+1];
    }

    public void update(int i , int delta) {
        while (i < sums.length) {
            sums[i] += delta;
            i += lowbit(i);
        }
    }

    public int query(int i ) {
        int sum = 0;
        while (i > 0 ) {
            sum += sums[i];
            i -= lowbit(i);
        }
        return sum;
    }

    private int lowbit(int i) {
        return i & -i;
    }
}
