package com.abramchik.taskTwoCollections.treeSet;

public interface MyTreeSet<V>{

    boolean add(V value);

    int size();

    void clear();

    boolean contains(V value);

    boolean remove(V value);

    V first();

    V last();
}