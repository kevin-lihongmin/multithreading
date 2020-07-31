package com.kevin.multithreading.geektime.division;


import java.util.concurrent.locks.StampedLock;

/**
 *
 * @author kevin
 * @date 2020/7/31 22:45
 * @since 1.0.0
 */
public class StampedLockDemo {

    public static void main(String[] args) {

    }

    /**
     *  模拟多线程环境下的两个可能被其他线程修改的变量
     */
    int x, y;
    final StampedLock stampedLock = new StampedLock();

    /**
     *  计算两点间的距离
     * @return 距离
     */
    private double userOptimisticLock() {
        // 获取乐观锁
        long stamp = stampedLock.tryOptimisticRead();
        // 乐观地读取变量x，y
        int currentX = x, currentY = y;

        // 判断执行期间是否有发生过变化
        if (!stampedLock.validate(stamp)) {
            // 升级为悲观读锁
            long currentStamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(currentStamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    /**
     *  使用{@link StampedLock} 悲观读，写锁的使用
     */
    private void userLock() {
        final StampedLock lock = new StampedLock();
        // 获取、释放悲观读锁示意图； 相当于乐观锁的版本号
        long stamp = lock.readLock();

        try {

        } finally {
            lock.unlock(stamp);
        }

        // 获取和释放写锁
        long stamped = lock.writeLock();
        try {

        } finally {
            lock.unlockWrite(stamped);
        }
    }
}
