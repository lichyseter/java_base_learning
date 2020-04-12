package com.lichy.java.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * 测试LockSupport类
 * 1. 对于wait和notify 必须保证先wait后notify否则会抛异常，LockSupport没有先后顺序，允许先unpark再park，也能正常执行逻辑
 * 2. 对于LockSupport中的UNSAFE.park(false, nanos);其中第一个参数指的是第二个参数是相对时间还是绝对时间，如果false是相对时间。如果是0代表无限等待。
 * 3. 对于Blocker，线程中会有一个parkBlocker的字段，存这个同步用的对象。
 * 4. LockSupport类并不会处理锁
 *
 * @author lichy
 */
public class LockSupportTest {


    public static void main(String[] args) throws InterruptedException {
        testWaitNotify();
        System.out.println("--------------------");
        testLockSupport();
    }

    /**
     * 测试LockSupport
     */
    private static void testLockSupport() throws InterruptedException {
        LockSupportThread t = new LockSupportThread();
        System.out.println("before thread start");
        t.start();
        LockSupport.unpark(t);
        Thread.sleep(100);
        System.out.println("after unpark");
    }

    /**
     * 测试wait-notify的使用
     */
    private static void testWaitNotify() throws InterruptedException {
        WaitNotifyThread t = new WaitNotifyThread();
        synchronized (t) {
            t.start();
            System.out.println("before wait");
            t.wait();
            System.out.println("after wait");
        }
    }

    /**
     * 用于notify和wait的线程
     */
    static class WaitNotifyThread extends Thread {

        public void run() {
            synchronized (this) {
                System.out.println("before notify");
                notify();
                System.out.println("after notify");
            }
        }
    }

    /**
     * 使用LockSupport的线程
     */
    static class LockSupportThread extends Thread {

        public void run() {
            System.out.println("before park");
            LockSupport.park();
            System.out.println("after park");
        }
    }

}
