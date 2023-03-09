package com.abramchik.collections;

import com.abramchik.taskTwoCollections.linkedList.MyLinkedList;
import com.abramchik.taskTwoCollections.linkedList.MyLinkedListImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTest {

    private MyLinkedList<Integer> list;
    private Integer value;

    @Before
    public void addElementToList(){
        list = new MyLinkedListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void addElementShouldBeAddedToList(){
        list.add(123);
        Assert.assertEquals(value = 123, list.getElement(3));
        Assert.assertEquals(4, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addElementByIndex(){
        list.add(1, 5000);
        Assert.assertEquals(value = 5000, list.getElement(1));
        Assert.assertEquals(value = 1, list.getElement(0));
        Assert.assertEquals(value = 2, list.getElement(2));
        list.add(21, 3242);
    }

    @Test
    public void addLastElement(){
        list.addLast(55);
        Assert.assertEquals(value = 55, list.getLast());
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void addFirstElement(){
        list.addFirst(65);
        Assert.assertEquals(value = 65, list.getFirst());
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void sizeShouldBeChanged(){
        Assert.assertEquals(3, list.size());
        list.addLast(222);
        list.addLast(333);
        Assert.assertEquals(5, list.size());
        list.remove(1);
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void getElementByIndex(){
        Assert.assertEquals(value = 1, list.getElement(0));
        Assert.assertEquals(value = 3, list.getElement(2));
    }

    @Test
    public void getLastElement(){
        Assert.assertEquals(value = 3, list.getLast());
    }

    @Test
    public void getFirstElement(){
        Assert.assertEquals(value = 1, list.getFirst());
    }

    @Test
    public void listShouldBeClear(){
        list.clear();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void removeElementByIndex(){
        list.remove(0);
        Assert.assertEquals(value = 2, list.getFirst());
        list.remove(1);
        Assert.assertEquals(value = 2, list.getLast());
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void removeElement(){
        value = 2;
        list.remove(value);
        Assert.assertEquals(value = 3, list.getElement(1));
    }
}
