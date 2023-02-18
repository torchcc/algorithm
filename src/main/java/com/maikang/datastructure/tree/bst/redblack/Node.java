package com.maikang.datastructure.tree.bst.redblack;


import static com.maikang.datastructure.tree.bst.redblack.BTreePrinter.*;

class Node<K extends Comparable<?>, V> {
    K key;
    V value;

    Node<K, V> left;
    Node<K, V> right;
    Node<K, V> parent;

    boolean isLeftChild;
    boolean black;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return black ?
               ANSI_GREEN_BACKGROUND + ANSI_BLACK + key.toString() + ANSI_RESET
               : ANSI_GREEN_BACKGROUND + ANSI_RED + key.toString() + ANSI_RESET;

    }
}