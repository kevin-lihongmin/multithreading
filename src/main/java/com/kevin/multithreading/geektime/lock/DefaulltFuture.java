package com.kevin.multithreading.geektime.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  模拟 Dubbo的Rpc方法调用，异步转同步
 *
 * @author kevin
 * @date 2020/7/30 0:44
 * @since 1.0.0
 */
public class DefaulltFuture {

    private final Lock lock = new ReentrantLock(true);

    private final Condition condition = lock.newCondition();

    private volatile Object response;

    Object get(long timeout) {
        long start = System.nanoTime();
        lock.lock();
        try {
            while (response != null) {
                condition.await(timeout, TimeUnit.MILLISECONDS);

                long current = System.nanoTime();
                if (response != null || current - start > timeout) {
                    break;
                }
            }
            if (response == null) {
                throw new RuntimeException("等待超时：" + timeout + " ms");
            }
            return response;
        } catch (InterruptedException e) {
            return "";
        } finally {
            lock.unlock();
        }
    }
    /**
     *  当同步远程方法调用返回时
     * @param response 远程同步返回的结果
     */
    private void doReceived(Object response) {
        lock.lock();
        try {
            this.response = response;
            if (response != null) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
