package com.kevin.multithreading.work.threadpool.client;

import com.google.common.collect.Lists;
import com.kevin.multithreading.work.threadpool.ThreadPool;
import com.kevin.multithreading.work.threadpool.ThreadPoolEntity;
import com.kevin.multithreading.work.threadpool.ThreadPoolInit;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  线程池初始化， Java SPI实现
 *
 * @author kevin
 * @date 2020/8/23 14:36
 * @since 1.0.0
 */
public class ThreadPoolImpl implements ThreadPool {

    @Override
    public List<ThreadPoolEntity> appendThreadPool() {
        return Lists.newArrayList(
                new ThreadPoolEntity(ThreadPoolEnum.ORDER.name(), ThreadPoolInit.PoolModel.IO,  Boolean.FALSE, Boolean.TRUE,
                        "货源安排线程池【Per PO Per Thread】", 2, 8, 30, TimeUnit.SECONDS,
                        new LinkedBlockingDeque(50), new ThreadPoolExecutor.AbortPolicy())
            );
    }
}