package com.kevin.multithreading.designpattern;


/**
 *  自己设置中断标志位的 两阶段终止模式
 *
 * @author kevin
 * @date 2020/8/6 23:16
 * @since 1.0.0
 */
public class TerminatedTwoStageTermination {
    /** 设置自己的中断标志位 */
    private volatile boolean terminated = false;
    boolean started = false;
    /** 采集线程 */
    Thread rptThread;

    public synchronized void start() {
        // 只能启动一个监控线程
        if (started) {
            return;
        }
        started = true;
        terminated = false;
        rptThread = new Thread(() -> {
            while (!terminated) {
                // 采集工作
                report();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // 重新设置自己的中断标志位
                    Thread.currentThread().interrupt();
                }
            }
            started = false;
        });
        rptThread.start();
    }

    public synchronized void stop() {
        // 设置自己的中断标志位
        terminated = true;
        // 中断线程，即使是 TIMED_WAITING状态的线程，然后进行下一次判断 自己的终止标志位 terminated
        rptThread.interrupt();
    }

    private void report() {
    }


}
