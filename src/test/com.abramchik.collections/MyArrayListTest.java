package com.abramchik.collections;

import com.abramchik.taskTwoCollections.arrayList.MyArrayList;
import com.abramchik.taskTwoCollections.arrayList.MyArrayListImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyArrayListTest {

    private MyArrayList<String> arrayList;

    @Before
    public void addElementsToList() {
        arrayList = new MyArrayListImpl<>();
        arrayList.add("Dima");
        arrayList.add("Lena");
        arrayList.add("Egor");
        arrayList.add("Ignat");
    }

    @Test
    public void addElementsShouldBeAddedToArrayList() {
        arrayList.add("Kirill");
        arrayList.add("Iosiff");
        arrayList.add("Nastya");
        arrayList.add("Vika");
        arrayList.add("Ron");
        arrayList.add("Bill");
        arrayList.add("Sasha");
        Assert.assertEquals(11, arrayList.size());
        Assert.assertEquals("Dima", arrayList.get(0));
        Assert.assertEquals("Ron", arrayList.get(8));
    }

    @Test
    public void addElementShouldBeAddedToListByIndex() {
        arrayList.add(2, "Oleg");
        Assert.assertEquals("Lena", arrayList.get(1));
        Assert.assertEquals("Oleg", arrayList.get(2));
        Assert.assertEquals("Egor", arrayList.get(3));
        Assert.assertEquals(5, arrayList.size());
    }

    @Test
    public void containsElement() {
        Assert.assertEquals(true, arrayList.contains("Ignat"));
        Assert.assertEquals(false, arrayList.contains("Tutu"));
    }

    @Test
    public void setElementByIndex() {
        arrayList.set(3, "Leonid");
        Assert.assertEquals("Egor", arrayList.get(2));
        Assert.assertEquals("Leonid", arrayList.get(3));
        Assert.assertEquals(4, arrayList.size());
    }

    @Test
    public void removeElementFromListByIndex() {
        arrayList.remove(1);
        Assert.assertEquals("Egor", arrayList.get(1));
        Assert.assertEquals(3, arrayList.size());
    }

    @Test
    public void removeElementFromList() {
        arrayList.remove("Lena");
        Assert.assertEquals(false, arrayList.contains("Lena"));
        Assert.assertEquals(3, arrayList.size());
    }

    @Test
    public void clealCollectoin() {
        arrayList.clear();
        Assert.assertEquals(0, arrayList.size());
    }
}
