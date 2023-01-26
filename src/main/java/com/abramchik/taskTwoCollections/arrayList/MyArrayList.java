package com.abramchik.taskTwoCollections.arrayList;

public interface MyArrayList<T> {

    void add(T t);

    void add(int index, T element);

    T get(int index);

    boolean contains(T t);

    void set(int index, T t);

    void remove(int index);

    void remove(T t);

    int size();

    void clear();
}
