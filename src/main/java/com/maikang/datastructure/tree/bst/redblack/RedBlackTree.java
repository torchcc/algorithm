package com.maikang.datastructure.tree.bst.redblack;


/**
 * <a href="https://www.youtube.com/watch?v=nMExd4DthdA&list=PLpPXw4zFa0uKKhaSz87IowJnOTzh9tiBk&index=66"> Red Black Tree</a>
 * <p>
 * 1. every node is ether red or black
 * 2. Root is always black
 * 3. new insertions are always red
 * 4. every path from root to leaf has the same number of BLACK nodes
 * 5. no path can have tow consecutive RED nodes
 * 6. Nulls are black
 * <p>
 * <p>
 * the way to correct/Rebalance Tree:
 * when we encounter 2 consecutive black ndoes in a path
 * 1. if aunt is black: we'll do a rotation. After rotation, the three nodes(grand, parent, me the troublemaker) we were working on end up as
 * <p>
 * black
 * /   \
 * red   red
 * <p>
 * <p>
 * <p>
 * 2. if aunt is red: we'll do color flip. After flipping,
 * <p>
 * red
 * /   \
 * black  black
 * <p>
 * (troublemaker's) grand is red, parent and aunt is black
 */


public class RedBlackTree<K extends Comparable<?>, V> implements IBinarySearchTree<K, V> {
    private Node<K, V> root;
    private int size;

    @Override
    public void add(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        if (root == null) {
            root = node;
            root.black = true;
        } else {
            add(root, node);
        }
        size++;

        checkColor(node);
    }

    @Override
    public void clear() {
        this.root = null;
    }

    private void add(Node<K, V> parent, Node<K, V> node) {
        // if duplicate is allowed, we can use >= instead of >
        if (((Comparable<K>) node.key).compareTo(parent.key) > 0) {
            if (parent.right == null) {
                parent.right = node;
                node.parent = parent;
                node.isLeftChild = false;
                return;
            }
            add(parent.right, node);
        } else {
            if (parent.left == null) {
                parent.left = node;
                node.parent = parent;
                node.isLeftChild = true;
                return;
            }
            add(parent.left, node);
        }
    }

    private void checkColor(Node<K, V> node) {
        if (node == null) return;
        if (node == root) {
            node.black = true;
            return;
        }

        // got 2 consecutive red node:
        if (!node.black && (node.parent != null && !node.parent.black)) {
            rebalance(node);
        }
        checkColor(node.parent);
    }

    public void rebalance(Node<K, V> node) {
        // aunt is grand(node.parent.parent)'s right child
        if (node.parent.isLeftChild) {
            // black aunt: we do rotation
            if (node.parent.parent.right == null || node.parent.parent.right.black) {
                rotate(node);
            } else {
                // red aunt, we'll do color flip.
                node.parent.parent.right.black = true;
                node.parent.black = true;
                node.parent.parent.black = false;
            }

        } else { // aunt is grand's left child
            // black aunt: we do rotation
            if (node.parent.parent.left == null || node.parent.parent.left.black) {
                rotate(node);
            } else {
                // red aunt, we'll do color flip.
                node.parent.parent.left.black = true;
                node.parent.black = true;
                node.parent.parent.black = false;

            }
        }

    }

    private void rotate(Node<K, V> node) {
        if (node.isLeftChild) { //   x child's left subtree
            if (node.parent.isLeftChild) { // left child's left subtree   LL
                rightRotate(node.parent.parent);
                // change color after rotation
                node.black = false;
                node.parent.black = true;
                node.parent.right.black = false;
            } else { // right child's left subtree                RL
                rightLeftRotate(node.parent.parent);
                // change color
                node.black = true;
                node.left.black = false;
                node.right.black = false;
            }
        } else { // x child's right subtree
            // left child's right subtree                   LR
            if (node.parent.isLeftChild) {
                leftRightRotate(node.parent.parent);
                // change color
                node.black = true;
                node.left.black = false;
                node.right.black = false;
            } else {
                // right child's right subtree           RR
                leftRotate(node.parent.parent);
                // change color
                node.black = false;
                node.parent.black = true;
                node.parent.left.black = false;
            }

        }
    }

    private void rightLeftRotate(Node<K, V> grand) {
        rightRotate(grand.right);
        leftRotate(grand);
    }

    private void leftRightRotate(Node<K, V> grand) {
        leftRotate(grand.left);
        rightRotate(grand);
    }


    public void leftRotate(Node<K, V> grand) {
        Node<K, V> tmp = grand.right;

        connectTmpWithGrandsParent(grand, tmp);

        grand.right = tmp.left;
        if (grand.right != null) {
            grand.right.parent = grand;
            grand.right.isLeftChild = false;
        }


        tmp.left = grand;
        grand.isLeftChild = true;
        grand.parent = tmp;
    }


    public void rightRotate(Node<K, V> grand) {
        Node<K, V> tmp = grand.left;

        connectTmpWithGrandsParent(grand, tmp);

        grand.left = tmp.right;
        if (grand.left != null) {
            grand.left.parent = grand;
            grand.left.isLeftChild = true;
        }


        tmp.right = grand;
        grand.isLeftChild = false;
        grand.parent = tmp;
    }

    private void connectTmpWithGrandsParent(Node<K, V> grand, Node<K, V> tmp) {
        tmp.parent = grand.parent;
        if (grand.parent == null) root = tmp;
        else {
            if (grand.isLeftChild) {
                tmp.isLeftChild = true;
                tmp.parent.left = tmp;
            } else {
                tmp.isLeftChild = false;
                tmp.parent.right = tmp;
            }
        }
    }

    // define: the height of one node and null are both 0;
    public int height() {
        int h = height(root);
        return h == -1 ? 0 : h;
    }

    private int height(Node<K, V> node) {
        if (node == null) return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public int blackNodes(Node<K, V> node) {
        if (node == null) return 1;
        int rightBlackNodes = blackNodes(node.right);
        int leftBlackNodes = blackNodes(node.right);
        if (rightBlackNodes != leftBlackNodes) {
            throw new RuntimeException("violate the rule that any path of red-black tree has the same number of black nodes");
        }
        if (node.black) leftBlackNodes++;
        return leftBlackNodes;
    }


    private static void testAdd() {

        // 43, 18, 22, 9, 21, 6, 8, 20, 63, 50, 62, 51
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        int[] arr = new int[]{43, 18, 22, 9, 21, 6, 8, 20, 63, 50, 62, 51};
        for (int num : arr) {
            tree.add(num, num);
            System.out.printf("adding %d...%n", num);
            System.out.println("-------");
            BTreePrinter.printNode(tree.root);
        }

        System.out.println("===============");

        // https://www.youtube.com/watch?v=v6eDztNiJwo&list=PLpPXw4zFa0uKKhaSz87IowJnOTzh9tiBk&index=67
        tree.clear();
        arr = new int[]{3, 1, 5, 7, 6, 8, 9, 10};
        for (int num : arr) {
            tree.add(num, num);
            System.out.printf("adding %d...%n", num);
            System.out.println("-------");
            BTreePrinter.printNode(tree.root);
        }

    }

    public static void main(String[] args) {

        testAdd();
    }


}


