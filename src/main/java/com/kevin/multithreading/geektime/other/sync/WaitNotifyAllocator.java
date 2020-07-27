package com.kevin.multithreading.geektime.other.sync;


import java.util.ArrayList;
import java.util.List;

/**
 *  使用【破坏占用且等待条件】 创建细粒度锁，防止死锁的转账
 *
 *  使用经典的wait notifyAll方式，使用管程模型作为理论，没有特殊理由不要尝试换写法
 *  {@link Object#notifyAll()} 唤醒时表示曾经满足过，但是两个可能不是一个时机
 *
 * @author kevin
 * @date 2020/7/27 0:17
 * @since 1.0.0
 * @see Account2#transfer6(Account2, Double)
 */
public class WaitNotifyAllocator {

    private List<Object> als = new ArrayList<>();

    /**
     *  一次性申请所有的锁
     *
     * @return 是否申请成功
     */
    synchronized Boolean apply(Object from, Object to) {
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (Exception e) {
                return false;
            }
        }
        als.add(from);
        als.add(to);
        return true;
    }

    /**
     *  是否锁
     * @param from 释放第一把锁
     * @param to 释放第二把锁
     */
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }

}
