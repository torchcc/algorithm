package com.maikang.datastructure.dp;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}

class UniqueBST {
    public List<TreeNode> generateTrees(int n) {
        List[][] memoi = new List[n+1][n+1];
        return helper(1, n, memoi);   
    }
     private List<TreeNode> helper(int start, int end, List[][] memoi) {
          List<TreeNode> list = new ArrayList<>();
         if (start > end) {
             list.add(null);
             return list;
         }
         if (memoi[start][end] != null) return memoi[start][end];
         for (int i = start; i <= end; i++) {
              List<TreeNode> lefts = helper(start , i-1, memoi);
              List<TreeNode> rights = helper(i+1, end, memoi);
             for (TreeNode left:lefts) {
                 for (TreeNode right: rights) {
                     TreeNode root = new TreeNode(i);
                     root.left = left;
                     root.right = right;
                     list.add(root);
                 }
             }
         }
         return memoi[start][end] = list;
     }
}