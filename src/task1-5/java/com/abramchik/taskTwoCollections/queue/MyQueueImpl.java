package com.abramchik.taskTwoCollections.queue;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyQueueImpl implements MyQueue {

    Object[] array;
    int front;
    int rear;
    final int capacity;
    int size;

    public MyQueueImpl(int size) {
        array = new Object[size];
        capacity = size;
        front = -1;
        rear = -1;
        this.size = 0;
    }

    @Override
    public boolean dequeue() {
        if (isEmpty()) {
            return false;
        } else {
            array[rear] = null;
            rear--;
            size--;
            if (size == 0) {
                front--;
            }
            return true;
        }
    }

    @Override
    public boolean enqueue(Object object) {
        if (isFull()) {
            return false;
        } else {
            rear++;
            array[size] = object;
            size++;
            if (front == -1) {
                front++;
            }return true;
        }
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            return null;
        } else {
            return array[rear];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return capacity == size;
    }
}
