package com.lichy.java.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore测试类
 *
 * @author lichy
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            Thread t = new Thread(() -> {
//                try {
//                    semaphore.acquire();
//                    TimeUnit.SECONDS.sleep(2);
//                    System.out.println("第"+ finalI +"个线程执行完毕。");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    semaphore.release();
//                }
//            });
//            t.start();
//        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                try {
                    semaphore.acquire(2);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("第" + finalI + "个线程执行完毕。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                }
            });
            t.start();
        }
    }


}
