package com.abramchik.taskTwoCollections.linkedList;

public interface MyLinkedList<T> {

    void add(T t);

    void add(int index, T t);

    void addLast(T t);

    void addFirst(T t);

    int size();

    T getElement(int index);

    T getLast();

    T getFirst();

    void clear();

    void remove(int index);

    void remove(T t);
}
