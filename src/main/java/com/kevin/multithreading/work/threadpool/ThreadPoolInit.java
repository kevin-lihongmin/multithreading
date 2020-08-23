package com.kevin.multithreading.work.threadpool;

import com.kevin.multithreading.work.iopool.TaskQueue;
import com.kevin.multithreading.work.iopool.TaskThreadFactory;
import com.kevin.multithreading.work.iopool.ThreadPoolExecutorImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  线程池定义和初始化和订单打印线程状态数据
 *
 * @author kevin
 * @date 2020/8/23 14:23
 * @since 1.0.0
 */
@Slf4j
public class ThreadPoolInit {

    /**
     *  线程池工具map
     */
    public static final Map<String, ThreadPoolExecutor> THREAD_POOL_EXECUTOR_MAP = new ConcurrentHashMap<>(16);

    static {
        // 初始化线程池
        ServiceLoader<ThreadPool> load = ServiceLoader.load(ThreadPool.class);
        load.forEach(threadPool -> threadPool.appendThreadPool().forEach(SimpleThreadPool::putThreadPool));

        // 初始化线程池状态信息订单打印
        printScheduledThreadStats();
    }

    /**
     * 线程池类型， 只是作为标识当前任务是CPU型还是IO型为主
     */
    public enum PoolModel {
        IO,
        CPU,
        FAST_IO
    }

    /**
     *  线程工厂
     */
    public static class DefaultThreadFactory implements ThreadFactory {

        /**
         *  定义线程组
         */
        static ThreadGroup threadGroup;

        /**
         *  定义每个线程池中每个线程的名称后缀数字
         */
        static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);

        /**
         *  定义每个线程词的名称前缀
         */
        static String namePrefix;

        public DefaultThreadFactory(String name) {
            final SecurityManager securityManager = System.getSecurityManager();
            threadGroup = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = name + "-thread-";
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(threadGroup, runnable, namePrefix + THREAD_NUMBER.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if(thread.getPriority() != Thread.NORM_PRIORITY){
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }

    /**
     * 启动打印线程
     */
    private static void printScheduledThreadStats() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            THREAD_POOL_EXECUTOR_MAP.entrySet().forEach(entry -> {
                ThreadPoolExecutor threadPool = entry.getValue();
                String name = entry.getKey();
                printInfo(threadPool, name);
            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 打印线程池信息
     * @param threadPool 线程池
     * @param name 线程池名称
     */
    public static void printInfo(ThreadPoolExecutor threadPool, String name) {
        log.info("{} Pool Size: {}", name, threadPool.getPoolSize());
        log.info("{} Active Threads: {}", name, threadPool.getActiveCount());
        log.info("{} Number of Tasks Completed: {}", name, threadPool.getCompletedTaskCount());
        log.info("{} Number of Tasks in Queue: {}", name, threadPool.getQueue().size());
    }

    /**
     * 往线程池容器中放入线程池池
     * @param threadPoolEntity 线程池定义对象
     */
    public static final void putThreadPool(ThreadPoolEntity threadPoolEntity) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor(threadPoolEntity);
        THREAD_POOL_EXECUTOR_MAP.put(threadPoolEntity.taskName, threadPoolExecutor);
    }

    /**
     *  根据枚举获取线程池
     * @param entity 线程池枚举
     */
    private static ThreadPoolExecutor getThreadPoolExecutor(ThreadPoolEntity entity) {
        ThreadPoolExecutor executor = null;
        if (entity.poolModel == PoolModel.FAST_IO && entity.blockingQueue instanceof TaskQueue) {
            TaskQueue taskQueue = (TaskQueue)entity.blockingQueue;
            ThreadPoolExecutorImpl threadPoolExecutor = new ThreadPoolExecutorImpl(entity.corePoolNum,
                    entity.maxPoolNum,
                    entity.deleteThreadNum,
                    entity.deleteTreadUnit,
                    taskQueue,
                    new TaskThreadFactory(entity.taskName),
                    entity.rejectedExecutionHandler);
            // 设置父类，用于判断线程的对列表是否真的满了
            taskQueue.setParent(threadPoolExecutor);
        } else {
            executor = new ThreadPoolExecutor(entity.corePoolNum,
                    entity.maxPoolNum,
                    entity.deleteThreadNum,
                    entity.deleteTreadUnit,
                    entity.blockingQueue,
                    new DefaultThreadFactory(entity.taskName),
                    entity.rejectedExecutionHandler);
        }
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