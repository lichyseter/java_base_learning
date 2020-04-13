package com.lichy.java.concurrent.Queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue测试类
 * @author lichy
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Person> queue = new PriorityBlockingQueue<>();
        queue.add(new Person(1, 1));
        queue.add(new Person(3, 3));
        queue.add(new Person(2, 2));
        queue.add(new Person(2, 3));
        for (int i = 0; i < 4; i++) {
            System.out.println(queue.take());
        }
    }


    static class Person implements Comparable<Person> {

        int age;

        int height;

        Person(int age, int height) {
            this.age = age;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", height=" + height +
                    '}';
        }

        @Override
        public int compareTo(Person o) {
            return this.age == o.age ? this.height - o.height : this.age - o.age;
        }
    }
}
