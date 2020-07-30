package com.kevin.multithreading.util;


import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  项目上可以直接使用的线程池工具类
 *
 * @author lihongmin
 * @date 2019/2/23 17:51
 */
public class SimpleThreadPool {

    private static final Logger logger = LoggerFactory.getLogger(SimpleThreadPool.class);

    /**
     *  线程池工具map
     */
    public static final Map<ThreadPoolEnum, ThreadPoolExecutor> THREAD_POOL_EXECUTOR_MAP = new HashMap<ThreadPoolEnum, ThreadPoolExecutor>(16);

    /**
     *  线程池集合枚举
     */
    public enum ThreadPoolEnum {
        name1("skuInfo", "sku相关线程池", 5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque(10), new ThreadPoolExecutor.CallerRunsPolicy()),
        name2("spuInfo", "spu相关线程池", 5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque(10), new ThreadPoolExecutor.CallerRunsPolicy());

        ThreadPoolEnum(String name, String detail, int corePoolNum, int maxPoolNum, int deleteThreadNum, TimeUnit deleteTreadUnit, LinkedBlockingDeque blockingDeque, RejectedExecutionHandler rejectedExecutionHandler) {
            this.name = name;
            this.detail = detail;
            this.corePoolNum = corePoolNum;
            this.maxPoolNum = maxPoolNum;
            this.deleteThreadNum = deleteThreadNum;
            this.deleteTreadUnit = deleteTreadUnit;
            this.blockingDeque = blockingDeque;
            this.rejectedExecutionHandler = rejectedExecutionHandler;
        }

        /**
         *  先池名称
         */
        private String name;

        /**
         *  线程池说明
         */
        private String detail;

        private int corePoolNum;

        private int maxPoolNum;

        private int deleteThreadNum;

        private TimeUnit deleteTreadUnit;

        private LinkedBlockingDeque blockingDeque;

        private RejectedExecutionHandler rejectedExecutionHandler;

        static Map<String, ThreadPoolEnum> map = new HashMap<String, ThreadPoolEnum>(16);
        static {
            for (ThreadPoolEnum threadPoolName : ThreadPoolEnum.values()) {
                map.put(threadPoolName.getName(), threadPoolName);
            }
        }

        /**
         *  根据线程池名称获取线程池枚举
         * @param name 线程池名称
         * @return 线程池枚举
         */
        public ThreadPoolEnum getThreadPoolEnum(String name) {
            return map.get(name);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

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
            if (thread.isDaemon()){
                thread.setDaemon(false);
            }
            if(thread.getPriority() != Thread.NORM_PRIORITY){
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }

    /**
     *  初始化线程池
     */
    static {
        THREAD_POOL_EXECUTOR_MAP.put(ThreadPoolEnum.name1, getThreadPoolExecutor(ThreadPoolEnum.name1));
        THREAD_POOL_EXECUTOR_MAP.put(ThreadPoolEnum.name2, getThreadPoolExecutor(ThreadPoolEnum.name2));
    }

    /**
     *  根据枚举获取线程池
     * @param threadPoolEnum 线程池枚举
     */
    private static ThreadPoolExecutor getThreadPoolExecutor(ThreadPoolEnum threadPoolEnum) {
        return new ThreadPoolExecutor(threadPoolEnum.corePoolNum,
                threadPoolEnum.maxPoolNum,
                threadPoolEnum.deleteThreadNum,
                threadPoolEnum.deleteTreadUnit,
                threadPoolEnum.blockingDeque,
                new DefaultThreadFactory(threadPoolEnum.name),
                threadPoolEnum.rejectedExecutionHandler);
    }

    /**
     *  执行没有返回值的任务
     * @param threadPoolEnum 线程池枚举
     * @param runnable 任务
     */
    public static void execute(ThreadPoolEnum threadPoolEnum, Runnable runnable) {
        if (!THREAD_POOL_EXECUTOR_MAP.containsKey(threadPoolEnum)) {
            throw new IllegalArgumentException("未找到线程池：" + threadPoolEnum.name);
        }
        // 执行任务
        THREAD_POOL_EXECUTOR_MAP.get(threadPoolEnum).execute(runnable);
    }

    /**
     * 执行有返回值任务
     *
     * @param threadPoolEnum
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> List<Future<T>> execute(ThreadPoolEnum threadPoolEnum, Callable<T>... callable) {
        if (!THREAD_POOL_EXECUTOR_MAP.containsKey(threadPoolEnum)) {
            throw new IllegalArgumentException("未配置线程池" + threadPoolEnum.name);
        }
        ThreadPoolExecutor executor = THREAD_POOL_EXECUTOR_MAP.get(threadPoolEnum);
        List<Future<T>> futureList = new ArrayList<Future<T>>(callable.length);
        for (int i = 0; i < callable.length; i++) {
            Future<T> f = executor.submit(callable[i]);
            futureList.add(f);
        }
        return futureList;
    }

    /**
     * 批量执行并行任务
     * @param threadPoolEnum
     * @param callableList
     * @return
     */
    /*public static List<Future> executeAll(ThreadPoolEnum threadPoolEnum, List<Callable> callableList) {
        if (!THREAD_POOL_EXECUTOR_MAP.containsKey(threadPoolEnum)) {
            throw new IllegalArgumentException("未配置线程池" + threadPoolEnum.name);
        }
        try {
            return THREAD_POOL_EXECUTOR_MAP.get(threadPoolEnum).invokeAll(callableList, 2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.error("线程池批量执行任务异常失败", e);
        } catch (Exception e) {
            logger.error("线程池批量执行任务异常失败", e);
        }
        return Lists.newArrayList();
    }*/

    /**
     * 执行无返回的线程任务
     * @param threadPoolEnum
     * @param r
     */
    public static void executeRunnable(ThreadPoolEnum threadPoolEnum, Runnable... r) {
        if (!THREAD_POOL_EXECUTOR_MAP.containsKey(threadPoolEnum)) {
            throw new IllegalArgumentException("未配置线程池" + threadPoolEnum.name);
        }
        ThreadPoolExecutor executor = THREAD_POOL_EXECUTOR_MAP.get(threadPoolEnum);
        for (int i = 0; i < r.length; i++) {
            executor.submit(r[i]);
        }
    }

}
