package com.abramchik.taskTwoCollections.hashMap;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int HASH_TABLE_MULTIPLICATOR = 2;
    private static final int HASH_FUNCTION_INITIAL_VALUE = 31;
    private static final int HASH_FUNCTION_MULTIPLICATOR = 17;

    private Node<K, V>[] hashTable;
    private int size = 0;
    private float threshold;


    public MyHashMapImpl() {
        hashTable = new Node[DEFAULT_INITIAL_CAPACITY];
        threshold = DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR;
    }

    private int hash(K key) {
        int hash = HASH_FUNCTION_INITIAL_VALUE;
        hash = hash * HASH_FUNCTION_MULTIPLICATOR * key.hashCode();
        return Math.abs(hash % hashTable.length);
    }


    @Override
    public boolean put(K key, V value) {
        if (size + 1 >= threshold) {
            threshold *= 2;
            arrayGrow();
        }

        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key);

        if (hashTable[index] == null) {
            return simpleAdd(index, newNode);
        }

        List<Node<K, V>> nodeList = hashTable[index].getNodes();

        if (keyExists(key)) {
            for (Node<K, V> node : nodeList) {
                if (key.equals(node.getKey())) {
                    node.setValue(value);
                }
            }
        } else {
            nodeList.add(newNode);
            size++;
        }
        return true;
    }

    private void arrayGrow() {
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = new Node[oldHashTable.length * HASH_TABLE_MULTIPLICATOR];
        size = 0;
        for (Node<K, V> node : oldHashTable) {
            if (node != null) {
                for (Node<K, V> i : node.getNodes()) {
                    put(i.key, i.value);
                }
            }
        }
    }

    private boolean keyExists(K key) {
        if (key == null) {
            return false;
        }
        int index = hash(key);
        if (index > hashTable.length || hashTable[index] == null) {
            return false;
        }
        List<Node<K, V>> nodeList = hashTable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            if (key.equals(node.getKey())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        int index = hash(key);
        if (index > hashTable.length || hashTable[index] == null) {
            return null;
        } else if (!keyExists(key)) {
            return null;
        } else {
            List<Node<K, V>> nodeList = hashTable[index].getNodes();

            if (nodeList.size() == 1) {
                return nodeList.get(0).getValue();
            }
            for (Node<K, V> node : nodeList) {
                if (key.equals(node.getKey())) {
                    return node.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        hashTable = new Node[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        int index = hash(key);
        V result;
        if (hashTable[index] == null || !keyExists(key)) {
            return null;
        }
        List<Node<K, V>> nodeList = hashTable[index].getNodes();
        if (nodeList.size() == 1) {
            result = nodeList.get(0).getValue();
            hashTable[index] = null;
            size--;
            return result;
        } else {
            for (Node<K, V> node : nodeList) {
                if (key.equals(node.getKey())) {
                    result = node.getValue();
                    nodeList.remove(node);
                    size--;
                    return result;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        if (containsKey(key) && containsValue(value)) {
            remove(key);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return keyExists(key);
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null) {
                continue;
            }
            List<Node<K, V>> nodeList = hashTable[i].getNodes();
            for (Node<K, V> node : nodeList) {
                if (node.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean simpleAdd(int index, Node<K, V> newNode) {
        hashTable[index] = new Node<>(null, null);
        hashTable[index].getNodes().add(newNode);
        size++;
        return true;
    }

    @Getter
    private class Node<K, V> {
        private K key;
        @Setter
        private V value;
        private List<Node<K, V>> nodes;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<>();
        }

        private int hash() {
            return hashCode() % hashTable.length;
        }

        @Override
        public int hashCode() {
            int hash = HASH_FUNCTION_INITIAL_VALUE;
            hash = hash * HASH_FUNCTION_MULTIPLICATOR + key.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}

