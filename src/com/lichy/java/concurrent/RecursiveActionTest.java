package com.lichy.java.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RecursiveAction样例
 *
 * @author lichy
 */
public class RecursiveActionTest {
    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new MyRecursiveAction(0, 10));
        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(sum);
    }

    static class MyRecursiveAction extends RecursiveAction {

        int start;
        int end;

        MyRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start == 0) {
                sum.getAndAdd(start);
            } else if (end - start == 1) {
                sum.getAndAdd(start + end);
            } else {
                int middle = (start + end) / 2;
                (new MyRecursiveAction(start, middle)).fork();
                (new MyRecursiveAction(middle + 1, end)).fork();
            }
        }
    }


}
