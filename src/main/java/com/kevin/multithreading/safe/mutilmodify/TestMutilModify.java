package com.kevin.multithreading.safe.mutilmodify;

import com.alibaba.dubbo.common.json.JSON;
import com.kevin.multithreading.work.threadpool.SimpleThreadPool;
import com.kevin.multithreading.work.threadpool.ThreadPoolEntity;
import com.kevin.multithreading.work.threadpool.client.ThreadPoolEnum;
import com.kevin.multithreading.work.threadpool.client.ThreadPoolImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *  测试多线程修改一个对象的结果
 *
 * @author kevin
 * @date 2020/11/2 23:02
 * @since 1.0.0
 */
@Slf4j
public class TestMutilModify {

    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        initThreadPool();
        ArrayList<Callable<Object>> taskList = new ArrayList<>(15);
//        CountDownLatch countDownLatch = new CountDownLatch(15);

        set1(user, taskList);
        set2(user, taskList);
        set3(user, taskList);
        set4(user, taskList);
        set5(user, taskList);
        set6(user, taskList);
        set7(user, taskList);
        set8(user, taskList);
        set9(user, taskList);
        set10(user, taskList);
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue11("11");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue12("12");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
        taskList.add(() -> {
            TestMutilModify.sleep(400);
            user.setValue13("13");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
        taskList.add(() -> {
            TestMutilModify.sleep(300);
            user.setValue14("14");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue15("15");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });

        List<Future<Object>> futures = SimpleThreadPool.executeAll(ThreadPoolEnum.ORDER.name(), taskList);
//        Thread.sleep(3000);
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
//                e.printStackTrace();
            } catch (ExecutionException e) {
//                e.printStackTrace();
            }
        });
//        countDownLatch.await();

        log.info("user = {}", user.toString());

    }

    private static void set10(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue10("10");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void set9(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue9("9");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void set8(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue8("8");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void set7(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue7("7");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void set6(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue6("6");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void set5(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(560);
            user.setValue5("5");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void set4(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(2300);
            user.setValue4("4");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void set3(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue3("3");
            log.info("Thread:" + Thread.currentThread().getName());
            return null;
        });
    }

    private static void set1(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(60);
            user.setValue1("1");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void set2(User user, ArrayList<Callable<Object>> taskList) {
        taskList.add(() -> {
            TestMutilModify.sleep(200);
            user.setValue2("2");
            log.info("Thread:" + Thread.currentThread().getName());
//            countDownLatch.countDown();
            return null;
        });
    }

    private static void initThreadPool() {
        ThreadPoolImpl threadPool = new ThreadPoolImpl();
        List<ThreadPoolEntity> threadPoolEntities = threadPool.appendThreadPool();
        SimpleThreadPool.putThreadPool(threadPoolEntities.get(0));
    }

    public static void sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }
}
