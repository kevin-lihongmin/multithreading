package com.kevin.multithreading.geektime;

/**
 *  证明多线程未必快，已经线程上下文切换的影响非常大
 * @author kevin
 * @date 2020/10/29 0:09
 * @since 1.0.0
 */
public class ContextSwitchDemo {
    public static void main(String[] args) {
        //运行多线程
        MultiThreadTester test1 = new MultiThreadTester();
        test1.Start();
        //运行单线程
        SerialTester test2 = new SerialTester();
        test2.Start();
    }

    public static class MultiThreadTester extends ThreadContextSwitchTester {
        @Override
        public void Start() {
            long start = System.currentTimeMillis();
            MyRunnable myRunnable1 = new MyRunnable();
            Thread[] threads = new Thread[12];
            //创建多个线程
            for (int i = 0; i < 12; i++) {
                threads[i] = new Thread(myRunnable1);
                threads[i].start();
            }
            for (int i = 0; i < 12; i++) {
                try {
                    //等待一起运行完
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("multi thread exce time: " + (end - start) + "s");
            System.out.println("counter: " + counter);
        }
    // 创建一个实现Runnable的类
    class MyRunnable implements Runnable {
        public void run() {
           while (counter < 100000000) {
                  synchronized (this) {
                         if(counter < 100000000) {
                                increaseCounter();
                         }

                  }
           }
        }
    }
}

  //创建一个单线程
   static class SerialTester extends ThreadContextSwitchTester{
          @Override
          public void Start() {
                 long start = System.currentTimeMillis();
                 for (long i = 0; i < count; i++) {
                       increaseCounter();
                 }
                 long end = System.currentTimeMillis();
                 System.out.println("serial exec time: " + (end - start) + "s");
                 System.out.println("counter: " + counter);
          }
   }

   //父类
   static abstract class ThreadContextSwitchTester {
          public static final int count = 100000000;
          public volatile int counter = 0;
          public int getCount() {
                 return this.counter;
          }
          public void increaseCounter() {

                 this.counter += 1;
          }
          public abstract void Start();
   }
}