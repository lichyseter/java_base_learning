package com.lichy.java.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 测试CompleteFuture
 *
 * @author lichy
 */
public class CompleteFutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("1等待两秒钟");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> {
            System.out.println("1执行完成");
        });
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("2执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finished";
        }).thenAccept(n -> {
            System.out.println("2执行的结果为" + n);
        });
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
                System.out.println("3执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1 / 0;
        }).exceptionally(e -> {
            System.out.println("3错误信息" + e.getMessage());
            return 0;
        }).thenAccept(n -> {
            System.out.println("3结果" + n);
        });
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("4执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1 / 0;
        }).whenComplete((i, e) -> {
            if (e != null) {
                System.out.println("4错误信息" + e.getMessage());
            } else {
                System.out.println("4结果" + i);
            }
        });

        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
                System.out.println("5执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 12;
        });
//        completableFuture.completeExceptionally(new Exception("5人工错误"));
//        try {
//            System.out.println(completableFuture.get());
//        } catch (Exception e) {
//            System.out.println("5错误详情" + e.getMessage());
//        }
        System.out.println(completableFuture.get());
    }
}
