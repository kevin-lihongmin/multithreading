package com.kevin.multithreading.geektime.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  一个Condition 队列的管程模型
 *
 * @author kevin
 * @date 2020/11/21 10:33
 * @since 1.0.0
 */
public class OneConditionDemo {

    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();
  
        new Thread(() -> {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "拿到锁了");
            System.out.println(Thread.currentThread().getName() + "等待信号");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "拿到信号");

            reentrantLock.unlock();
        }, "线程1").start();
  
        new Thread(() -> {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "拿到锁了");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "发出信号");
            condition.signalAll();

            reentrantLock.unlock();
        }, "线程2").start();
    }  
}  