package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰方法的注解定义。用于修饰方法
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
@AnnotationTypeAnnotation(description = "annotation_description")
public @interface MethodAnnotation {
    /**
     * 描述
     *
     * @return 值
     */
    String description();

}
