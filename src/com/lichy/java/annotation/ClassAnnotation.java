package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰类的注解定义
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface ClassAnnotation {
    /**
     * 值
     *
     * @return 值
     */
    String value();

    /**
     * 名称
     *
     * @return 名称
     */
    String name();

    /**
     * 年龄
     *
     * @return 年龄
     */
    int age() default 0;

    /**
     * 字符数组
     *
     * @return 字符数组
     */
    String[] newNames();

}
