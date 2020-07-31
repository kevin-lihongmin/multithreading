package com.kevin.multithreading.geektime.lock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

/**
 *  信号量模型demo，
 *
 *
 * @author lihongmin
 * @date 2020/7/30 8:48
 * @since 1.0.0
 * @see Semaphore#acquire()
 * @see Semaphore#release()
 */
public class SemaphoreDemo {

    /** 计数器 */
    int count;

    /** 等待队列 */
    Queue queue = new LinkedBlockingDeque();

    /**
     *  构造器中初始化计数器
     * @param count 计算器初始值
     */
    public SemaphoreDemo(int count) {
        this.count = count;
    }

    void down() {
        this.count--;
        if (this.count < 0) {
            // 将当前线程插入等待队列
            // 阻塞当前线程
        }
    }

    void up() {
        this.count++;
        if (this.count <= 0) {
            // 移除队列中的一个线程 T
            // 唤醒该线程 T
        }
    }


}
