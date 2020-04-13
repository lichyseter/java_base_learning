package com.lichy.java.concurrent;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap测试类
 * @author lichy
 */
public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 30; i++) {
            concurrentSkipListMap.put(i, i);
        }
        concurrentSkipListMap.put(36, 36);
        concurrentSkipListMap.put(38, 38);
        concurrentSkipListMap.put(40, 40);
        System.out.println("大于等于37的key" + concurrentSkipListMap.ceilingKey(37));
        System.out.println("第一个的key" + concurrentSkipListMap.firstKey());
        System.out.println("小于等于37的key" + concurrentSkipListMap.floorKey(37));
    }
}
