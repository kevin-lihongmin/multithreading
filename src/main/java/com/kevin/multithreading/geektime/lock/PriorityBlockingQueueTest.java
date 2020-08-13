package com.kevin.multithreading.geektime.lock;

import com.kevin.multithreading.util.SimpleThreadPool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  测试优先级队列，   出队结果与优先级无关
 * @author kevin
 * @date 2020/8/12 17:02
 * @since 1.0.0
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        Callable<User>[] call = new Callable[20];
        for (int i = 0; i < 20; i++) {
            int num = atomicInteger.getAndIncrement();
            call[i] = () -> {
                System.out.println("我执行了：" + num);
                User user = new User(num, "name" + num);
                return user;
            };
            System.out.println("我添加了：" + num);
        }
        List<Future<User>> futures = SimpleThreadPool.executeAll(SimpleThreadPool.ThreadPoolEnum.CREATE_ORDER, Arrays.asList(call));


        for (Future<User> future : futures) {
            System.out.println(future.get().getId() + " !");
        }
    }

    private static void test() throws InterruptedException, ExecutionException {
        PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<User>(20, (user1, user2) -> user1.getId() - user1.getId());
        Callable<User>[] call = new Callable[20];
        CountDownLatch countDownLatch = new CountDownLatch(20);
        final Random random = new Random(2000);
        for (int i = 0; i < 20; i++) {
            call[i] = () -> {
                int num = Math.abs(random.nextInt());
                System.out.println("我执行了：" + num);
                User user = new User(num, "name" + num);
                queue.add(user);
                countDownLatch.countDown();
                return user;
            };
        }

        SimpleThreadPool.execute(SimpleThreadPool.ThreadPoolEnum.CREATE_ORDER, call);
        countDownLatch.await();

        queue.forEach(user -> {
            System.out.println(user.getId() + " !");
        });

        List<Future<User>> futures = SimpleThreadPool.executeAll(SimpleThreadPool.ThreadPoolEnum.CREATE_ORDER, Arrays.asList(call));


        for (Future<User> future : futures) {
            System.out.println(future.get().getId() + " !");
        }
    }

    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    public static class User {
        private int id;

        private String name;
    }

}
