package com.abramchik.taskFour;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads {

    private static final int THREAD_FIXED_VALUE = 8;

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);

        ExecutorService threadTwo = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_FIXED_VALUE);

        threadTwo.submit(myThread);
        for (int i = 1; i <= THREAD_FIXED_VALUE; i++) {
            executorService.submit(myThread);
        }
        thread.start();

        threadTwo.shutdown();
        executorService.shutdown();
        thread.join();

        Map<String, Integer> result = myThread.getMap();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println("Thread name: " + entry.getKey() + " , value: " + entry.getValue());
        }

        myThread.showInfoAboutThreads();

    }
}
