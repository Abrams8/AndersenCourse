package com.abramchik.taskTwoCollections.hashMap;

public interface MyHashMap<K, V> {

    boolean put(K key, V value);

    V get(K key);

    int size();

    void clear();

    boolean isEmpty();

    V remove(K key);

    boolean remove(K key, V value);

    boolean containsKey(K key);

    boolean containsValue(V value);

}
