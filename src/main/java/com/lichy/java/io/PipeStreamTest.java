package com.lichy.java.io;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 管道流样例类
 * 管道流主要是为运行在同一个JVM中的两个线程提供通信能力。不同的JVM中的线程是不能进行通信的
 *
 * @author lichy
 */
public class PipeStreamTest {

    public static void main(String[] args) throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        pipedOutputStream.connect(pipedInputStream);
        new ReadThread(pipedInputStream).start();
        new WriteThread(pipedOutputStream).start();
    }

    static class ReadThread extends Thread {
        PipedInputStream pipedInputStream;

        public ReadThread(PipedInputStream pipedInputStream) {
            this.pipedInputStream = pipedInputStream;
        }

        @Override
        public void run() {
            try {
                byte[] bytes = new byte[1024];
                pipedInputStream.read(bytes);
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class WriteThread extends Thread {
        PipedOutputStream pipedOutputStream;

        public WriteThread(PipedOutputStream pipedOutputStream) {
            this.pipedOutputStream = pipedOutputStream;
        }

        @Override
        public void run() {
            try {
                pipedOutputStream.write("abc".getBytes());
                pipedOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
