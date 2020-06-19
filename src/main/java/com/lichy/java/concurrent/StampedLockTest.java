package com.lichy.java.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 核心是读的时候允许写，如果发生写了的话，name重读一次。这样提高写的并发效率
 */
public class StampedLockTest {

    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
        for (int i = 0; i < 2; i++) {
            final int count = i;
            new Thread(() -> {
                long lock = stampedLock.writeLock();
                try {

                    TimeUnit.SECONDS.sleep(1 + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    stampedLock.unlockWrite(lock);
                }
                System.out.println("写线程执行完毕" + count);
            }
            ).start();
        }

        for (int i = 0; i < 2; i++) {
            final int count = i;
            new Thread(() -> {
                long lock = stampedLock.tryOptimisticRead();
                // 在这里做一些事，然后再检查试着stamp是否正确，如果不正确，需要显示加锁再做一次。

                if (!stampedLock.validate(lock)) {
                    lock = stampedLock.readLock();
                    try {
                        TimeUnit.SECONDS.sleep(1 + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        stampedLock.unlockRead(lock);
                    }
                }
                System.out.println("读线程执行完毕" + count);
            }
            ).start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
