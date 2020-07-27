package com.kevin.multithreading.geektime.other.sync;


import java.util.ArrayList;
import java.util.List;

/**
 *  使用【破坏占用且等待条件】 创建细粒度锁，防止死锁的转账
 *
 * @author kevin
 * @date 2020/7/27 0:17
 * @since 1.0.0
 */
public class Allocator {

    private List<Object> als = new ArrayList<>();

    /**
     *  一次性申请所有的锁
     *  PS： 但是如果是三层 synchronized 则该方法不满足
     * @return 是否申请成功
     */
    synchronized Boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) {
            return false;
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
    }
}
