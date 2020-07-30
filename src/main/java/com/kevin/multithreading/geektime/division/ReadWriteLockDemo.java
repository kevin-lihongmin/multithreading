package com.kevin.multithreading.geektime.division;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  使用{}
 * @author kevin
 * @date 2020/7/30 23:33
 * @since 1.0.0
 */
public class ReadWriteLockDemo<K, V> {

    private final Map<K,V> cache = new HashMap<>();
    /** 公平的读写锁 */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    /** 读锁 */
    Lock readLock = lock.readLock();
    /** 写锁 */
    Lock writeLock = lock.writeLock();

    V get(K key) {
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    V put(K key, V value) {
        writeLock.lock();
        try {
            return cache.putIfAbsent(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo<Object, Object> cache = new ReadWriteLockDemo<>();
        cache.put("kevin", "kevin");
        System.out.println(cache.get("kevin"));
    }
}
