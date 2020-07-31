package com.kevin.multithreading.geektime.division;


import com.kevin.multithreading.util.SimpleThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.kevin.multithreading.util.SimpleThreadPool.ThreadPoolEnum.CREATE_ORDER;

/**
 *  {@link java.util.concurrent.CountDownLatch} 协调多线程与主线程的步调
 * @author kevin
 * @date 2020/8/1 0:03
 * @since 1.0.0
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        List<Runnable> task = new ArrayList<>();
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 执行耗时任务， 如 数据库查询， io调用等
        task.add(() -> {
            queue.add(111);
            countDownLatch.countDown();
        });
        task.add(() -> {
            queue.add(222);
            countDownLatch.countDown();
        });
        task.add(() -> {
            queue.add(333);
            countDownLatch.countDown();
        });
        SimpleThreadPool.executeRunnable(CREATE_ORDER, task.toArray(new Runnable[0]));
        countDownLatch.await();

        AtomicInteger total = new AtomicInteger();
        queue.iterator().forEachRemaining(num -> {
            total.addAndGet(num);
        });
        System.out.println(total.get());
    }
}
