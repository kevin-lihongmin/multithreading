package com.kevin.multithreading.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 *  线程池的父子线程共享变量
 *
 * @author kevin
 * @date 2020/10/10 20:57
 * @since 1.0.0
 */
public class AliThreadLocal {

    public static void main(String[] args) {
//        ThreadLocal threadLocal = new ThreadLocal();

        InheritableThreadLocal itl = new InheritableThreadLocal();

        TransmittableThreadLocal<Object> ttl = new TransmittableThreadLocal();

//        ttl.
    }
}
