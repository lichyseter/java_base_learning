package com.lichy.java.concurrent;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个生产者，三个消费者测试公平锁与非公平锁
 *
 * @author lichy
 */
public class ReentrantLockFairTest {
    /**
     * 总数量
     */
    private static int MAX = 100000;

    /**
     * 缓存数据队列
     */
    private static Queue<Integer> integers = new ArrayBlockingQueue<>(20000);

    /**
     * 锁对象
     */
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * 全部生产完成
     */
    private static volatile boolean finished = false;
    /**
     * 总消费次数
     */
    private static int consumCount = 0;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                while (!integers.offer(i)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
            finished = true;
        }
        );
        producer.start();
        Thread consumer1 = new ConsumerThread(1);
        consumer1.start();
        Thread consumer2 = new ConsumerThread(2);
        consumer2.start();
        Thread consumer3 = new ConsumerThread(3);
        consumer3.start();
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
            while (!finished || !integers.isEmpty()) {
                lock.lock();
                try {
                    for (int i = 0; i < 1000; i++) {
                        while (integers.poll() == null) {
                            try {
                                TimeUnit.MILLISECONDS.sleep(100);
                            } catch (InterruptedException ignored) {
                            }
                        }
                    }
                    consumCount++;
                    System.out.println("消费次数" + consumCount);
                    System.out.println("消费者" + num + "消费1000个");
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
