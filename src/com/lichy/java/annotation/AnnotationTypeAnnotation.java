package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰注解函数的注解定义。用于修饰注解。
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.ANNOTATION_TYPE)
public @interface AnnotationTypeAnnotation {
    /**
     * 描述
     *
     * @return 值
     */
    String description();

}
