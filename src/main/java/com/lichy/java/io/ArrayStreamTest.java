package com.lichy.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 数组流样例。
 * 为什么不用byte数组?1因为提供了一些方法。2.因为有些接口只接收流。
 * @author lichy
 *
 */
public class ArrayStreamTest {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write("abc".getBytes());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        byte[] bytes = new byte[1024];
        byteArrayInputStream.read(bytes);
        System.out.println(new String(bytes));
    }
}
