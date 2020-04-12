package com.lichy.java.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试ScheduledThreadPoolExecutor功能
 *
 * @author lichy
 */
public class ScheduledThreadPoolExecutorTest {


    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
            System.out.println("固定频率当前时间" + System.currentTimeMillis());
        }, 0, 3, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
            System.out.println("固定间隔当前时间" + System.currentTimeMillis());
        }, 0, 3, TimeUnit.SECONDS);
    }
}
