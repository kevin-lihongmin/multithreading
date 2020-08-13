package com.kevin.multithreading.designpattern;


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
}
