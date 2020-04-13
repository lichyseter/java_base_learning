package com.lichy.java.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTask样例类
 */
public class RecursiveTaskTest {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new MyRecursiveTask(0, 10));
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyRecursiveTask extends RecursiveTask<Integer> {
        int start;
        int end;

        MyRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start == 0) {
                return start;
            } else if (end - start == 1) {
                return start + end;
            } else {
                int middle = (start + end) / 2;
                MyRecursiveTask leftTask = new MyRecursiveTask(start, middle);
                MyRecursiveTask rightTask = new MyRecursiveTask(middle + 1, end);
                leftTask.fork();
                rightTask.fork();
                return leftTask.join() + rightTask.join();
            }
        }
    }
}
