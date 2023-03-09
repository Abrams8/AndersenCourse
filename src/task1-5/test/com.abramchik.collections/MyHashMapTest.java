package com.abramchik.collections;

import com.abramchik.taskTwoCollections.hashMap.MyHashMap;
import com.abramchik.taskTwoCollections.hashMap.MyHashMapImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyHashMapTest {

    private MyHashMap<String, Integer> hashMap;
    private static final int DELTA = 0;

    @Before
    public void hashMapInitialization(){
        hashMap = new MyHashMapImpl<>();
    }

    @Test
    public void addElementsToMap(){
        hashMap.put("qwe", 11);
        hashMap.put("asd", 22);
        hashMap.put("zxc", 33);
        Assert.assertEquals(11, hashMap.get("qwe"), DELTA);
        Assert.assertEquals(22, hashMap.get("asd"), DELTA);
        Assert.assertEquals(33, hashMap.get("zxc"), DELTA);
    }

    @Test
    public void sizeShouldBeChanged(){
        Assert.assertEquals(0, hashMap.size());
        hashMap.put("def", 221);
        hashMap.put("def2", 221);
        Assert.assertEquals(2, hashMap.size());
    }

    @Test
    public void clearShouldBeDeleteAllElements(){
        hashMap.put("qwe", 432);
        hashMap.put("trgd", 535);
        hashMap.put("jhfd", 75);
        Assert.assertEquals(3, hashMap.size());
        hashMap.clear();
        Assert.assertEquals(0, hashMap.size());
    }

    @Test
    public void isEmpty(){
        Assert.assertTrue(hashMap.isEmpty());
        hashMap.put("iend", 4224);
        Assert.assertFalse(hashMap.isEmpty());
    }

    @Test
    public void deleteElementByKey(){
        hashMap.put("gigo", 12345);
        hashMap.put("tutu", 432);
        Assert.assertEquals(2, hashMap.size());
        hashMap.remove("gigo");
        Assert.assertEquals(1, hashMap.size());
        Assert.assertEquals(null, hashMap.get("gigo"));
    }

    @Test
    public void deleteElementByKeyAndValue(){
        hashMap.put("error", 404);
        hashMap.put("hello", 500);
        Assert.assertEquals(2, hashMap.size());
        hashMap.remove("error", 404);
        Assert.assertEquals(1, hashMap.size());
        Assert.assertEquals(null, hashMap.get("error"));
        Assert.assertFalse(hashMap.remove("hello", 501));
    }

    @Test
    public void conteinsKeyShouldReturnTrueOrFalse(){
        Assert.assertFalse(hashMap.containsKey("3232"));
        hashMap.put("kkl", 377);
        Assert.assertTrue(hashMap.containsKey("kkl"));
    }

    @Test
    public void conteinsValueShouldReturnTrueOrFalse(){
        Assert.assertFalse(hashMap.containsValue(123));
        hashMap.put("vag", 377);
        Assert.assertTrue(hashMap.containsValue(377));
        hashMap.put("vag", 477);
        Assert.assertFalse(hashMap.containsValue(377));
    }
}
