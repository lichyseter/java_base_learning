package com.lichy.java.io;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件流相关样例类
 * <p>
 * 对于字符流的reader和writer，无法指定编码，所以一般不用
 *
 * @author lichy
 */
public class FileStreamTest {

    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        String classPath = URLDecoder.decode(FileStreamTest.class.getResource("/").toString(), StandardCharsets.UTF_8.name());
        if (classPath.toLowerCase().startsWith("file:")) {
            classPath = classPath.substring(5);
        }
        inputStreamRead(classPath);
        // 如果有中文，或者说需要考虑编码，不能使用filereader.必须读字节流，再转换。
        bufferedStreamReader(classPath);
        outputStreamWriter(classPath);
        bufferedOutputStreamWriter(classPath);
    }

    /**
     * 通过字节流写文件
     *
     * @param classPath 文件
     */
    private static void bufferedOutputStreamWriter(String classPath) {
        File file = new File(classPath + "FileOutput.txt");
        try (BufferedWriter stream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            stream.write("测试buffer写入");
            stream.flush();
            System.out.println("测试buffer写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过字节流写文件
     *
     * @param classPath 类路径
     */
    private static void outputStreamWriter(String classPath) throws FileNotFoundException {
        File file = new File(classPath + "FileOutput.txt");
        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write("测试".getBytes(StandardCharsets.UTF_8));
            stream.flush();
            System.out.println("写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过字符流读取文件内容
     *
     * @param classPath 类路径
     */
    private static void bufferedStreamReader(String classPath) {
        File file = new File(classPath + "FileInput.txt");
        if (file.exists() && file.isFile()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过字节流读取文件内容
     *
     * @param classPath 类路径
     */
    private static void inputStreamRead(String classPath) {
        File file = new File(classPath + "FileInput.txt");
        if (file.exists() && file.isFile()) {
            try (FileInputStream stream = new FileInputStream(file)) {
                byte[] bytes = new byte[1000];
                stream.read(bytes);
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
