package com.lichy.java.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试类
 *
 * @author lichy
 */
public class ReentrantReadWriteLockTest {
    /**
     * 锁对象
     */
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * 锁对象
     */
    private static Lock readLock = lock.readLock();
    /**
     * 锁对象
     */
    private static Lock writeLock = lock.writeLock();
    /**
     * 缓存数据队列
     */
    private static int[] integers = new int[3];
    /**
     * 缓存不
     */
    private static volatile boolean cacheValidate = false;


    public static void main(String[] args) {
        Thread producer1 = new ConsumerThread(1);
        Thread producer2 = new ConsumerThread(2);
        producer1.start();
        producer2.start();
    }

    /**
     * 消费者线程
     */
    static class ConsumerThread extends Thread {
        /**
         * 标记
         */
        int num;

        ConsumerThread(int i) {
            super();
            this.num = i;
        }

        @Override
        public void run() {
            readLock.lock();
            for (int i = 0; i < 200; i++) {
                if (!cacheValidate) {
                    readLock.unlock();
                    writeLock.lock();
                    if (!cacheValidate) {
                        integers[0] = 1;
                        integers[1] = 2;
                        integers[2] = 3;
                    }
                    cacheValidate = true;
                    System.out.println("线程" + num + "初始化缓存完成。");
                    readLock.lock();
                    writeLock.unlock();
                }
                System.out.println("线程" + num + "输出" + integers[i > 2 ? 2 : i]);
            }
            readLock.unlock();
        }
    }
}
