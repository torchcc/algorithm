package com.maikang.datastructure.tree.bst.redblack;

public interface IBinarySearchTree<K extends Comparable<?>, V> {

    void add(K k, V v);

    void clear();
}
