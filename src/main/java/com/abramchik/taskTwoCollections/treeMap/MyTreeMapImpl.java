package com.abramchik.taskTwoCollections.treeMap;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class MyTreeMapImpl<K, V> implements MyTreeMap<K, V> {

    private static final int NEGATIVE_COMPARE_RESULT = -1;
    private static final int OBJECTS_ARE_EQUAL = 0;
    private static final int POSITIVE_COMPARE_RESULT = 1;
    private static final int HASH_FUNCTION_INITIAL_VALUE = 31;
    private static final int HASH_FUNCTION_MULTIPLICATOR = 17;

    private int size;
    private Node<K, V> node;

    public MyTreeMapImpl() {
        this.size = 0;
        this.node = null;
    }

    @Override
    public boolean put(K key, V value) {
        checkKeyNotNull(key);
        if (size == 0) {
            return addFirstElement(key, value);
        }
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> nextLeftNode = node.getLeft();
        Node<K, V> nextRightNode = node.getRight();
        Node<K, V> currentNode = node;
        while (true) {
            int compareResult = currentNode.compareTo(newNode);
            if (compareResult == OBJECTS_ARE_EQUAL) {
                currentNode.setValue(value);
                return true;
            }
            if (compareResult == NEGATIVE_COMPARE_RESULT) {
                if (nextLeftNode == null) {
                    currentNode.setLeft(newNode);
                    size++;
                    return true;
                } else {
                    currentNode = nextLeftNode;
                    nextRightNode = currentNode.getRight();
                    nextLeftNode = currentNode.getLeft();
                    continue;
                }
            }
            if (compareResult == POSITIVE_COMPARE_RESULT) {
                if (nextRightNode == null) {
                    currentNode.setRight(newNode);
                    size++;
                    return true;
                } else {
                    currentNode = nextRightNode;
                    nextRightNode = currentNode.getRight();
                    nextLeftNode = currentNode.getLeft();
                    continue;
                }
            }
        }
    }

    private boolean addFirstElement(K key, V value) {
        if (node == null) {
            node = new Node(key, value);
            size++;
            return true;
        } else {
            return false;
        }
    }

    private void checkKeyNotNull(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public V get(K key) {
        checkKeyNotNull(key);
        Node<K, V> newNode = new Node<>(key, null);
        try {
            return (V) getValueUtil(node, newNode);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private V getValueUtil(Node<K, V> currentNode, Node<K, V> findNode) {
        if (currentNode.compareTo(findNode) == OBJECTS_ARE_EQUAL) {
            findNode.setValue(currentNode.getValue());
        } else if (currentNode.compareTo(findNode) == NEGATIVE_COMPARE_RESULT) {
            getValueUtil(currentNode.getLeft(), findNode);
        } else if (currentNode.compareTo(findNode) == POSITIVE_COMPARE_RESULT) {
            getValueUtil(currentNode.getRight(), findNode);
        }
        return findNode.getValue();
    }

    @Override
    public boolean remove(K key) {
        checkKeyNotNull(key);
        Node<K, V> removedNode = new Node<>(key, null);
        Node<K, V> previousNode = new Node<>(null, null);
        try {
            return deleteValueUtil(node, removedNode, previousNode);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private boolean deleteValueUtil(Node<K, V> currentNode, Node<K, V> removedNode, Node<K, V> previousNode) {
        if (currentNode.compareTo(removedNode) == OBJECTS_ARE_EQUAL) {
            try {
                previousNode.getRight();
                if (previousNode.getRight().compareTo(currentNode) == OBJECTS_ARE_EQUAL) {
                    previousNode.setRight(null);
                    size--;
                } else {
                    previousNode.getLeft();
                    if (previousNode.getLeft().compareTo(currentNode) == OBJECTS_ARE_EQUAL) {
                        previousNode.setLeft(null);
                        size--;
                    }
                }
            } catch (NullPointerException e) {
                previousNode.setLeft(null);
                size--;
            }
        } else if (currentNode.compareTo(removedNode) == NEGATIVE_COMPARE_RESULT) {
            deleteValueUtil(currentNode.getLeft(), removedNode, currentNode);
        } else if (currentNode.compareTo(removedNode) == POSITIVE_COMPARE_RESULT) {
            deleteValueUtil(currentNode.getRight(), removedNode, currentNode);
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.node = null;
        size = 0;
    }

    @Override
    public Set keySet() {
        Set<K> set = new LinkedHashSet<>();
        keySetRecurs(node, set);
        return set;
    }

    private void keySetRecurs(Node node, Set<K> set) {
        if (node == null) {
            return;
        } else {
            keySetRecurs(node.getLeft(), set);
            set.add((K) node.getKey());

            keySetRecurs(node.getRight(), set);
            set.add((K) node.getKey());
        }
    }

    @Override
    public K getLastKey() {
        if (size == 0) {
            return null;
        } else {
            Node currentNode = node;
            while (currentNode.right != null) {
                currentNode = currentNode.right;
            }
            return (K) currentNode.key;
        }
    }

    @Override
    public K getFirstKey() {
        if (size == 0) {
            return null;
        } else {
            Node currentNode = node;
            while (currentNode.left != null) {
                currentNode = currentNode.left;
            }
            return (K) currentNode.key;
        }
    }

    @Getter
    private class Node<K, V> implements Comparable<Node<K, V>> {

        private K key;

        @Setter
        private V value;

        @Setter
        private Node left;

        @Setter
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node<K, V> node) {
            if (this.hashCode() > node.hashCode()) {
                return POSITIVE_COMPARE_RESULT;
            } else if (this.hashCode() == node.hashCode() && this.equals(node)) {
                return OBJECTS_ARE_EQUAL;
            } else {
                return NEGATIVE_COMPARE_RESULT;
            }
        }

        @Override
        public int hashCode() {
            int hash = HASH_FUNCTION_INITIAL_VALUE;
            hash = hash * HASH_FUNCTION_MULTIPLICATOR + key.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Node<K, V> node = (Node<K, V>) object;
            return Objects.equals(key, node.key);
        }
    }
}