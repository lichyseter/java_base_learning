package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰类型参数的注解定义.用于泛型。
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE_PARAMETER)
public @interface TypeParameterAnnotation {
    /**
     * 描述
     *
     * @return 值
     */
    String description();

}
