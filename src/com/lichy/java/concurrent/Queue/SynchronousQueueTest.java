package com.lichy.java.concurrent.Queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 测试SynchronousQueue
 *
 * @author lichy
 */
public class SynchronousQueueTest {


    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        Thread t1 = new Thread(() -> {
            try {
                synchronousQueue.put("1");
                System.out.println("放入1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("取出1");
        });
        Thread t3 = new Thread(() -> {
            try {
                synchronousQueue.put("2");
                System.out.println("放入2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("取出2");
        });
        t1.start();
        t2.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t3.start();
        t4.start();
    }
}
