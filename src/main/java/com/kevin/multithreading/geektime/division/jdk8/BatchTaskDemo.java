package com.kevin.multithreading.geektime.division.jdk8;


import com.kevin.multithreading.util.OldSimpleThreadPool;

import java.util.concurrent.*;

/**
 *  批量执行任务
 *
 * 保存价格数据库了：40
 * 保存价格数据库了：20
 * 保存价格数据库了：30
 *
 * @author kevin
 * @date 2020/8/4 22:54
 * @since 1.0.0
 */
public class BatchTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new BatchTaskDemo().demo();
    }

    /**
     *  ThreadPoolExecutor+Future 方式实现， 批量查询电商系统的价格，并且保存到数据库中
     *  并且能够先执行完先保存，节约执行时间
     */
    public void demo() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100,
                0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new OldSimpleThreadPool.DefaultThreadFactory("test"));

        final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        threadPoolExecutor.execute(() -> {
            try {
                queue.put(threadPoolExecutor.submit(() -> {
                    sleep(300);
                    return 20;
                }).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.execute(() -> {
            try {
                queue.put(threadPoolExecutor.submit(() -> {
                    sleep(500);
                    return 30;
                }).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.execute(() ->{
            try {
                queue.put(threadPoolExecutor.submit(() -> {
                    sleep(200);
                    return 40;
                }).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 3; i++) {
            Integer take = queue.take();
            System.out.println("保存价格数据库了：" + take);
        }
    }

    public static void sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
