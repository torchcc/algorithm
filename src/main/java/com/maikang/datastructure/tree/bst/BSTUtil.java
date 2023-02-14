package com.maikang.datastructure.tree.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=-9sHvAnLN_w&list=PLpPXw4zFa0uKKhaSz87IowJnOTzh9tiBk&index=59
 *
 *
 *
 * 1. if a grandson cause imbalance, its grandparent has to pay for it. we'll need to do a rotation of
 * its grandparent around its parent.
 *
 * left rotate
 * 2. if grandparent's right child's right subtree causes imbalance, we do a left rotation of grand around parent.
 *
 * right rotate
 * 3. if grandparent's left child's left subtree causes imbalance, we do a right rotation of grand around parent.
 *
 *
 * right left rotate
 * 4. if grandparent's right child's left subtree causes imbalance, we do a right rotation of parent around son,
 *      and a left rotation of grand around the new parent.
 *
 * left right rotate
 * 5. if grandparent's left child's right subtree causes imbalance, we do a left rotation of parent around son,
 *      and a right rotation of grand around the new parent.
 */
public class BSTUtil {

    public static void main(String[] args) {
        TreeNode root = BSTUtil.treeFromString("[1,null,2,null,3,null,4]");
        BTreePrinter.printNode(root);

        TreeNode rotated;
        rotated = leftRotate(root);
        System.out.println("----------");
        BTreePrinter.printNode(rotated);

        rotated = leftRotate(rotated);
        System.out.println("----------");
        BTreePrinter.printNode(rotated);


        TreeNode treeNode = BSTUtil.treeFromString("[4, null, 8, 6]");
        BTreePrinter.printNode(treeNode);

        BTreePrinter.printNode(rightLeftRotate(treeNode));
        AVLTree avlTree = new AVLTree();
    }

    public static TreeNode leftRotate (TreeNode grand) {
        TreeNode tmp = grand.right;
        grand.right = tmp.left;
        tmp.left = grand;
        return tmp;
    }

    public static TreeNode rightRotate (TreeNode grand) {
        TreeNode tmp = grand.left;
        grand.left = tmp.right;
        tmp.right = grand;
        return tmp;
    }

    public static TreeNode rightLeftRotate (TreeNode grand) {
        // do a right rotation of parent.
        grand.right = rightRotate(grand.right);

        // do a left rotation of grand;
        return leftRotate(grand);
    }

    public static TreeNode leftRightRotate (TreeNode grand) {
        // do a left rotation of parent.
        grand.left = rightRotate(grand.left);

        // do a left rotation of grand;
        return rightRotate(grand);
    }


    /**
     *
     * input string like `[1, 2, null, 3, 4]` return the root of a tree that it constructs
     * @param str
     * @return
     */
    public static TreeNode treeFromString(String str) {
        List<TreeNode> nodes = stringToTreeNodes(str);
        TreeNode[] arr = nodes.toArray(new TreeNode[0]);
        if (nodes.size() == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(arr[0]);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode cur = q.poll();
            if (cur == null) continue;
            if (i < arr.length) {
                cur.left = arr[i++];
                q.offer(cur.left);
            }
            if (i < arr.length) {
                cur.right = arr[i++];
                q.offer(cur.right);
            }
        }
        return arr[0];

    }

    /**
     * input string like [1, 2, null, 3, 4] return a list of TreeNode.
     * @param str
     * @return
     */
    private static List<TreeNode> stringToTreeNodes(String str) {
        String[] values = str.
                replace("[", "").
                replace("]", "").
                trim().split(",");
        List<TreeNode> nodes = new ArrayList<>();
        for (String val : values) {
            val = val.trim();
            if (!val.equals("null")) nodes.add(new TreeNode(Integer.valueOf(val)));
            else nodes.add(null);
        }
        return nodes;
    }

    /**
     * print to visualize a tree
     * @param root
     */
    public static void print(TreeNode root) {
        BTreePrinter.printNode(root);
    }

}
