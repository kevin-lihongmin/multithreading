package com.kevin.multithreading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  多线程学习
 *
 * <p>
 *     多线程按照整体划分为：分工（division）、同步（synchronization）、互斥（lock）
 *
 * @author kevin
 * @date 2020/7/26 13:04
 * @since 1.0.0
 */
@SpringBootApplication
public class MultithreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultithreadingApplication.class, args);
	}

}
