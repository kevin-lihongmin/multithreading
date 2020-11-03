package com.kevin.multithreading.geektime.lock;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  使用{@link ReentrantLock} 和 {@link Condition} 模拟管程模型；在synchronized中只能有一个条件队列，
 *  而当前可以创建多个Condition即可
 *
 *  <p>
 *      对于入队操作，如果队列已满，就需要等待直到队列不满，所以这里用了notFull.await();。
 *      对于出队操作，如果队列为空，就需要等待直到队列不空，所以就用了notEmpty.await();。
 *      如果入队成功，那么队列就不空了，就需要通知条件变量：队列不空notEmpty对应的等待队列。
 *      如果出队成功，那就队列就不满了，就需要通知条件变量：队列不满notFull对应的等待队列。
 *
 *  <p>
 *      我们知道 {@link Object#wait()}、{@link Object#notify()}、{@link Object#notifyAll()} 只能在 synchronized中使用
 *      同样 {@link Condition#await()}、{@link Condition#signal()}、{@link Condition#signalAll()} 只能在Lock&Condition中使用
 *      并且这三个方法一一对应， 需要注意上面三个是{@link Object}的方法，所以在下面的{@link Condition}中同样存在，千万别调用错了
 *
 * @author kevin
 * @date 2020/7/29 23:51
 * @since 1.0.0
 *
 * @see LinkedBlockingQueue #takeLock
 * @see LinkedBlockingQueue #putLock
 *
 * @see LinkedBlockingQueue #notFull
 * @see LinkedBlockingQueue #notEmpty
 */
public class BlockedQueue {

    /** 创建一个公平锁 */
    final Lock lock = new ReentrantLock(true);

    /** 条件变量：队列不满 */
    final Condition notFull = lock.newCondition();

    /** 条件变量：队列不空 */
    final Condition notEmpty = lock.newCondition();

    /**
     *  入队操作
     */
    /*void enqueue() throws InterruptedException {
        lock.lock();
        try {
            while (队列已满) {
                // 等待队列不满
                notFull.await();
            }
            // 省略入队操作

            // 入队后通知可出队
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }*/

    /*void dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (队列已空) {
                // 等待队列不空
                notEmpty.await();
            }
            // 省略出队列操作，省略一万行
            // 出队列后，通知可入队
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }*/
}
