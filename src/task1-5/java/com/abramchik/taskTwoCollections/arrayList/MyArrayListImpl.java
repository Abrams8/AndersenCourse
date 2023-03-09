package com.abramchik.taskTwoCollections.arrayList;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Collection;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyArrayListImpl<T> implements MyArrayList<T> {
    static final int DEFAULT_CAPACITY = 10;
    static final Object[] EMPTY_COLLECTION = {};
    int size;
    int capacity;
    Object[] array;

    public MyArrayListImpl() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public MyArrayListImpl(int capacity) {
        if (capacity > 0) {
            this.array = new Object[capacity];
            this.size = 0;
            this.capacity = capacity;
        } else if (capacity == 0) {
            this.array = new Object[DEFAULT_CAPACITY];
            this.size = 0;
            this.capacity = DEFAULT_CAPACITY;
        } else {
            throw new IllegalArgumentException("Illigal capacity " + capacity);
        }
    }

    public MyArrayListImpl(Collection<? extends T> collection) {

        if (collection.size() == 0) {
            this.array = new Object[DEFAULT_CAPACITY];
            this.size = 0;
            this.capacity = DEFAULT_CAPACITY;
        } else {
            this.size = collection.size();
            this.capacity = size;
            this.array = new Object[capacity];
            collection.toArray(array);
        }
    }

    @Override
    public void add(T element) {
        if (capacity == 0) {
            this.array = new Object[DEFAULT_CAPACITY];
            capacity = DEFAULT_CAPACITY;
            array[size] = element;
            size++;
        }
        else if (capacity < (size + 1) && element != null) {
            capacity = (int) ((capacity * 1.5) + 1);
            array = Arrays.copyOf(array, capacity);
            array[size] = element;
            size++;
        }
        else if (capacity >= (size + 1) && element != null) {
            array[size] = element;
            size++;
        }
    }

    @Override
    public void add(int index, T element) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (capacity <= size) {
            grow();
        }

        Object[] newArray = new Object[capacity];

        if (size == index) {
            array[index] = element;
            size++;
        }
        if (index == 0) {
            System.arraycopy(array, 0, newArray, 1, size);
            newArray[0] = element;
            array = newArray;
            size++;
        }
        if (index > 0) {
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = element;
            System.arraycopy(array, index, newArray, index + 1, size - index);
            array = newArray;
            size++;
        }

    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return (T) array[index];
        }
    }

    @Override
    public boolean contains(T o) {
        return searchElement(o) == 1;
    }

    @Override
    public void set(int index, T o) {
        try {
            array[index] = o;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newArray = new Object[capacity];

        if (index == 0) {
            System.arraycopy(array, 1, newArray, 0, size - 1);
            array = newArray;
            size--;
        }
        if (index > 0) {
            System.arraycopy(array, 0, newArray, 0, index);

            System.arraycopy(array, index + 1, newArray, index, size - 1);
            array = newArray;
            size--;
        }

    }

    @Override
    public void remove(T o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                remove(i);
                break;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = EMPTY_COLLECTION;
        size = 0;
        capacity = 0;
    }


    @Override
    public String toString() {
        return "MyArrayListImpl{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    private void grow() {
        this.capacity = (int) ((array.length * 1.5) + 1);
        array = Arrays.copyOf(array, capacity);
    }

    private int searchElement(T o) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                result = 1;
                break;
            }
        }
        return result;
    }

}