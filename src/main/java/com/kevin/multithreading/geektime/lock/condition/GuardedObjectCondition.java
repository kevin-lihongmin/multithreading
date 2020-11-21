package com.kevin.multithreading.geektime.lock.condition;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 *  Guarded Suspension [受保护的暂停] 模式; 等待唤醒机制,
 *  在此基础上实现 模拟{@link Condition}形成链路
 *
 * @author kevin
 * @date 2020/8/5 23:13
 * @since 1.0.0
 */
public final class GuardedObjectCondition<T> {
    /** 受保护的对象 */
    T object;
    /** 创建公平锁 */
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition done = lock.newCondition();
    private final Condition done2 = lock.newCondition();
    final int timeout = 500000000;

    /**
     *  获取受保护的对象
     * @param predicate 条件
     * @return 受保护对象
     */
    public T get2(Predicate<T> predicate) {
        lock.lock();
        try {
            // MESA管程推荐的写法
            while (!predicate.test(object)) {
                done2.await(/*timeout, TimeUnit.MILLISECONDS*/);
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
        GuardedObjectCondition guardedObject = new GuardedObjectCondition();
        // 第二个线程进入CLH
        new Thread(() -> {
            sleep(100);
            guardedObject.get2(o -> false);
        }, "get2-1").start();
        /*// 第三个线程进入CLH
        new Thread(() -> {
            sleep(100);
            guardedObject.get2(o -> false);
        }, "get2-2").start();*/

        /*// 第四个线程进入Condition等待队列
        new Thread(() -> {
            sleep(1000);
            guardedObject.get(o -> false);
        }, "get-2").start();*/
        // 第五个线程进入Condition等待队列
        new Thread(() -> {
            sleep(1000);
            guardedObject.get(o -> false);
        }, "get-3").start();

        Thread.sleep(700);
        // 主线程先进入等待队列
        guardedObject.get(o -> false);
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
