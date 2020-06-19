package com.lichy.java.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                LockSupport.park();
                System.out.println("开始执行后线程");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LockSupport.unpark(Thread.currentThread());
            }
        }
        );
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("等待一秒钟");
        Thread t2 = new Thread(() -> {
            try {
                System.out.println("先执行线程");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LockSupport.unpark(t);
            }
        }
        );
        t2.start();
        LockSupport.parkNanos(10000);
    }
}
