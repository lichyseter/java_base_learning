package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰字段的注解定义
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface FieldAnnotation {
    /**
     * 描述
     *
     * @return 值
     */
    String description();

}
