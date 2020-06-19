package com.lichy.java.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int count = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1 + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程执行完毕" + count);
                countDownLatch.countDown();
            }
            ).start();
        }
        System.out.println("开始执行");
        countDownLatch.await();
        System.out.println("所有执行完成");
    }
}
