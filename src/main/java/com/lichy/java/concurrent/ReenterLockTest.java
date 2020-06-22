package com.lichy.java.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class ReenterLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        for (int i = 0; i < 3; i++) {
            final int count = i;
            new Thread(() -> {
                lock.lock();
                try {
                    if (count == 1) {
                        condition2.await();
                    } else if (count == 2){
                        condition3.await();
                    }
                    System.out.println("线程开始执行" + count);
                    TimeUnit.SECONDS.sleep(1 + count);
                    if (count == 0) {
                        condition2.signal();
                    } else if (count == 1) {
                        condition3.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
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
