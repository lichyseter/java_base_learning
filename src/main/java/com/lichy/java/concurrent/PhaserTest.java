package com.lichy.java.concurrent;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 5; i++) {
            final int count = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1 + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                phaser.arriveAndAwaitAdvance();
                System.out.println("开始执行步骤1：" + count);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行步骤1结束：" + count);
                phaser.arriveAndAwaitAdvance();
                System.out.println("开始执行步骤2：" + count);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行步骤2结束：" + count);

                if (count == 0) {
                    phaser.arriveAndDeregister();
                } else {
                    phaser.arriveAndAwaitAdvance();
                    System.out.println("开始执行步骤3：" + count);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("执行步骤3结束：" + count);
                }
            }
            ).start();
        }
    }
}
