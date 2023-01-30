package com.abramchik.collections;

import com.abramchik.taskTwoCollections.treeMap.MyTreeMap;
import com.abramchik.taskTwoCollections.treeMap.MyTreeMapImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class MyTreeMapTest {

    private MyTreeMap<String, Integer> map;
    private static final int DELTA = 0;

    @Before
    public void treeMapInitialization() {
        map = new MyTreeMapImpl<>();
    }

    @Test
    public void addElementsToMap() {
        map.put("uaBY", 111);
        map.put("byUA", 222);
        Assert.assertEquals(222, map.get("byUA"), DELTA);
        Assert.assertEquals(111, map.get("uaBY"), DELTA);
    }

    @Test
    public void getElementShouldReturnValueOrNull() {
        map.put("qwe", 123);
        Assert.assertEquals(123, map.get("qwe"), DELTA);
        Assert.assertNull(map.get("key"));
    }

    @Test
    public void removeElementsShouldReturnTrueOrFalse() {
        map.put("qwe", 123);
        Assert.assertFalse(map.remove("someKey"));
        Assert.assertTrue(map.remove("qwe"));
    }

    @Test
    public void sizeShouldChanged() {
        Assert.assertEquals(0, map.size());
        map.put("qwe", 123);
        map.put("qwer", 313);
        Assert.assertEquals(2, map.size());
        map.put("qwe", 321);
        Assert.assertEquals(2, map.size());
    }

    @Test
    public void clearCollectionShouldDeleteAllElements() {
        map.put("qwe", 123);
        map.put("qwer", 313);
        Assert.assertEquals(2, map.size());
        map.clear();
        Assert.assertEquals(0, map.size());
    }

    @Test
    public void keySetShouldReturnSetWithAllKeys() {
        map.put("qwe", 123);
        map.put("qwer", 313);
        map.put("313", 656);
        map.put("3532trt", 242);
        Set<String> set = map.keySet();
        Assert.assertEquals(4, set.size());
        Assert.assertTrue(set.contains("313"));
    }

    @Test
    public void getFirstKey() {
        map.put("qwe", 123);
        map.put("qwer", 313);
        map.put("313", 656);
        map.put("3532trt", 242);
        Assert.assertEquals("qwer", map.getFirstKey());
    }

    @Test
    public void getLastKey() {
        map.put("rty", 678);
        map.put("cvb", 435);
        map.put("0", 222);
        map.put("te", 222);
        Assert.assertEquals("0", map.getLastKey());
    }
}
