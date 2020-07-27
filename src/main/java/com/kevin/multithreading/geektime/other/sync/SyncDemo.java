package com.kevin.multithreading.geektime.other.sync;


/**
 *  synchronized
 *
 * @author kevin
 * @date 2020/7/26 22:48
 * @since 1.0.0
 */
public class SyncDemo {

    static long value = 0L;

    synchronized long get() {
        return value;
    }

    synchronized static long addOne() {
        return value++;
    }
}
