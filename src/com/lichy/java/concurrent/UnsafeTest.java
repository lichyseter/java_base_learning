package com.lichy.java.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe类样例
 *
 * @author lichy
 */
public class UnsafeTest {


    public static void main(String[] args) throws Exception {
        // 通过反射实例化Unsafe
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // 实例化Player
        Player player = (Player) unsafe.allocateInstance(Player.class);
        player.setAge(18);
        player.setName("lichy");
        long ageOffset = -1;
        long nameOffset = -1;
        for (Field field : Player.class.getDeclaredFields()) {
            System.out.println(field.getName() + ":对应的内存偏移地址" + unsafe.objectFieldOffset(field));
            if ("name".equals(field.getName())) {
                nameOffset = unsafe.objectFieldOffset(field);
            } else if ("age".equals(field.getName())) {
                ageOffset = unsafe.objectFieldOffset(field);
            }
        }
        System.out.println("-------------------");
        // unsafe.compareAndSwapInt(arg0, arg1, arg2, arg3)
        // arg0, arg1, arg2, arg3 分别是目标对象实例，目标对象属性偏移量，当前预期值，要设的值

        // 修改内存偏移地址为12的值（age）,返回true,说明通过内存偏移地址修改age的值成功
        System.out.println(unsafe.compareAndSwapInt(player, ageOffset, 18, 20));
        System.out.println("age修改后的值：" + player.getAge());
        System.out.println("-------------------");
        // 修改内存偏移地址为16的值，volatile修饰，修改能立马对其他线程可见
        System.out.println(unsafe.compareAndSwapObject(player, nameOffset, "lichy", "test"));
        System.out.println("name修改后的值：" + player.getName());
    }
}

class Player {
    private int age;
    private String name;

    private Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
