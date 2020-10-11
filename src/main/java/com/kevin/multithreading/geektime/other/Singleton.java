package com.kevin.multithreading.geektime.other;


/**
 *  双重锁检查方式，初始化单例，  如果想防止指令重拍序则在instance上加一个 volatile 关键字即可
 *  <p>
 *      编译器优化【只保证在单线程下执行的结果一致】之后，可能的结果：
 *      1、将多条语句位置替换
 *      如： int a = 1；int b = 2； 优化为：int b = 2; int a = 1;  // 单线程下是ok的
 *      2、将高级语言Java的底层一条语句，对于的多条CPU指令进行重拍序下，如下面的双重锁检查
 *         可能在【singleton = new Singleton();】对象时的三个指令，重拍序之后可能把2和3颠倒
 *      如：1）、分配一块内存M
 *          2）、在内存M上初始化Singleton对象
 *          3）、将M地址赋值给instance对象
 *
 *  <p>
 *      可能的问题：
 *      线程A执行到把 【M地址赋值给Instance】但是还没有真正创建对象时发生线程切换，
 *      线程B在第一处的判断instance是否为null，不为null， 直接返回。使用时却发生了NPE
 *
 * @author kevin
 * @date 2020/7/26 18:26
 * @since 1.0.0
 */
public class Singleton {

    /**
     *  单例对象
     */
    private static volatile Singleton singleton;

    /**
     *  对外提供的单例对象，需要保证只初始化一次，每次获取同一对象
     */
    public Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /**
     *  构造私有化
     */
    private Singleton() {
    }
}
