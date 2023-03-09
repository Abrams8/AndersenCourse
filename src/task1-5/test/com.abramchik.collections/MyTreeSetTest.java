package com.abramchik.collections;

import com.abramchik.taskTwoCollections.treeSet.MyTreeSet;
import com.abramchik.taskTwoCollections.treeSet.MyTreeSetImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyTreeSetTest {

    private MyTreeSet<String> set;

    @Before
    public void treeSetInitialization(){
        set = new MyTreeSetImpl<>();
        set.add("Dima");
        set.add("Egor");
        set.add("1234");
    }

    @Test(expected = NullPointerException.class)
    public void addElementToSet(){
        set.add("Robin");
        Assert.assertEquals(4, set.size());
        Assert.assertTrue(set.add("NewPerson"));
        Assert.assertFalse(set.add("NewPerson"));
        set.add(null);
    }

    @Test
    public void sizeShouldChanged(){
        Assert.assertEquals(3, set.size());
        set.add("Man");
        set.add("Child");
        Assert.assertEquals(5, set.size());
        set.remove("Dima");
        Assert.assertEquals(4, set.size());
        set.clear();
        Assert.assertEquals(0, set.size());
    }

    @Test
    public void setShouldDeleteAllElements(){
        Assert.assertEquals(3, set.size());
        set.clear();
        Assert.assertEquals(0, set.size());
    }

    @Test(expected = NullPointerException.class)
    public void containsShouldReturnTrueOrFalse(){
        Assert.assertTrue(set.contains("Dima"));
        Assert.assertFalse(set.contains("Dimon"));
        set.add("Dimon");
        Assert.assertTrue(set.contains("Dimon"));
        Assert.assertTrue(set.contains(null));
    }

    @Test(expected = NullPointerException.class)
    public void removeShouldDeleteElement(){
        Assert.assertEquals(3, set.size());
        Assert.assertTrue(set.remove("Dima"));
        Assert.assertFalse(set.remove("Dima"));
        Assert.assertEquals(2, set.size());
        set.remove(null);
    }

    @Test
    public void firstSholdReturnFirstElement(){
        set.add("3");
        Assert.assertEquals("3", set.first());
        set.add("1");
        Assert.assertEquals("1", set.first());
    }

    @Test
    public void lastShouldReturnLastElement(){
        Assert.assertEquals("Egor", set.last());
        set.add("qqqq");
        Assert.assertEquals("qqqq", set.last());
        set.add("aaaa");
        Assert.assertEquals("qqqq", set.last());
    }
}
