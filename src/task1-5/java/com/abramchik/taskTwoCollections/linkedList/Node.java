package com.abramchik.taskTwoCollections.linkedList;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Node<T> {
    T currentElement;
    Node<T> previousElement;
    Node<T> nextElement;
}
