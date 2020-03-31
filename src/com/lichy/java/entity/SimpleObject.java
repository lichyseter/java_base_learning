package com.lichy.java.entity;

import java.util.Arrays;

/**
 * 一个简单的java类对象
 *
 * @author lichy
 */
@ClassAnnotation(
        value = "测试", name = "test", newNames = {"test"})
public class SimpleObject implements SimpleInterface {
    /**
     * 值
     */
    private String value;
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 新名称
     */
    private String[] newNames;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SimpleObject{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", newNames=" + Arrays.toString(newNames) +
                '}';
    }
}
