package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰类型参数的注解定义.用于使用类型。
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE_USE)
public @interface TypeUseAnnotation {
    /**
     * 描述
     *
     * @return 值
     */
    String description();

}
