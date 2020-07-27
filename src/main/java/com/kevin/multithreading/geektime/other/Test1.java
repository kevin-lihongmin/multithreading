package com.kevin.multithreading.geektime.other;


/**
 *  【缓存】可见性问题
 *
 *  打印结果： 14786、20000、15444
 *
 * @author kevin
 * @date 2020/7/26 15:10
 * @since 1.0.0
 */
public class Test1 {

    /**
     *  起始值
     */
    private long count = 0;

    private long getCount() {
        return count;
    }

    /**
     *  加加一万次
     */
    private void add() {
        int index = 0;
        int loop = 10000;
        while (index++ < loop) {
            ++count;
        }
    }

    /**
     *  计算多线程下的总值
     * @return 叠加的结果
     * @throws InterruptedException 线程中断异常
     */
    public Long calc() throws InterruptedException {
        final Test1 test1 = new Test1();
        Thread thread = new Thread(() -> test1.add());
        Thread thread1 = new Thread(() -> test1.add());
        // 启动线程，等待CPU时间切片
        thread.start();
        thread1.start();
        // 等到两个线程执行结束
        thread.join();
        thread1.join();

        return test1.getCount();
    }

    public static void main(String[] args) throws InterruptedException {
        Long calc = new Test1().calc();
        System.out.println(calc);
    }
}
