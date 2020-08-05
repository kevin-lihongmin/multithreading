package com.kevin.multithreading.geektime.division.jdk8;


import org.springframework.util.StopWatch;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *  基于{@link ForkJoinTask} 分治实现 斐波那契数的计算
 *
 * <p> 4个线程 <p/>
 * 当前线程id = 12
 * 当前线程id = 12
 * 当前线程id = 11
 * 当前线程id = 11
 * 当前线程id = 12
 * 当前线程id = 11
 * 斐波那契数【30】结果为：832040
 * StopWatch '': running time (millis) = 30934
 *
 * <p> 40个线程 <p/>
 * 当前线程id = 13
 * 当前线程id = 13
 * 当前线程id = 24
 * 当前线程id = 24
 * 当前线程id = 21
 * 当前线程id = 21
 * 斐波那契数【30】结果为：832040
 * StopWatch '': running time (millis) = 31510
 *
 * @author kevin
 * @date 2020/8/5 21:17
 * @since 1.0.0
 */
public class ForkJoinFibonacci {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 创建分治任务线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(40);
        // 创建分支任务
        Fibonacci fibonacci = new Fibonacci(30);
        // 启动分支任务
        Integer result = forkJoinPool.invoke(fibonacci);
        System.out.println("斐波那契数【30】结果为：" + result);

        stopWatch.stop();
        System.out.println(stopWatch.shortSummary());
    }

    /**
     *  由于需要返回计算的值，所以继承自{@link RecursiveTask}
     */
    static class Fibonacci extends RecursiveTask<Integer> {

        final int n;
        public Fibonacci(int n) {
            this.n = n;
        }
        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            // 打印线程
            System.out.println("当前线程id = " + Thread.currentThread().getId());
            Fibonacci f1 = new Fibonacci(n -1);
            // 创建子任务
            f1.fork();

            Fibonacci f2 = new Fibonacci(n - 2);
            // 等待子任务结果，并合并计算结果
            return f2.compute() + f1.join();
        }
    }
}
