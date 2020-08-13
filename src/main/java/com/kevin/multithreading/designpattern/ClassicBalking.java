package com.kevin.multithreading.designpattern;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  经典的Balking模式的写法
 *
 *  比如多线程情况下，定时判断文本【CSDN博客，processon等】是否有修改过，如果有则进行保存， 没有修改过则不保存； 称之为 Balking模式
 *  1、可以将修改{@code changed} 表示的方法用 synchronized或者 Lock修饰
 *  2、使用 volitile修饰，没有原子性要求， 比如 Dubbo等负载均衡会将服务的列表同步到本地， 定时查询是否发生了变化
 *  3、还有一种无锁的方式是， volitile + 双重检查【单例】的实现
 *
 *
 * @author kevin
 * @date 2020/8/6 21:42
 * @since 1.0.0
 */
public class ClassicBalking {
    boolean changed = false;
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    /**
     *   启动字段保存方法
     */
    void startAutoSave() {
        service.scheduleWithFixedDelay(() -> {
            // 自动保存
            autoSave();
            // 每5秒钟字自动保存一次
        }, 5, 5, TimeUnit.SECONDS);
    }

    private void autoSave() {
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed = false;
        }
        System.out.println("我保存了");
    }

    private void edit() {
        synchronized (this) {
            changed = true;
        }
    }


}
