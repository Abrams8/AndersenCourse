package com.abramchik.taskTwoCollections.queue;

public interface MyQueue<T> {

    boolean dequeue();

    boolean enqueue(T t);

    T peek();

    int size();

    boolean isEmpty();

    boolean isFull();


}
