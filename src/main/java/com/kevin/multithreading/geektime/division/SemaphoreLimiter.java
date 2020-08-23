package com.kevin.multithreading.geektime.division;


import java.util.List;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.kevin.multithreading.util.OldSimpleThreadPool.DefaultThreadFactory;

/**
 *  使用{@link Semaphore} 实现一个限流器
 *   即多线程的并发的情况下，只允许指定长度的个数执行任务，其余的排队
 *
 * .......................省略.........................
 * 当前线程{test-thread-93}获取到的任务为：kevin test 2
 * 当前线程{test-thread-94}获取到的任务为：kevin test 3
 * 当前线程{test-thread-95}获取到的任务为：kevin test 4
 * 当前线程{test-thread-96}获取到的任务为：kevin test 5
 * 当前线程{test-thread-97}获取到的任务为：kevin test 6
 * 当前线程{test-thread-98}获取到的任务为：kevin test 7
 * 当前线程{test-thread-99}获取到的任务为：kevin test 8
 * 当前线程{test-thread-100}获取到的任务为：kevin test 9
 *
 * @author kevin
 * @date 2020/7/30 22:32
 * @since 1.0.0
 */
public class SemaphoreLimiter<T, R> {

    private final List<T> pool;

    private final Semaphore semaphore;

    /**
     *  限流器构造
     * @param t 限流类型
     */
    public SemaphoreLimiter(T[] t) {
        int size = t.length;
        this.pool = new Vector<T>(){};
        for (int i = 0; i < size; i++) {
            pool.add(t[i]);
        }
        this.semaphore = new Semaphore(size);
    }

    /**
     *  执行任务
     * @param function 功能
     * @return
     * @throws InterruptedException
     */
    R execute(Function<T, R> function) throws InterruptedException {
        T t = null;
        semaphore.acquire();
        try {
            t = pool.remove(0);
            return function.apply(t);
        } finally {
            pool.add(t);
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String[] str = new String[10];
        for (int i = 0; i < str.length; i++) {
            str[i] = "kevin test " + i;
        }
        SemaphoreLimiter<String, String> pool = new SemaphoreLimiter<String, String>(str);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100,
                0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new DefaultThreadFactory("test"));
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                    try {
                        pool.execute(t -> {
                            System.out.println("当前线程{" + Thread.currentThread().getName() + "}获取到的任务为：" + t);
                            return t;
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            );
        }
    }

}
