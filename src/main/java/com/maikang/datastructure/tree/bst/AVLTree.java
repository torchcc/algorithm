package com.maikang.datastructure.tree.bst;

import static com.maikang.datastructure.tree.bst.TreeNode.getHeight;

public class AVLTree {
    private TreeNode root;

    private int size = 0;

    private TreeNode nodeToCheck = null;
    public void remove(int key) {
        root = remove(root, key);
        checkBalance(nodeToCheck);
    }

    /**
     * near the same as BST remove LC450: https://leetcode.com/problems/delete-node-in-a-bst/description/
     * but to mark a nodeToCheck to rebalance.
     * @param node
     * @param key
     * @return
     */
    private TreeNode remove(TreeNode node, int key) {
        if (node == null) return null;

        if (key > node.val) {
            node.right = remove(node.right, key);
            return node;
        } else if (key < node.val) {
            node.left = remove(node.left, key);
            return node;
        } else {
            size--;
            if (node.left == null && node.right == null) {
                nodeToCheck = node.parent;
                return null;
            }
            else if (node.left == null) {
                node.right.parent = node.parent;
                return nodeToCheck = node.right;
            } else if (node.right == null) {
                node.left.parent = node.parent;
                return nodeToCheck = node.left;
            } else {

                TreeNode cur = node.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                node.val = cur.val;
                node.right = remove(node.right, cur.val);
                return node;
            }
        }
    }

//    private TreeNode remove(TreeNode node, int key) {
//        if (node == null) return null;
//
//        if (key > node.val) {
//            node.right = remove(node.right, key);
//            return node;
//        } else if (key < node.val) {
//            node.left = remove(node.left, key);
//            return node;
//        } else {
//            size--;
//            if (node.left == null && node.right == null) {
//                return null;
//            }
//            else if (node.left == null) {
//                node.right.parent = node.parent;
//                return nodeToCheck = node.right;
//            } else if (node.right == null) {
//                node.left.parent = node.parent;
//                return nodeToCheck = node.left;
//            } else {
//
//                TreeNode cur = node.right;
//                while (cur.left != null) {
//                    cur = cur.left;
//                }
//                TreeNode prev = cur.parent;
//
//                nodeToCheck = cur;
//                if (prev != node) {
//                    prev.left = cur.right;
//                    if (cur.right != null)
//                        cur.right.parent = prev;
//
//                    cur.right = node.right;
//                    node.right.parent = cur;
//
//                    nodeToCheck = prev;
//                }
//                // Important !!
//                cur.parent = node.parent;
//
//                cur.left = node.left;
//                node.left.parent = cur;
//                return cur;
//            }
//        }
//    }

    public void add(TreeNode node) {
        if (node == null) return;
        if (root == null) {
            root = node;
        } else {
            add(root, node);
        }
        size++;
    }

    /**
     * nearly the same as the common add method in BST, but  `checkBalance(node)` at the end;
     * @param root
     * @param node
     */
    private void add(TreeNode root, TreeNode node) {
        // insert it to the right
        if (node.val == root.val) {
            throw new RuntimeException("values of the tree must be unique!");
        } else if (node.val > root.val) {
            if (root.right == null) {
                root.right = node;
                node.parent = root;
            } else {
                add(root.right, node);
            }
        } else {
            if (root.left == null) {
                root.left = node;
                node.parent = root;
            } else {
                add(root.left, node);
            }
        }
        // after inserting a node, check if the nodes are balanced from current node to the root;
        checkBalance(node);
    }

    /**
     * update height and check balance from node to root, if imbalance happens during updating,  call `rebalance` method.
     *
     * @param node
     */
    private void checkBalance(TreeNode node) {
        if (node == null) return;
        node.refreshHeight();

        if (Math.abs(getHeight(node.right) - getHeight(node.left)) > 1)
            rebalance(node);
        checkBalance(node.parent);
    }

    private void rebalance(TreeNode node) {
        TreeNode parent = node.parent;
        Boolean isNodeLeftChildOfItsParent = parent == null ? null : node == parent.left;

        // right child
        if (getHeight(node.right) - getHeight(node.left) > 1) {
            // right subtree
            if (getHeight(node.right.right) > getHeight(node.right.left)) {
                // left rotate
                node = leftRotate(node);
            } else {
                node = rightLeftRotate(node);
            }
        }
        else if (getHeight(node.right) - getHeight(node.left) < -1)
        { // left child
            // left subtree
            if (getHeight(node.left.left) > getHeight(node.left.right)) {
                // right rotate
                node = rightRotate(node);
            } else {
                node = leftRightRotate(node);
            }

        }

        // rebuild parent relationship: connect `parent` pointer
        node.parent = parent;
        if (parent == null) root = node;
        else {
            if (isNodeLeftChildOfItsParent) parent.left = node;
            else parent.right = node;
        }
    }

    public static TreeNode leftRotate(TreeNode grand) {
        TreeNode tmp = grand.right;

        grand.right = tmp.left;

        // AVL specific
        if (tmp.left != null)
            tmp.left.parent = tmp;


        tmp.left = grand;

        // AVL specific
        grand.parent = tmp;

        // AVL specific: update heights which has changed
        grand.refreshHeight();
        tmp.refreshHeight();

        return tmp;
    }

    public static TreeNode rightRotate(TreeNode grand) {
        TreeNode tmp = grand.left;

        grand.left = tmp.right;

        // AVL specific
        if (tmp.right != null)
            tmp.right.parent = tmp;

        tmp.right = grand;
        // AVL specific
        grand.parent = tmp;

        // AVL specific update heights which has changed
        grand.refreshHeight();
        tmp.refreshHeight();

        return tmp;
    }

    public static TreeNode rightLeftRotate(TreeNode grand) {
        // do a right rotation of parent.
        TreeNode tmp = rightRotate(grand.right);

        // AVL specific
        grand.right = tmp;
        tmp.parent = grand;

        // do a left rotation of grand;
        return leftRotate(grand);
    }

    public static TreeNode leftRightRotate(TreeNode grand) {
        // do a left rotation of parent.
        TreeNode tmp = leftRotate(grand.left);

        // AVL specific
        grand.left = tmp;
        tmp.parent = grand;

        // do a left rotation of grand;
        return rightRotate(grand);
    }

    public static void main(String[] args) {
//        testWhole(new int[] {6, 4, 2});

// https://www.youtube.com/watch?v=7m94k2Qhg68&list=PLpPXw4zFa0uKKhaSz87IowJnOTzh9tiBk&index=65
//        testAdd(new int[]{43, 18, 22, 9, 21, 6, 8, 20, 63, 50, 62, 51});
        testRemove();
    }

    private static void testAdd(int[] arr) {

        // 43, 18, 22, 9, 21, 6, 8, 20, 63, 50, 62, 51
        AVLTree tree = new AVLTree();

        for (int num : arr) {
            tree.add(new TreeNode(num));
            BSTUtil.print(tree.root);
            System.out.println("-------");

        }
    }

    private static void testRemove() {
        AVLTree tree = new AVLTree();

        int[] arr =  new int[]{43, 18, 22, 9, 21, 6, 8, 20, 63, 50, 62, 51};
        for (int num : arr) {
            TreeNode node = new TreeNode(num);
            tree.add(node);
        }
        System.out.println("tree built-------");
        BSTUtil.print(tree.root);
        for (int i = arr.length-1; i >= 0; i--) {
            System.out.println(arr[i]  + "is going to be removed -------");
            tree.remove(arr[i]);
            BSTUtil.print(tree.root);
            System.out.println("");
        }
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
