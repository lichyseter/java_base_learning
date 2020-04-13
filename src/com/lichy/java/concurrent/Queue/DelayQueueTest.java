package com.lichy.java.concurrent.Queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue样例
 *
 * @author lichy
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayElement> delayQueue = new DelayQueue<>();
        long time = System.currentTimeMillis();
        delayQueue.add(new DelayElement(1, time + 1000 * 6));
        delayQueue.add(new DelayElement(2, time + 1000 * 5));
        delayQueue.add(new DelayElement(3, time + 1000 * 4));
        delayQueue.add(new DelayElement(4, time + 1000 * 3));
        delayQueue.add(new DelayElement(5, time + 1000 * 2));
        while (!delayQueue.isEmpty()) {
            System.out.println(delayQueue.take().value);
        }
    }

    static class DelayElement implements Delayed {

        int value;
        long time;
        //定义时间工具类
        private TimeUnit timeUnit = TimeUnit.SECONDS;

        DelayElement(int value, long time) {
            this.value = value;
            this.time = time;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            long delay = getDelay(timeUnit) - o.getDelay(timeUnit);
            if (delay > 0) {
                return 1;
            }
            return delay == 0 ? 0 : -1;
        }
    }
}
