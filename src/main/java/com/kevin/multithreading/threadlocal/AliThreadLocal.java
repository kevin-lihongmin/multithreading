package com.kevin.multithreading.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  线程池的父子线程共享变量
 *
 * @author kevin
 * @date 2020/10/10 20:57
 * @since 1.0.0
 */
public class AliThreadLocal {

    static ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);

    static {
        executorService.prestartAllCoreThreads();
    }

    public static void main(String[] args) throws InterruptedException {
        TransmittableThreadLocal<String> itl = new TransmittableThreadLocal<>();
        itl.set("kevin-2");

        // 使用TtlRunnable或TtlCallable包装原生回调方法
        executorService.execute(Objects.requireNonNull(TtlRunnable.get(() -> {
            System.out.println("InheritableThreadLocal ThreadPoolExecutor by TtlRunnable:" + itl.get());
        })));

        // 使用包装TtlExecutors包装原生的ThreadPoolExecutor
        // 1、getTtlExecutor：修饰接口Executor
        // 2、getTtlExecutorService：修饰接口ExecutorService
        // 3、getTtlScheduledExecutorService：修饰接口ScheduledExecutorService
        Executor ttlExecutor = TtlExecutors.getTtlExecutor(executorService);
        ttlExecutor.execute(() -> {
            System.out.println("InheritableThreadLocal ThreadPoolExecutor by TtlExecutors:" + itl.get());
        });

        Thread.sleep(10000);
    }
    /*
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    threadLocal.set("kevin-1");
    new Thread(() -> {
        System.out.println("ThreadLocal:" + threadLocal.get());
    }).start();

    InheritableThreadLocal<String> itl = new InheritableThreadLocal<>();
    itl.set("kevin-2");
    new Thread(() -> {
        System.out.println("InheritableThreadLocal:" + itl.get());
    }).start();

    executorService.execute(() -> {
        System.out.println("InheritableThreadLocal ThreadPoolExecutor:" + itl.get());
    });

    Thread.sleep(10000);*/
}
