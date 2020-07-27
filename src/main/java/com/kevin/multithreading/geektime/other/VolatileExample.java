package com.kevin.multithreading.geektime.other;


/**
 *  volatile关键字
 *
 *
 *
 * @author kevin
 * @date 2020/7/26 19:46
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class VolatileExample {

    int number = 0;

    volatile boolean flag = false;

    public void write() {
        number = 42;
        flag = true;
    }

    public void read() {
        if (flag) {
            // jdk 1.5之前可能为 0，可能为42； 之后一定为 42
            System.out.println(number);
        }
    }
}
