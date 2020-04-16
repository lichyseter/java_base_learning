package com.lichy.java.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 时间类样例
 *
 * @author lichy
 */
public class DateTest {

    public static void main(String[] args) throws InterruptedException {
        testClock();
        testInstant();
        testLocalDateTime();
        testDuration();
    }

    /**
     * 测试Duration
     */
    private static void testDuration() {
        //表示两个瞬时时间的时间段
        Duration d1 = Duration.between(Instant.ofEpochMilli(System.currentTimeMillis() - 12323123), Instant.now());
        //得到相应的时差
        System.out.println(d1.toDays());
        System.out.println(d1.toHours());
        System.out.println(d1.toMinutes());
        System.out.println(d1.toMillis());
        System.out.println(d1.toNanos());
    }

    /**
     * LocalDateTime样例
     */
    private static void testLocalDateTime() {
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.of(2012, 10, 1, 10, 10, 10));
        System.out.println(LocalDateTime.parse("1992-10-10 09:09:09", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     *
     */
    private static void testInstant() {
        Instant instant1 = Instant.now();
        // 和标准开始时间的差值（到秒）
        System.out.println("和标准开始时间的差值（到秒）" + instant1.getEpochSecond());
        // 和标准开始时间的差值（到秒）
        System.out.println("和标准开始时间的差值（到毫秒）" + instant1.toEpochMilli());
        System.out.println("默认" + instant1.toString());
        System.out.println("获取时间的一部分" + instant1.get(ChronoField.NANO_OF_SECOND));
    }

    /**
     * Lock相关
     *
     * @throws InterruptedException
     */
    private static void testClock() throws InterruptedException {
        // 当前系统时区
        Clock c1 = Clock.systemUTC();
        System.out.println("默认时区" + c1.getZone());
        System.out.println("默认时区时间" + c1.millis());
        System.out.println("默认时区时间" + c1.instant().toString());
        Clock c2 = Clock.fixed(Instant.now(), ZoneId.of("Europe/Paris"));
        System.out.println("固定时钟起" + c2.millis());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("固定时钟终" + c2.millis());
        Clock c3 = Clock.offset(c1, Duration.ofSeconds(2));
        System.out.println("offset差" + (c3.millis() - c1.millis()));
        Clock c4 = Clock.tick(c1, Duration.ofMillis(200));
        IntStream.rangeClosed(0, 9).forEach(n ->
        {
            try {
                TimeUnit
                        .MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("tick时钟" + c4.instant().toString());
        });
    }
}
