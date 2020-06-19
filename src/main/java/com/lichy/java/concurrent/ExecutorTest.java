package com.lichy.java.concurrent;

import java.util.concurrent.*;

public class ExecutorTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<?> f1 = executorService.submit(() -> {
            System.out.println("测试线程池开始1");
        });
        final Future<?> f2 = executorService.submit(() -> {
            System.out.println("测试线程池开始2");
        });
        f1.get();
        f2.get();
        System.out.println("执行完成");
        executorService.shutdown();
        System.out.println("---------------");
        ExecutorService customExecutorService = new ThreadPoolExecutor(0, 1,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        final Future<?> f3 = customExecutorService.submit(() -> {
            System.out.println("自定义线程池开始1");
        });
        final Future<?> f4 = customExecutorService.submit(() -> {
            System.out.println("自定义线程池开始2");
        });
        final Future<?> f5 = customExecutorService.submit(() -> {
            System.out.println("自定义线程池开始3");
        });
        f3.get();
        f4.get();
        f5.get();
        customExecutorService.shutdown();
    }
}
