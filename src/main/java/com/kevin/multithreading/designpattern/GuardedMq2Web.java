package com.kevin.multithreading.designpattern;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 *  模拟 Guarded Suspension 现实场景， 用户使用Web页面调用后台服务处理数据，然而当前服务需要调用其他服务处理，
 *  而该服务只提供了mq的对接方式。则发送出去mq请求处理， 以及处理完成之后需要接受一个Mq，将结果返回给Web页面
 *
 *  <p> 问题： 当前发送mq的线程和 消费mq的线程不是同一个线程； 可以实现多线程的 Guarded Suspension 模式
 * @author kevin
 * @date 2020/8/5 23:50
 * @since 1.0.0
 */
public class GuardedMq2Web<T> {
    /** 受保护的对象 */
    private T object;
    private final Lock lock = new ReentrantLock(true);
    private final Condition done = lock.newCondition();
    final int timeout = 2;

    /** 保存所以的 {@link GuardedMq2Web} */
    final static Map<Object, GuardedMq2Web> gos = new ConcurrentHashMap<>();

    /**
     *  静态的 创建对象方法
     * @param key mq的唯一表示id
     * @param <K> 对象
     * @return 创建的对象
     */
    public static <K> GuardedMq2Web create(K key) {
        GuardedMq2Web guarded = new GuardedMq2Web();
        return gos.put(key, guarded);
    }

    /**
     *  接收mq的方法，调用该方法
     * @param key mq的唯一表示id
     * @param object mq返回的内容对象
     * @param <K> String类型
     * @param <T> 可能也是String类型
     */
    public static <K,T> void fireEvent(K key, T object) {
        GuardedMq2Web remove = gos.remove(key);
        if (remove != null) {
            remove.onChanged(object);
        }
    }

    /**
     *  阻塞 获取对象
     * @param predicate 处理器
     * @return 远端执行结果
     */
    public T get(Predicate<T> predicate) {
        lock.lock();
        try {
            while (!predicate.test(object)) {
                done.await(timeout, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return object;
    }

    /**
     *  唤醒机制
     * @param object 获取到的对象
     */
    private void onChanged(T object) {
        lock.lock();
        try {
            this.object = object;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     *  模式Web情况，阻塞获取结果
     * @return
     */
    String handlerWebRequest() {
        // 模拟生成 mq的唯一表示
        String mqId = UUID.randomUUID().toString();

        GuardedMq2Web guarded = GuardedMq2Web.create(mqId);
        // 发送mq给具体处理业务的服务
        sendMq(new Message(mqId));
        // 等待结果
        Object result = guarded.get(t -> t != null);
        return result.toString();
    }

    /**
     *  模拟接收到mq
     * @param msg mq结果
     */
    void onMessage(Message msg) {
        // 唤醒等待的线程
        GuardedMq2Web.fireEvent(msg.id, msg);
    }

    @Data
    @AllArgsConstructor
    class Message{
        String id;
    }

    Boolean sendMq(Message message) {
        // 我发送出去mq了
        return true;
    }

}
