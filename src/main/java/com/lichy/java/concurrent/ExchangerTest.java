package com.lichy.java.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        for (int i = 0; i < 5; i++) {
            final int count = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1 + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("我是线程" + count + "收到来自其他线程的数据" + exchanger.exchange("来自" + count + "线程的数据", 2, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            ).start();
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
