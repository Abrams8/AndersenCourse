package com.abramchik.taskFour;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyThread implements Runnable {

    Counter counter = new Counter();

    @Getter
    Map<String, Integer> map = new ConcurrentHashMap<>();

    List<List<String>> threadInfoList = new ArrayList<>();

    @Override
    public void run() {
        List<String> list = new ArrayList<>();
        list.add(Thread.currentThread().getName());
        list.add("Thread started at: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ms")));
        counter.count();
        map.put(Thread.currentThread().getName(), counter.getI());
        list.add("Thread finished at: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ms")));
        threadInfoList.add(list);
    }

    public void showInfoAboutThreads() {
        for (int i = 0; i < threadInfoList.size(); i++) {
            List<String> threadInfo = threadInfoList.get(i);
            for (int j = 0; j < threadInfo.size(); j++) {
                System.out.println(threadInfo.get(j));
            }
            System.out.println("\n");
        }
    }

    @ToString()
    class Counter {
        @ToString.Exclude
        Lock lock = new ReentrantLock();

        @Getter
        @Setter
        private int i = 0;

        public void count() {
            lock.lock();
            i++;
            lock.unlock();
        }
    }
}