package com.maikang.datastructure.tree;

/*
Segment Tree,  a simple Binary Tree with members of span start, end, and val(could be sum, max, val), and all the leaves consist of the whole input array.
*/
class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int start;
    public int end;
    public int sum;
    public TreeNode (int sum, int start, int end, TreeNode left, TreeNode right) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.left = left;
        this.right = right;
    }
    public TreeNode (int sum, int start, int end) {
        this(sum, start, end, null, null);
    }
}

class SegmentTree {
   private TreeNode root;
    
    public SegmentTree(int[] nums) {
        this.root = buildTree(nums, 0, nums.length-1);    
    }
    
    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start == end) return new TreeNode(nums[start], start, end);
        int mid = start + (end - start) / 2;
        TreeNode left = buildTree(nums, start, mid);
        TreeNode right = buildTree(nums, mid+1, end);
        return new TreeNode(left.sum+right.sum, start, end, left, right);
    }
    private void updateTree(TreeNode node, int index, int val) {
        if (index == node.start && index == node.end) node.sum = val; 
        else {
            int mid = node.start + (node.end - node.start) / 2;
            if (index <= mid) updateTree(node.left, index, val);
            else updateTree(node.right, index, val);
            node.sum = node.left.sum + node.right.sum;
        }
    }
    private int sumRangQueryTree(TreeNode node, int start, int end) {
        if (start == node.start && end == node.end) return node.sum;
        else {
            int mid = node.start + (node.end - node.start) / 2;
            if (start > mid) return sumRangQueryTree(node.right, start, end);
            else if (end <= mid) return sumRangQueryTree(node.left, start, end);
            else {
                return sumRangQueryTree(node.left, start, mid) + sumRangQueryTree(node.right, mid+1, end);
            }
        }
    }
    
    public void update(int index, int val) {
        updateTree(root, index, val);
    }
    
    public int sumRange(int left, int right) {
        return sumRangQueryTree(root, left, right);
    }


    /**
     * ["NumArray","update","sumRange","sumRange","update","sumRange"]
     * [[[9,-8]],[0,3],[1,1],[0,1],[1,-3],[0,1]]
     * @param args
     */
    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(new int[]{9, -8});
        segmentTree.update(0, 3);
        System.out.println(segmentTree.sumRange(1, 1));
        System.out.println(segmentTree.sumRange(0, 1));
        segmentTree.update(1, -3);
        System.out.println(segmentTree.sumRange(0, 1));

    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */