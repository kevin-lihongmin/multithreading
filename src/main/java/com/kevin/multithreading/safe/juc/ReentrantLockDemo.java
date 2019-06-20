package com.kevin.multithreading.safe.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *   {@link java.util.concurrent.locks.ReentrantLock} 的demo
 * @author lihongmin
 * @date 2018/12/16
 * 16:31
 */
public class ReentrantLockDemo implements Runnable {

    private static Lock lock = new ReentrantLock();

    private static volatile int count = 0;

    @Override
    public void run() {
        lock.lock();
        try {
            // doSomething
            count++;
        } catch (Exception e) {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new ReentrantLockDemo());
        }
        System.out.println(ReentrantLockDemo.count);
        executorService.shutdown();

    }
}
