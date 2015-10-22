package com.darragh.spring.cache.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableCaching
public class Application {

    static final int ITERATIONS = 1000;
    static final int THREAD_COUNT = 4;

    static final int MIN = 1000;
    static final int MAX = 1100;

    private static void test(String testName, DaoInterface fib) throws InterruptedException {

        TestThread threads[] = new TestThread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new TestThread("Thread" + i, fib);
        }

        /** Avoid JVM warmup penalty */
        Thread.sleep(1000);

        for (Thread t : threads)
            t.start();

        for (Thread t : threads)
            t.join();

        int count = 0;
        long[] timings = new long[THREAD_COUNT * ITERATIONS];
        for (int j = 0; j < THREAD_COUNT; j++) {
            for (int i = 0; i < ITERATIONS; i++) {

                timings[count++] = threads[j].timings[i];

            }
        }

        System.out.println("Application : " + testName + " . Avg time : " + MathUtil.arithmeticMean(timings) + " " +
                "nanoseconds");
    }

    @Component
    static class Runner implements CommandLineRunner {

        @Autowired
        @Qualifier("nocache")
        private DaoInterface dao;

        @Autowired
        @Qualifier("caching-enabled")
        private DaoInterface daoCached;

        @Override
        public void run(String... args) throws Exception {

            test("Test with no cache : ", dao);
            test("Test with caching enabled : ", dao);
        }

    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("simple-cache");
    }

    public static void main(String[] args) {

        new SpringApplicationBuilder().showBanner(true).properties("log4j.logger.org.springframework=OFF").web(false).sources(Application.class).run(args);
    }
}

