package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰构造函数的注解定义。用于修饰构造函数
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.CONSTRUCTOR)
public @interface ConstructorAnnotation {
    /**
     * 描述
     *
     * @return 值
     */
    String description();

}
