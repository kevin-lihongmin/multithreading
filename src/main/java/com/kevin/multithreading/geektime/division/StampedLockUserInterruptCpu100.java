package com.kevin.multithreading.geektime.division;


import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 *  使用{@link StampedLock} 时候直接调用 {@link Thread#interrupt()} 会照成CPU 100%
 *
 * @author kevin
 * @date 2020/7/31 23:26
 * @since 1.0.0
 */
public class StampedLockUserInterruptCpu100 {

    public static void main(String[] args) throws InterruptedException {

        new StampedLockUserInterruptCpu100().cpu100Percent();
    }

    private void cpu100Percent() throws InterruptedException {
        final StampedLock stampedLock = new StampedLock();

        Thread thread = new Thread(() -> {
            // 获取写锁
            stampedLock.writeLock();
            // 模拟任务永远阻塞，则需要调用线程的interrupt
            LockSupport.park();
        });
        thread.start();
        // 为保证thread获取写锁，休眠
        Thread.sleep(100);

        Thread thread2 = new Thread(() -> {
            // 阻塞在悲观读
            stampedLock.readLock();
        });
        thread2.start();

        // 保证thread2 阻塞在读锁
        Thread.sleep(100);

        // 中断thread2 线程， 会导致所在线程的CPU飙升
        thread2.interrupt();
        thread2.join();
    }
}
