package com.lichy.java.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch测试类
 *
 * @author lichy
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("线程" + finalI + "开始执行");
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + finalI + "结束");
            }).start();
        }
    }
}
