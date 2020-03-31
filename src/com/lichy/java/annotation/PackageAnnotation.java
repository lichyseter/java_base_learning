package com.lichy.java.annotation;

import java.lang.annotation.*;

/**
 * 修饰构造函数的注解定义,用于package-info.java
 *
 * @author lichy
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.PACKAGE)
public @interface PackageAnnotation {
    /**
     * 描述
     *
     * @return 值
     */
    String description();

}
