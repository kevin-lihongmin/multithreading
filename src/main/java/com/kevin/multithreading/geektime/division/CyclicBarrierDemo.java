package com.kevin.multithreading.geektime.division;


import com.kevin.multithreading.util.OldSimpleThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.kevin.multithreading.util.OldSimpleThreadPool.ThreadPoolEnum.CREATE_ORDER;

/**
 *  模拟三个耗时任务 循环执行， 最后的加任务就是 Barrier【屏障】
 *
 *  222
 * 333
 * 111
 * 666
 * -------------------
 * 333
 * 222
 * 111
 * 666
 * -------------------
 * 111
 * 222
 * 111
 * 333
 * 222
 * 666
 * -------------------
 * 333
 * 666
 * -------------------
 * 111
 * 222
 * 111
 * 222
 * 333
 * 666
 * -------------------
 * 333
 * 666
 * -------------------
 * 111
 * 333
 * 222
 * 222
 * 666
 * -------------------
 * 111
 * 111
 * 333
 * 222
 * 666
 * -------------------
 * 333
 * 666
 * -------------------
 *
 * @author kevin
 * @date 2020/8/1 0:26
 * @since 1.0.0
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Integer> queue2 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Integer> queue3 = new LinkedBlockingQueue<>();

        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            AtomicInteger total = new AtomicInteger();
            try {
                total.addAndGet(queue.take());
                total.addAndGet(queue2.take());
                total.addAndGet(queue3.take());

                System.out.println(total.get());
                System.out.println("-------------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 1; i < 10; i++) {
            List<Runnable> task = new ArrayList<>();
            // 执行耗时任务， 如 数据库查询， io调用等
            task.add(() -> {
                System.out.println(111);
                queue.add(111);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                } catch (BrokenBarrierException e) {
                }
            });
            task.add(() -> {
                System.out.println(222);
                queue2.add(222);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                } catch (BrokenBarrierException e) {
                }
            });
            task.add(() -> {
                System.out.println(333);
                queue3.add(333);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                } catch (BrokenBarrierException e) {
                }
            });
            OldSimpleThreadPool.executeRunnable(CREATE_ORDER, task.toArray(new Runnable[0]));

        }
    }
}
