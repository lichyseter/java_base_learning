package com.lichy.java.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream类样例
 *
 * @author lichy
 */
public class StreamTest {

    public static void main(String[] args) {
        createStream();
        operations();
    }


    /**
     * 生成Stream的方法
     */
    private static void createStream() {
        //  Arrays.stream
        String[] strings = {"a", "b", "c"};
        Arrays.stream(strings).forEach(System.out::println);
        // Stream.of
        Stream.of(1, 2, 3).forEach(System.out::println);
        List<String> list = Arrays.asList(strings);
        //  list.stream
        list.stream().forEach(System.out::println);
        // Stream.iterate
        System.out.println(Stream.iterate(1, n -> n * 2).findAny().isPresent());
        // Stream.generate
        System.out.println(Stream.generate(() -> 2).anyMatch(n -> n.equals(2)));
    }

    /**
     * 各种option的样例
     */
    private static void operations() {
        System.out.println("-----------------");
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("张三", "大连", 24));
        persons.add(new Person("李四", "沈阳", 23));
        persons.add(new Person("王五", "浙江", 25));
        persons.stream().filter(n -> n.age > 23).forEach(System.out::println);
        System.out.println("-----------------");
        persons.stream().map(n -> n.name).forEach(System.out::println);
        System.out.println("-----------------");
        // 流转流
        persons.stream().flatMap(n -> Stream.of(n.name, n.place)).forEach(System.out::println);
        System.out.println("-----------------");
        persons.stream().limit(1).forEach(System.out::println);
        System.out.println("-----------------");
        persons.stream().sorted(Comparator.comparingInt(a -> a.age)).forEach(System.out::println);
        System.out.println("-----------------");
        persons.stream().map(n -> n.age).distinct().forEach(System.out::println);
        System.out.println("-----------------");
        persons.stream().skip(1).forEach(System.out::println);
        System.out.println("-----------------");
        System.out.println("终端操作-----------------");
        System.out.println(persons.stream().map(n -> n.age).collect(Collectors.toList()));
        System.out.println("-----------------");
        System.out.println(persons.stream().map(n -> n.age).reduce((a, b) -> a + b).orElse(20));
        System.out.println(persons.stream().map(n -> n.age).reduce(1, (a, b) -> a + b));
        //      System.out.println(persons.parallelStream().map(n -> n.age).reduce(1, (a, b) -> a + b, (c, d) -> c + d));
        System.out.println("-----------------");
        System.out.println(persons.stream().min(Comparator.comparingInt(a -> a.age)).orElse(new Person("defalut", "defalut", 0)));
        System.out.println("-----------------");
        System.out.println(persons.stream().max(Comparator.comparingInt(a -> a.age)).orElse(new Person("defalut", "defalut", 0)));
        System.out.println("-----------------");
        System.out.println(persons.stream().anyMatch(n -> n.age > 0));
        System.out.println("-----------------");
        System.out.println(persons.stream().findAny());
        System.out.println(persons.stream().findFirst());
        System.out.println(persons.stream().anyMatch(n -> n.age > 0));
        System.out.println(persons.stream().noneMatch(n -> n.age > 0));
        System.out.println(persons.stream().allMatch(n -> n.age > 0));
    }


    static class Person {
        String name;
        String place;
        int age;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", place='" + place + '\'' +
                    ", age=" + age +
                    '}';
        }

        Person(String name, String place, int age) {
            this.name = name;
            this.place = place;
            this.age = age;
        }
    }
}
