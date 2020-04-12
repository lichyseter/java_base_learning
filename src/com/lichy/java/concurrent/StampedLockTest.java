package com.lichy.java.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 测试stamped lock
 * ReentrantReadWriteLock实现了读写分离，想要获取读锁就必须确保当前没有其他任何读写锁了，
 * 但是一旦读操作比较多的时候，想要获取写锁就变得比较困难了，因为当前有可能会一直存在读锁。而无法获得写锁
 * <p>
 * 可以用乐观读来提高效率
 *
 * @author lichy
 */
public class StampedLockTest {
    /**
     * 锁对象
     */
    private static StampedLock lock = new StampedLock();
    /**
     * 数据
     */
    private static List<String> data = new ArrayList<>();

    /**
     * 写数据
     */
    public static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            data.add("写入的时间戳：" + stamped);
            System.out.println("写入的时间戳：" + stamped);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }

    /**
     * 读数据
     */
    private static void read() {
        long stamped = -1;
        try {
            stamped = lock.readLock();
            for (String name : data) {
                System.out.println("读取数据：" + name);
            }
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }
    }

    /**
     * 乐观读
     */
    private static void optimisticRead() {
        //尝试去拿一个乐观锁
        long stamped = lock.tryOptimisticRead();
        //如果没有线程修改，我们再去获取一个读锁
        if (lock.validate(stamped)) {
            try {
                stamped = lock.readLock();
                for (String name : data) {
                    System.out.println("读的数据是：" + name);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamped);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable readTask = () -> {
            while (true) {
               optimisticRead();
  //              read();
            }
        };
        Runnable writeTask = () -> {
            while (true) {
                write();
            }
        };
        for (int i = 0; i < 9; i++) {
            executor.submit(readTask);
        }
        executor.submit(writeTask);
    }
}
