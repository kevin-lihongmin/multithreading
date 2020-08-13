package com.kevin.multithreading.geektime.division.jdk8;


import com.kevin.multithreading.util.SimpleThreadPool;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  {@link java.util.concurrent.CompletableFuture#allOf(CompletableFuture[])} 的顺序问题
 *
 *  当前值为：1
 * 当前值为：2
 * 当前值为：3
 * 当前值为：4
 * StopWatch '': running time (millis) = 2125
 *
 * @author kevin
 * @date 2020/8/13 21:43
 * @since 1.0.0
 */
public class CompletableFutureOfAll {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        ThreadPoolExecutor threadPoolExecutor = SimpleThreadPool.THREAD_POOL_EXECUTOR_MAP.get(SimpleThreadPool.ThreadPoolEnum.CREATE_ORDER);

        stopWatch.start();
        List<CompletableFuture<Object>> taskList = new ArrayList<>();
        CompletableFuture<Object> t1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        },threadPoolExecutor);
        CompletableFuture<Object> t2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        },threadPoolExecutor);
        CompletableFuture<Object> t3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        },threadPoolExecutor);
        CompletableFuture<Object> t4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 4;
        },threadPoolExecutor);
        taskList.add(t1);
        taskList.add(t2);
        taskList.add(t3);
        taskList.add(t4);

        CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0]));

        taskList.forEach(obj -> {
            try {
                Integer num = (Integer) obj.get();
                System.out.println("当前值为：" + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        stopWatch.stop();
        System.out.println(stopWatch.shortSummary());

        threadPoolExecutor.shutdownNow();
    }
}
