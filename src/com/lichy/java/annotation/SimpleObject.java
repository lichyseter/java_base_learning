package com.lichy.java.annotation;

import java.util.Arrays;

/**
 * 一个简单的java类对象
 *
 * @author lichy
 */
@ClassAnnotation(
        value = "测试", name = "test", newNames = {"test"})
public class SimpleObject<@TypeParameterAnnotation(description = "type_parameter_description") T> implements SimpleInterface {
    /**
     * 值
     */
    private String value;

    @ConstructorAnnotation(description = "constructor_description")
    public SimpleObject() {
    }

    /**
     * 名称
     */
    @FieldAnnotation(description = "test_description")
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

    /**
     * 输出信息
     *
     * @param message 信息
     */
    @MethodAnnotation(description = "method_description")
    public @TypeUseAnnotation(description = "type_use_description") String printMessage(@ParameterAnnotation(description = "parameter_description") String message) {
        if (message != null) {
            System.out.println((T) message);
        }
        return "输出成功";
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
