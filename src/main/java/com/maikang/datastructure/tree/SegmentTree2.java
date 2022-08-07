package com.maikang.datastructure.tree;

class SegmentTree2 {
    public int[] tree;
    private int n;
    
    public SegmentTree2(int n ) {
        this.n = n;
        tree = new int[n * 2];
    }
    
    public SegmentTree2(int[] nums) {
        this.n = nums.length;
        tree = new int[n * 2];
        for (int i = n, j = 0; i < 2 * n; i++, j++) tree[i] = nums[j];
        for (int i = n-1; i > 0; i--) tree[i] = tree[2 * i] + tree[2 * i + 1];
    }
    
    public void updateDelta(int idx, int delta) {
        idx += n;
        while (idx != 0) {
            tree[idx] += delta;
            idx /= 2;
        }
    }
    // 2 * n means leftchild, 
    public void update(int idx, int val) {
        idx += n;
        tree[idx] = val;
        while (idx != 0) {
            int left = idx, right = idx;
            if (idx % 2 == 0) right = idx + 1;
            else left = idx - 1;
            tree[idx/2] = tree[left] + tree[right];
            idx /= 2;
        }
    }
    
    public int query(int l, int r) {
        l += n;
        r += n;
        int sum = 0;
        while (l <= r) {
            if (l % 2 == 1) {
                sum += tree[l];
                l++;
            }
            if (r % 2 == 0) {
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}
