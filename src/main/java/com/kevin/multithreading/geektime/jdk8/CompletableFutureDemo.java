package com.kevin.multithreading.geektime.jdk8;

import java.util.concurrent.CompletableFuture;

/**
 *  {@link java.util.concurrent.CompletableFuture} 实现泡茶例子
 *  1、洗水壶，烧开水
 *  2、洗茶壶，洗茶杯，那茶叶
 *  3、泡茶
 *
 *  步骤1和2可以并行；3需要等待1和2执行完成
 *
 *  <p>
 *      CompletableFuture方法基本都提供了两个，在没有传入线程池Executor的情况下，默认会使用ForkJoin的公共线程池，否则使用传入的线程池
 *      CompletableFuture继承自CompletableStage，其中定义了很多的并发执行的 AND、OR、分支合并等接口，并且该接口也分成两类，如果没有传入线程池Executor
 *          则使用上面传入非线程池，否则使用传入的【后续可以打印一下线程名称】
 *
 * @author lihongmin
 * @date 2020/8/4 15:32
 * @since 1.0.0
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            System.out.println("洗水壶");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("烧开水");
        });
    }
}
