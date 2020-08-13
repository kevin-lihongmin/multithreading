package com.kevin.multithreading.geektime.division.jdk8;


import com.kevin.multithreading.util.SimpleThreadPool;

import java.util.concurrent.*;

import static com.kevin.multithreading.geektime.division.jdk8.BatchTaskDemo.sleep;

/**
 *  批量执行任务
 *
 * 将获取到的价格保存到数据库了：50
 * 将获取到的价格保存到数据库了：20
 * 将获取到的价格保存到数据库了：10
 *
 * @author kevin
 * @date 2020/8/4 23:19
 * @since 1.0.0
 */

public class CompletionServiceDemo {
    /**
     *  获取线程池
     */
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new SimpleThreadPool.DefaultThreadFactory("test"));
    /**
     *  使用默认的无界队列 {@link java.util.concurrent.LinkedBlockingQueue}
     */
    public static final CompletionService<Integer> COMPLETION_SERVICE = new ExecutorCompletionService(threadPoolExecutor);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        COMPLETION_SERVICE.submit(() -> {
            sleep(300);
            return 20;
        });
        COMPLETION_SERVICE.submit(() -> {
            sleep(400);
            return 10;
        });
        COMPLETION_SERVICE.submit(() -> {
            sleep(100);
            return 50;
        });

        for (int i = 0; i < 3; i++) {
            Integer integer = COMPLETION_SERVICE.take().get();
            threadPoolExecutor.execute(() -> System.out.println("将获取到的价格保存到数据库了：" + integer));
        }
    }
}
