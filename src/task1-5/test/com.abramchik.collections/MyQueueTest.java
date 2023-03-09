package com.abramchik.collections;

import com.abramchik.taskTwoCollections.queue.MyQueue;
import com.abramchik.taskTwoCollections.queue.MyQueueImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyQueueTest {

    private MyQueue<String> queue;

    @Before
    public void addElementToQueue(){
        queue = new MyQueueImpl(10);
        queue.enqueue("Masha");
        queue.enqueue("Sasha");
        queue.enqueue("Dima");
    }

    @Test
    public void deleteElementFromQueue(){
        Assert.assertEquals("Dima", queue.peek());
        queue.dequeue();
        Assert.assertEquals("Sasha", queue.peek());
    }

    @Test
    public void showElementFromQueue(){
        Assert.assertEquals("Dima", queue.peek());
        Assert.assertEquals("Dima", queue.peek());
    }

    @Test
    public void sizeShouldBeChanged(){
        Assert.assertEquals(3, queue.size());
        queue.dequeue();
        Assert.assertEquals(2, queue.size());
    }

    @Test
    public void isEmpty(){
        Assert.assertEquals(false, queue.isEmpty());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        Assert.assertEquals(true, queue.isEmpty());
    }

    @Test
    public void isFull(){
        Assert.assertEquals(false, queue.isFull());
        queue.enqueue("q");
        queue.enqueue("a");
        queue.enqueue("t");
        queue.enqueue("x");
        queue.enqueue("c");
        queue.enqueue("f");
        queue.enqueue("d");
        Assert.assertEquals(true, queue.isFull());

    }
}
