package com.abramchik.taskTwoCollections.linkedList;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Iterator;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyLinkedListImpl<T> implements MyLinkedList<T>, Iterable<T>, DescendingIterator<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    @Override
    public void add(T t) {
        if (size == 0) {
            addFirst(t);
        } else {
            addLast(t);
        }
    }

    @Override
    public void add(int index, T t) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            addLast(t);
        } else if (index == 0) {
            addFirst(t);
        } else {
            Node<T> element = findNode(index);
            Node<T> previousElement = element.getPreviousElement();
            Node<T> nextElement = element.getNextElement();
            if (nextElement == null) {
                Node<T> pastedElement = new Node<T>(t, previousElement, element);
                element.setPreviousElement(pastedElement);
                previousElement.setNextElement(pastedElement);
            } else {
                Node<T> pastedElement = new Node<T>(t, previousElement, element);
                previousElement.setNextElement(pastedElement);
                element.setPreviousElement(pastedElement);
            }
            size++;
        }
    }

    @Override
    public void addLast(T t) {
        if (lastNode == null && firstNode == null) {
            addFirst(t);
        } else {
            Node<T> currentNode = lastNode;
            lastNode = new Node<>(t, currentNode, null);
            currentNode.setNextElement(lastNode);
            size++;
        }
    }

    @Override
    public void addFirst(T t) {
        if (firstNode != null) {
            Node<T> currentNode = firstNode;
            firstNode = new Node<>(t, null, currentNode);
            currentNode.setPreviousElement(firstNode);
            if (lastNode == null) {
                lastNode = currentNode;
            }
        } else {
            firstNode = new Node<>(t, null, null);
            if (lastNode == null) {
                lastNode = firstNode;
            }
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getElement(int index) {
        return findNode(index).getCurrentElement();
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public T getLast() {
        return lastNode.getCurrentElement();
    }

    @Override
    public T getFirst() {
        return firstNode.getCurrentElement();
    }

    @Override
    public void remove(int index) {
        Node<T> node = findNode(index);
        if (node.getNextElement() == null) {
            lastNode = lastNode.getPreviousElement();
            lastNode.setNextElement(null);
            size--;
        } else if (node.getPreviousElement() == null) {
            firstNode = firstNode.getNextElement();
            firstNode.setPreviousElement(null);
            size--;
        } else {
            Node<T> nextElement = node.getNextElement();
            Node<T> previousElement = node.getPreviousElement();
            nextElement.setPreviousElement(previousElement);
            previousElement.setNextElement(nextElement);
            size--;
        }
    }

    @Override
    public void remove(T t) {
        for (Node<T> i = firstNode; i != null; i = i.getNextElement()) {
            if (i.getCurrentElement().equals(t)) {
                removeNode(i);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public T next() {
                return getElement(counter++);
            }
        };
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<>() {
            int counter = size - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public T next() {
                return getElement(counter--);
            }
        };
    }

    private Node<T> findNode(int index) {
        if (index == 0) {
            return firstNode;
        } else if (index == (size - 1)) {
            return lastNode;
        } else if (index > 0 && index < size - 1) {
            Node<T> node = firstNode;
            for (int i = 1; i <= index; i++) {
                node = node.getNextElement();
            }
            return node;
        } else throw new IndexOutOfBoundsException();
    }

    private void removeNode(Node<T> node) {
        Node<T> nextElement = node.getNextElement();
        Node<T> previousElement = node.getPreviousElement();
        if (nextElement == null) {
            previousElement.setNextElement(null);
            lastNode = previousElement;
            size--;
        } else if (previousElement == null) {
            nextElement.setPreviousElement(null);
            firstNode = nextElement;
            size--;
        } else {
            nextElement.setPreviousElement(previousElement);
            previousElement.setNextElement(nextElement);
            size--;
        }
    }
}
