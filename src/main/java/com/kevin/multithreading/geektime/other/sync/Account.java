package com.kevin.multithreading.geektime.other.sync;


/**
 *  模拟银行账户的修改密码和账户余额
 *  用不同的锁包含资源进行精细化管理，能够提升性能，被称为【细粒度锁】
 * @author kevin
 * @date 2020/7/26 22:54
 * @since 1.0.0
 */
public class Account {
    /**
     *  保护账户余额的锁
     */
    private final Object accountLock = new Object();

    /**
     * 保护密码的锁
     */
    private final Object passwordLock = new Object();

    /**
     *  账户余额，不考虑精度丢失问题
     */
    private Double balance;

    /**
     *  账户密码
     */
    private String password;

    /**
     *  模仿取款
     * @param money 取款的钱
     */
    public void withdraw(Double money) {
        synchronized (accountLock) {
            if (money < balance) {
                this.balance -= money;
            }
        }
    }

    /**
     *  模拟查看余额
     * @return 余额
     */
    public Double getBalance() {
        synchronized (accountLock) {
            return balance;
        }
    }

    /**
     *  模拟修改密码
     * @param password 需要修改的密码
     */
    public void updatePassword(String password) {
        synchronized (passwordLock) {
            this.password = password;
        }
    }

    /**
     *  模拟查看密码
     * @return 当前密码
     */
    public String getPassword() {
        synchronized (passwordLock) {
            return password;
        }
    }

}
