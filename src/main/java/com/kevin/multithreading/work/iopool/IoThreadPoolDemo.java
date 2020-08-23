package com.kevin.multithreading.work.iopool;

import com.kevin.multithreading.work.threadpool.ThreadPoolEntity;
import com.kevin.multithreading.work.threadpool.ThreadPoolInit;
import com.kevin.multithreading.work.threadpool.client.ThreadPoolImpl;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *  io 型 线程池 模仿Tomcat对原生线程池的改造
 *
 * @author kevin
 * @date 2020/8/23 15:39
 * @since 1.0.0
 */
public class IoThreadPoolDemo {

    public static void main(String[] args) {

        TaskQueue queue = new TaskQueue();

        ThreadPoolEntity entity = new ThreadPoolImpl().appendThreadPool().get(0);
        ThreadPoolExecutorImpl threadPoolExecutor = new ThreadPoolExecutorImpl(entity.corePoolNum,
                entity.maxPoolNum,
                entity.deleteThreadNum,
                entity.deleteTreadUnit,
                queue,
                new TaskThreadFactory(entity.taskName),
                entity.rejectedExecutionHandler);

        queue.setParent(threadPoolExecutor);


        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行了任务，当前的线程池参数为：");
                ThreadPoolInit.printInfo(threadPoolExecutor, entity.taskName);
            });
        }
    }

    /**
     *  根据枚举获取线程池
     * @param entity 线程池枚举
     */
    private static ThreadPoolExecutor getThreadPoolExecutor(ThreadPoolEntity entity) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(entity.corePoolNum,
                entity.maxPoolNum,
                entity.deleteThreadNum,
                entity.deleteTreadUnit,
                entity.blockingQueue,
                new ThreadPoolInit.DefaultThreadFactory(entity.taskName),
                entity.rejectedExecutionHandler);
        // 是否允许核心线程超时
        if (entity.allowsCoreThreadTimeOut) {
            executor.allowsCoreThreadTimeOut();
        }
        // 是否预热核心线程
        if (entity.preStartAllCoreThreads) {
            executor.prestartAllCoreThreads();
        }
        return executor;
    }
}
