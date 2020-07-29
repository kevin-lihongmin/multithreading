package com.kevin.multithreading.geektime.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  {@link java.util.concurrent.locks.ReentrantLock} demo， 怎么保证可见性
 *
 * @author kevin
 * @date 2020/7/29 23:07
 * @since 1.0.0
 */
public class ReentrantLockDemo {

    private final Lock lock = new ReentrantLock();

    int value;

    /**
     *  全局变量value 怎么保证可见性？
     *  ReentrantLock内部属性Sync，两个子类分别是NonfairSync和FairSync，默认初始化非公锁。 Sync继承自AQS【AbstractQueuedSynchronizer】内部维护了
     *  属性 volatile state字段。在{@link ReentrantLock#lock()} 时会读取state的值，解锁{@link ReentrantLock#unlock()} 时也会读写state的值，
     *  则根据Happens-Before规则：
     *  1、顺序规则： 对于线程T1， value++ Happens-Before于线程T2的 unlock操作
     *  2、volatile变量规则：获取锁 会先读取state，所以线程T1的 unlock() 操作 Happens-Before于T2的lock() 操作
     *  3、传递性规则： 线程T1的 value++ Hanppens-Before 线程T2的lock操作
     */
    public void add() {
        // 获取锁 【 state = 1 】
        lock.lock();
        try {
            value++;
        } finally {
            // 释放锁【 state = 0 】
            lock.unlock();
        }
    }
}
