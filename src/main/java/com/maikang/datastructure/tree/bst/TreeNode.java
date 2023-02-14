package com.maikang.datastructure.tree.bst;

public class TreeNode {
     public int val;

     public TreeNode left;

     public TreeNode right;

     public TreeNode parent;

     public int height = 0;


     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }

     public static int getHeight(TreeNode node) {
         if (node == null) return -1;
         return node.height;

     }

     public void refreshHeight() {
         height = 1 + Math.max(getHeight(left), getHeight(right));
     }

 }