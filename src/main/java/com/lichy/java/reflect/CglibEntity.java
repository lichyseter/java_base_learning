package com.lichy.java.reflect;

public class CglibEntity {

    public String getInterfaceValue(Integer i) {
        System.out.println("执行getInterfaceValue(" + i + ")");
        return i + " get";
    }
}
