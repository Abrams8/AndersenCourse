package com.abramchik.taskTwoCollections.treeSet;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MyTreeSetImpl<V> implements MyTreeSet<V> {

    private static final int HASH_FUNCTION_INITIAL_VALUE = 31;
    private static final int HASH_FUNCTION_MULTIPLICATOR = 17;
    private static final int NEGATIVE_COMPARE_RESULT = -1;
    private static final int OBJECTS_ARE_EQUAL = 0;
    private static final int POSITIVE_COMPARE_RESULT = 1;

    Node<V> node;
    int size;

    public MyTreeSetImpl() {
        this.node = null;
        size = 0;
    }

    @Override
    public boolean add(V value) {
        checkValueNotNull(value);
        if (addFirstElement(value) == true) {
            return true;
        } else {
            Node<V> newNode = new Node<>(value);
            boolean result = addNewNode(newNode, node, null);
            if (result) {
                size++;
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean addNewNode(Node<V> newNode, Node<V> currentNode, Node<V> previousNode) {
        int compareResult = currentNode.compareTo(newNode.getValue());
        if (compareResult == OBJECTS_ARE_EQUAL) {
            return false;
        } else if (compareResult == POSITIVE_COMPARE_RESULT) {
            previousNode = currentNode;
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                return addNewNode(newNode, currentNode, previousNode);
            } else {
                currentNode.setRight(newNode);
                return true;
            }
        } else {
            previousNode = currentNode;
            if (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
                return addNewNode(newNode, currentNode, previousNode);
            } else {
                currentNode.setLeft(newNode);
                return true;
            }
        }
    }

    private boolean checkValueNotNull(V value) {
        if (value == null) {
            throw new NullPointerException();
        } else {
            return true;
        }
    }

    private boolean addFirstElement(V value) {
        if (size == 0) {
            this.node = new Node<>(value);
            size++;
            return true;
        } else {
            return false;
        }
    }

    private List<Node<V>> findNode(Node<V> findNode, Node<V> currentNode, Node<V> previousNode) {
        int compareResult = currentNode.compareTo(findNode.getValue());
        if (compareResult == OBJECTS_ARE_EQUAL) {
            List<Node<V>> result = new LinkedList<>();
            result.add(previousNode);
            result.add(currentNode);
            return result;
        } else if (compareResult == POSITIVE_COMPARE_RESULT) {
            previousNode = currentNode;
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                return findNode(findNode, currentNode, previousNode);
            } else {
                return null;
            }
        } else {
            previousNode = currentNode;
            if (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
                return findNode(findNode, currentNode, previousNode);
            } else {
                return null;
            }
        }
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
    public boolean contains(V value) {
        checkValueNotNull(value);
        Node<V> findNode = new Node<>(value);
        List<Node<V>> list = findNode(findNode, node, null);
        return list != null;
    }

    private Set<V> allElementsUtil(Node<V> currentNode, Set<V> set){
        if(currentNode.getLeft() == null && currentNode.getRight() != null){
            set.add(currentNode.value);
            allElementsUtil(currentNode.getRight(), set);
        }else if(currentNode.getLeft() != null && currentNode.getRight() == null){
            set.add(currentNode.value);
            allElementsUtil(currentNode.getLeft(), set);
        }else if(currentNode.getLeft() != null && currentNode.getRight() != null){
            set.add(currentNode.value);
            allElementsUtil(currentNode.getLeft(), set);
            allElementsUtil(currentNode.getRight(), set);
        } else {
            set.add(currentNode.value);
        } return set;
    }

    @Override
    public boolean remove(V value) {
        checkValueNotNull(value);
        Node<V> removedNode = new Node<>(value);
        List<Node<V>> list = findNode(removedNode, node, null);
        if (list != null) {
            if (size == 1) {
                clear();
                return true;
            }
            Node<V> previousNode = list.get(0);
            if (previousNode == null) {
                Node<V> currentNode = list.get(1);
                if (currentNode.getLeft() == null) {
                    node = currentNode.getRight();
                    size--;
                    return true;
                } else if (currentNode.getRight() == null) {
                    node = currentNode.getLeft();
                    size--;
                    return true;
                } else {
                    Node<V> newNode = currentNode.getLeft();
                    node = currentNode.getRight();
                    size--;
                    addNewNode(newNode, node, null);
                    return true;
                }
            }
            int compareResultRight = previousNode.getRight().compareTo(removedNode.value);
            int compareResultLeft = previousNode.getLeft().compareTo(removedNode.value);
            if (previousNode.getRight() != null && compareResultRight == OBJECTS_ARE_EQUAL) {
                Node<V> currentNode = list.get(1);
                Node<V> currentNodeRight = currentNode.getRight();
                Node<V> currentNodeLeft = currentNode.getLeft();
                if (currentNodeLeft == null && currentNodeRight == null) {
                    previousNode.setRight(null);
                } else if (currentNodeLeft == null && currentNodeRight != null) {
                    previousNode.setRight(currentNodeRight);
                } else if (currentNodeLeft != null && currentNodeRight == null) {
                    previousNode.setRight(currentNodeLeft);
                } else {
                    previousNode.setRight(currentNodeRight);
                    addNewNode(currentNodeLeft, node, null);
                }
            } else {
                if (previousNode.getLeft() != null && compareResultLeft == OBJECTS_ARE_EQUAL) {
                    Node<V> currentNode = list.get(1);
                    Node<V> currentNodeRight = currentNode.getRight();
                    Node<V> currentNodeLeft = currentNode.getLeft();
                    if (currentNodeLeft == null && currentNodeRight == null) {
                        previousNode.setLeft(null);
                    } else if (currentNodeLeft == null && currentNodeRight != null) {
                        previousNode.setLeft(currentNodeRight);
                    } else if (currentNodeLeft != null && currentNodeRight == null) {
                        previousNode.setLeft(currentNodeLeft);
                    } else {
                        previousNode.setLeft(currentNodeRight);
                        addNewNode(currentNodeLeft, node, null);
                    }
                }

            }
            size--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public V first() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return this.node.getValue();
        } else {
            Node<V> currentNode = this.node;
            while (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }
            return currentNode.getValue();
        }
    }

    @Override
    public V last() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return this.node.getValue();
        } else {
            Node<V> currentNode = this.node;
            while (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
            }
            return currentNode.getValue();
        }
    }
@Getter
    private class Node<V> implements Comparable<V> {
        @Setter
        private Node right;

        @Setter
        private Node left;

        private V value;

        public Node(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(V value) {
            Node<V> node = new Node<>(value);
            if (this.hashCode() > node.hashCode()) {
                return NEGATIVE_COMPARE_RESULT;
            } else if (this.hashCode() == node.hashCode() && this.equals(node)) {
                return OBJECTS_ARE_EQUAL;
            } else {
                return POSITIVE_COMPARE_RESULT;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            int hash = HASH_FUNCTION_INITIAL_VALUE;
            hash = hash * HASH_FUNCTION_MULTIPLICATOR + value.hashCode();
            return hash;
        }
    }
}