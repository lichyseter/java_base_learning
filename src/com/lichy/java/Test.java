package com.lichy.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class Test {
    public static void main(String[] args) {
        List<String> arrs = new ArrayList<>();
        arrs.add("a");
        arrs.add("b");
        arrs.add("c");
        arrs.add("d");
        arrs.add("e");


        arrs.add("f");
        arrs.add("h");
        arrs.add("i");

        arrs.add("j");
        Spliterator<String> a =  arrs.spliterator();
        //此时结果：a:0-9（index-fence）
        Spliterator<String> b = a.trySplit();
        //此时结果：b:4-9,a:0-4
        Spliterator<String> c = a.trySplit();
        //此时结果：c:4-6,b:4-9,a:6-9
        Spliterator<String> d = a.trySplit();
        //此时结果：d:6-7,c:4-6,b:4-9,a:7-9
    }
}
