package com.kevin.multithreading.designpattern;


import lombok.extern.slf4j.Slf4j;

/**
 *  两阶段终止提交
 *  模式监控服务数据场景 当前是使用线程的终止标志位【Thread.currentThread().isInterrupted()】，
 *  但是线上环境很可能第三方类库没有正确的处理中断异常（比如try catch之后处理了其异常），所以一般是自己设置标志位
 *
 *  所以一般建议设置自己的标志位做检查， 如： {@link TerminatedTwoStageTermination}
 * @author kevin
 * @date 2020/8/6 23:00
 * @since 1.0.0
 */
@Slf4j
public class TwoStageTermination {
    boolean started = false;

    Thread rptThread;

    /**
     *  启动采集功能
     */
    public synchronized void start() {
        // 只能启动一个监控线程
        if (started) {
            return;
        }
        started = true;
        rptThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
               // 省略采集回传实现
               report();
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   log.error("hehe");
                   // 重新设置中断标志位
                   Thread.currentThread().interrupt();
               }
            }
            // 执行到此处线程马上终止
            started = false;
        });
        rptThread.start();
    }

    /**
     *  终止采集
     */
    synchronized void stop() {
        rptThread.interrupt();
    }

    /**
     *  模拟回传数据
     */
    private void report() {}

}
