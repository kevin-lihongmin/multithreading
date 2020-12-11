package com.kevin.multithreading.designpattern;


import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 *  Guarded Suspension [受保护的暂停] 模式; 等待唤醒机制
 *
 *  Thread-1 ---> wait
 *                     obj = null;
 *                                 <---- Thread-2
 *                     obj = XXX;
 *                     signalAll;
 *  Thread-1 <---
 *
 * @author kevin
 * @date 2020/8/5 23:13
 * @since 1.0.0
 */
public final class GuardedObject<T> {
    /** 受保护的对象 */
    T object;
    /** 创建公平锁 */
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition done = lock.newCondition();
    final int timeout = 5;

    /**
     *  获取受保护的对象
     * @param predicate 条件
     * @return 受保护对象
     */
    public T get(Predicate<T> predicate) {
        lock.lock();
        try {
            // MESA管程推荐的写法
            while (!predicate.test(object)) {
                done.await(timeout, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        // 返回非空的受保护对象
        return object;
    }
    /**
     *  事件通知方法
     * @param object 包含对象【改变状态值】
     */
    public void onChange(T object) {
        lock.lock();
        try {
            this.object = object;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GuardedObject guardedObject = new GuardedObject();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            // 1、执行AQS await的 addConditionWaiter(); fullyRelease(node);
            guardedObject.get(Objects::nonNull); // 出自旋的条件是被保护的Object不为null

            countDownLatch.countDown();
        }).start();

        // 线程创建一个线程，sleep一定时间，后唤醒自旋的主线程
        new Thread(() -> {
            try { Thread.sleep(5000L);  } catch (InterruptedException e) { e.printStackTrace(); }
            // 2、执行AQS的signAll方法
            guardedObject.onChange(new Object());
            // 3、执行await中的 自旋线程

            countDownLatch.countDown();
        }).start();

        countDownLatch.await();
    }
}
