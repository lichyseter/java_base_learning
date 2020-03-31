package com.lichy.java.annotation;

/**
 * 测试java.lang.Object类相关方法
 *
 * @author lichy
 */
public class AnnotationApiTest {


    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        // 获取类注解的值
        ClassAnnotation classAnnotation = SimpleObject.class.getAnnotation(ClassAnnotation.class);
        System.out.println(classAnnotation.toString());
        // 获取字段注解信息
        FieldAnnotation fieldAnnotation = SimpleObject.class.getDeclaredField("name").getAnnotation(FieldAnnotation.class);
        System.out.println(fieldAnnotation.toString());
        // 获取方法注解信息
        MethodAnnotation methodAnnotation = SimpleObject.class.getDeclaredMethod("printMessage",String.class).getAnnotation(MethodAnnotation.class);
        System.out.println(methodAnnotation.toString());
        // 获取方法参数的注解(不考虑空指针问题)
        ParameterAnnotation parameterAnnotation = SimpleObject.class.getDeclaredMethod("printMessage",String.class).getParameters()[0].getAnnotation(ParameterAnnotation.class);
        System.out.println(parameterAnnotation.toString());
        // 获取注解的注解信息
        AnnotationTypeAnnotation annotationTypeAnnotation = MethodAnnotation.class.getAnnotation(AnnotationTypeAnnotation.class);
        System.out.println(annotationTypeAnnotation.toString());
        // 获取package的注解信息
        Package packageInfo = Package.getPackage("com.lichy.java.annotation");
        PackageAnnotation packageAnnotation = SimpleObject.class.getPackage().getAnnotation(PackageAnnotation.class);
        System.out.println(packageAnnotation.toString());
        // 获取类注解的值
        SimpleObject<String> simpleObject = new SimpleObject<String>();
        TypeParameterAnnotation typeParameterAnnotation = simpleObject.getClass().getTypeParameters()[0].getAnnotation(TypeParameterAnnotation.class);
        System.out.println(typeParameterAnnotation.toString());
        // 获取类型上的注解值
        TypeUseAnnotation typeUseAnnotation = SimpleObject.class.getDeclaredMethod("printMessage",String.class).getAnnotatedReturnType().getAnnotation(TypeUseAnnotation.class);
        System.out.println(typeUseAnnotation.toString());

    }
}
