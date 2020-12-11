package com.kevin.multithreading.designpattern;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(5), new ThreadPoolExecutor.AbortPolicy());

        executor.prestartAllCoreThreads();

        executor.submit(() -> {
            System.out.println("线程Id： " + Thread.currentThread() + "线程内存地址：" + System.identityHashCode(Thread.currentThread()));
        });

        executor.submit(() -> {
            System.out.println("线程Id： " + Thread.currentThread() + "线程内存地址：" + System.identityHashCode(Thread.currentThread()));
        });

        Thread.sleep(3000);

        executor.submit(() -> {
            System.out.println("线程Id： " + Thread.currentThread() + "线程内存地址：" + System.identityHashCode(Thread.currentThread()));
        });
    }

}
