package com.lichy.java.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 5; i++) {
            final int count = i;
            new Thread(() -> {
                System.out.println("准备执行" + count);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("到齐" + count);
                try {
                    TimeUnit.SECONDS.sleep(1 + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完毕" + count);
            }
            ).start();
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cyclicBarrier.reset();
        for (int i = 0; i < 3; i++) {
            final int count = i;
            new Thread(() -> {

                System.out.println("准备执行" + count);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("到齐" + count);
                try {
                    TimeUnit.SECONDS.sleep(1 + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完毕" + count);
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
