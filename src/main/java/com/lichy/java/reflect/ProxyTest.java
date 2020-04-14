package com.lichy.java.reflect;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java动态代理样例类
 */
public class ProxyTest implements InvocationHandler {

    Entity entity;

    public ProxyTest(Entity entity) {
        this.entity = entity;
    }

    public static void main(String[] args) {
        Entity entity = new Entity();
        MyInterface myInterface = (MyInterface) Proxy.newProxyInstance(entity.getClass().getClassLoader(), entity.getClass().getInterfaces(), new ProxyTest(entity));
        System.out.println(myInterface.getInterfaceValue(1));
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用之前");
        System.out.println("参数" + args[0]);
        method.invoke(entity, args);
        System.out.println("调用之后");
        return "成功";
    }
}
