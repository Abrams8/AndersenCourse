package com.abramchik.taskTwoCollections.treeMap;

import java.util.Set;

public interface MyTreeMap<K, V> {

    boolean put(K key, V value);

    V get(K key);

    boolean remove(K key);

    int size();

    void clear();

    Set<K> keySet();

    K getLastKey();

    K getFirstKey();
}