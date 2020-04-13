package com.lichy.java.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Atomic样例
 *
 * @author lichy
 */
public class AtomicTest {

    private volatile int intTest = 1;

    public static void main(String[] args) {
        // fieldUpdater相关
        AtomicIntegerFieldUpdater atomicInteger = AtomicIntegerFieldUpdater.newUpdater(AtomicTest.class, "intTest");
        System.out.println(atomicInteger.incrementAndGet(new AtomicTest()));
        // atomicReference相关
        AtomicReference atomicReference = new AtomicReference();
        AtomicTest atomicTest1 = new AtomicTest();
        AtomicTest atomicTest2 = new AtomicTest();
        atomicReference.set(atomicTest1);
        System.out.println(atomicTest1.equals(atomicTest2));
        System.out.println(atomicTest1.equals(atomicReference.get()));
        atomicReference.compareAndSet(atomicTest1, atomicTest2);
        System.out.println(atomicTest2.equals(atomicReference.get()));
        // AtomicStampedReference 相关
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(10, 1);
        Thread t = new Thread(() -> {
            int stamp = 1;
            atomicStampedReference.compareAndSet(10, 11, stamp, stamp + 1);
            stamp++;
            atomicStampedReference.compareAndSet(11, 10, stamp, stamp + 1);
        });
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = 1;
            while (!atomicStampedReference.compareAndSet(10, 11, stamp, stamp + 1)) {
                System.out.println("时间戳加1");
                stamp++;
            }
        });
        t.start();
        t2.start();
    }

}
