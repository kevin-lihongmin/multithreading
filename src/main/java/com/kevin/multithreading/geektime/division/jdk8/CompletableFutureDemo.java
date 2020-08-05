package com.kevin.multithreading.geektime.division.jdk8;

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
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            try {
                long id = Thread.currentThread().getId();
                System.out.println(id + "洗水壶");
                Thread.sleep(300);

                System.out.println(id + "烧开水");
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                long id = Thread.currentThread().getId();
                System.out.println(id + "洗茶壶");
                Thread.sleep(500);

                System.out.println(id + "洗茶杯");
                Thread.sleep(500);

                System.out.println(id + "拿茶叶");
                Thread.sleep(500);

            } catch (InterruptedException e) {}
            return "龙井茶";
        });

        CompletableFuture<String> cf3 = cf1.thenCombineAsync(cf2, (cf1Obj, cf2Obj) -> {
            long id = Thread.currentThread().getId();
            System.out.println(id + "拿到茶叶：" + cf2Obj);
            System.out.println(id + "泡茶。。。");
            return "上茶" +cf2Obj;
        });

        System.out.println(cf3.join());
    }
}
