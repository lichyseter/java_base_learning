package com.lichy.java.concurrent;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phaser测试类
 *
 * @author lichy
 */
public class PhaserTest {

    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser();
        Thread t1 = new Task(phaser, 1);
        Thread t2 = new Task(phaser, 2);
        Thread t3 = new Task(phaser, 3);
        t1.start();
        t2.start();
        t3.start();
    }

    static class MyPhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println((phase + 1) + "阶段共有" + registeredParties + "个组同步");
            return phase == 3;
        }
    }

    static class Task extends Thread {
        Phaser phaser;
        int num;

        Task(Phaser phaser, int i) {
            this.phaser = phaser;
            this.num = i;
            phaser.register();
        }

        @Override
        public void run() {
            System.out.println("--------线程【" + num + "】第一阶段-------");
            try {
                TimeUnit.MILLISECONDS.sleep(100 * num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("--------线程【" + num + "】第二阶段-------");
            try {
                TimeUnit.MILLISECONDS.sleep(100 * num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            if (num == 3) {
                phaser.arriveAndDeregister();
            } else {
                phaser.arriveAndAwaitAdvance();
                System.out.println("--------线程【" + num + "】第三阶段-------");
                try {
                    TimeUnit.MILLISECONDS.sleep(100 * num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                phaser.arriveAndAwaitAdvance();
            }
            System.out.println("--------线程【" + num + "】完成-------");
        }
    }
}
