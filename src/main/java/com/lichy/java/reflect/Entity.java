package com.lichy.java.reflect;

public class Entity implements MyInterface {
    @Override
    public String getInterfaceValue(Integer i) {
        System.out.println("执行getInterfaceValue(" + i + ")");
        return i + " get";
    }
}
