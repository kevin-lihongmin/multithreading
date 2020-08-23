package com.kevin.multithreading.geektime.synchronization;


import com.kevin.multithreading.util.OldSimpleThreadPool;
import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  使用 累计器的例子来模拟 CAS的实现
 *
 * @author kevin
 * @date 2020/8/3 21:58
 * @since 1.0.0
 */
@Data
public class SimulatedCas {

    /** 叠加器 */
    private volatile int count;

    public void addOne() {
        int newValue;
        do {
            newValue = count + 1;
        } while (count != cas(count, newValue));
    }

    private synchronized int cas(int expect, int newValue) {
        // 读取目前的叠加器的值
        int currentValue = count;
        // 比较期望值和目前的值，如果得到期望值
        if (currentValue == expect) {
            // 更新count值
            count = newValue;
        }
        return currentValue;
    }

    public static void main(String[] args) throws InterruptedException {
        SimulatedCas simulatedCas = new SimulatedCas();
        simulatedCas.setCount(0);
        ThreadPoolExecutor kevin = new ThreadPoolExecutor(300, 500, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new OldSimpleThreadPool.DefaultThreadFactory("kevin"),
                new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            kevin.execute(() -> {
                simulatedCas.addOne();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        System.out.println("当前值为：" + simulatedCas.getCount());
    }
}
