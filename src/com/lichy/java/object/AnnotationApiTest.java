package com.lichy.java.object;

import com.lichy.java.entity.ClassAnnotation;
import com.lichy.java.entity.SimpleObject;

/**
 * 测试java.lang.Object类相关方法
 *
 * @author lichy
 */
public class AnnotationApiTest {


    public static void main(String[] args) {
        // 获取类注解的值
        ClassAnnotation classAnnotation = SimpleObject.class.getAnnotation(ClassAnnotation.class);
        System.out.println(classAnnotation.toString());

    }
}
