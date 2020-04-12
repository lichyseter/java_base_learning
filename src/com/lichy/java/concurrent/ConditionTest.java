package com.lichy.java.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试Condition
 *
 * @author lichy
 */
public class ConditionTest {

    /**
     * 数据
     */
    private static int data;

    /**
     * 锁对象
     */
    private static ReentrantLock lock = new ReentrantLock(true);
    /**
     * condition
     */
    private static Condition condition = lock.newCondition();
    /**
     * 完成
     */
    private volatile static boolean finished = false;

    /**
     * 有数据
     */
    private volatile static boolean hasData = false;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 100; i++) {
                    while (hasData) {
                        condition.await();
                    }
                    data = i;
                    hasData = true;
                    condition.signalAll();
                }
                finished = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
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
            while (!finished) {
                lock.lock();
                try {
                    while (!hasData && !finished) {
                        condition.await();
                        System.out.println("唤醒了" + num);
                    }
                    if (hasData) {
                        System.out.println("消费者" + num + "消费" + data);
                        hasData = false;
                        condition.signalAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }
}
