package com.darragh.spring.cache.test;

import java.util.Random;

class TestThread extends Thread {

    Random random = new Random();

    DaoInterface instance;

    String threadName;

    long[] timings = new long[Application.ITERATIONS];

    public TestThread(String threadName, DaoInterface instance) {
        super(threadName);
        this.threadName = threadName;
        this.instance = instance;
    }

    public void run() {

        for (int i = 0; i < Application.ITERATIONS; i++) {

            long s = System.nanoTime();
            int randomNum = random.nextInt((Application.MAX - Application.MIN) + 1) + Application.MIN;

            /** Calculate DaoInterface for N */
            instance.getData(randomNum);

            timings[i] = (System.nanoTime() - s);
        }

    }

}
