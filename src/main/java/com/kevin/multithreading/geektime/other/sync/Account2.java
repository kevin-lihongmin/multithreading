package com.kevin.multithreading.geektime.other.sync;


/**
 *  模拟转账
 *
 *  在方法上加synchronized ,当前的锁不能保护 account2
 * @author kevin
 * @date 2020/7/26 23:05
 * @since 1.0.0
 */
public class Account2 {

    private Long id;
    /**
     *  账号余额
     */
    private Double balance;

    /**
     *  转账方法
     *  使用使用synchronized 可以保证转账的安全吗？
     *
     * @param account2 转钱的账号
     * @param money 转的金额
     */
    public synchronized void transfer(Account2 account2, Double money) {
        if (this.balance > money) {
            this.balance -= money;
            account2.balance += money;
        }
    }

    /**
     *  上面会有线程安全问题系列，但是所有人的转账操作都是串行的
     *  效率非常低
     * @param account2 转钱的账号
     * @param money 转的金额
     */
    public void transfer2(Account2 account2, Double money) {
        synchronized (Account2.class) {
            if (this.balance > money) {
                this.balance -= money;
                account2.balance += money;
            }
        }
    }

    /**
     *  使用细粒度锁
     *  <p> A给B转账，不影响C和D转账
     *  <p> 但是可能出现死锁的问题
     *
     * @param account2 转钱的账号
     * @param money 转的金额
     */
    public void transfer3(Account2 account2, Double money) {
        synchronized (this) {
            synchronized (account2) {
                if (this.balance > money) {
                    this.balance -= money;
                    account2.balance += money;
                }
            }
        }
    }

    /**
     *  使用破坏循环等待条件，防止死锁
     * @param account2 转钱的账号
     * @param money 转的金额
     */
    public void transfer4(Account2 account2, Double money) {
        Account2 left = this;
        Account2 right = account2;
        if (this.id > account2.id) {
            left = account2;
            right = this;
        }
        synchronized (left) {
            synchronized (right) {
                if (this.balance > money) {
                    this.balance -= money;
                    account2.balance += money;
                }
            }
        }
    }

    /**
     *  引入锁管理器,一般使用单例
     */
    private Allocator allocator;

    /**
     *  引入锁管理器,一般使用单例
     */
    private WaitNotifyAllocator waitNotifyAllocator;

    /**
     *  破坏占用且等待条件
     * @param account2 转钱的账号
     * @param money 转的金额
     */
    public void transfer5(Account2 account2, Double money) {
        // 阻塞一次性申请所有需要的锁，直到成功
        while (!allocator.apply(this, account2)) {
            try {
                synchronized (this) {
                    synchronized (account2) {
                        if (this.balance > money) {
                            this.balance -= money;
                            account2.balance += money;
                        }
                    }
                }
            } finally {
                allocator.free(this, account2);
            }
        }
    }

    /**
     *  破坏占用且等待条件
     * @param account2 转钱的账号
     * @param money 转的金额
     */
    public void transfer6(Account2 account2, Double money) {
        // 阻塞一次性申请所有需要的锁，直到成功
        if (waitNotifyAllocator.apply(this, account2)) {
            try {
                synchronized (this) {
                    synchronized (account2) {
                        if (this.balance > money) {
                            this.balance -= money;
                            account2.balance += money;
                        }
                    }
                }
            } finally {
                waitNotifyAllocator.free(this, account2);
            }
        }
    }
}
