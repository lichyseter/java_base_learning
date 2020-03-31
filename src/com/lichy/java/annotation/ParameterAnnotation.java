package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰参数的注解定义。用于修饰参数
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.PARAMETER)
public @interface ParameterAnnotation {
    /**
     * 描述
     *
     * @return 值
     */
    String description();

}
