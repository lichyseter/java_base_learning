package com.lichy.java.enumt;

/**
 * 测试枚举类相关测试方法
 *
 * @author lichy
 */
public enum EnumTest {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");
    //中文描述
    private String desc;

    /**
     * 私有构造,防止被外部调用
     *
     * @param desc 描述
     */
    private EnumTest(String desc) {
        this.desc = desc;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     *
     * @return 描述
     */
    public String getDesc() {
        return desc;
    }

    public static void main(String[] args) {
        System.out.println("枚举类型比较的是ordinal顺序，所以星期一小于星期二，compareTo小于0：" + EnumTest.MONDAY.compareTo(EnumTest.TUESDAY));
        System.out.println("枚举类型equals比较的是否同一个引用,星期一不equals星期二：" + EnumTest.MONDAY.equals(EnumTest.TUESDAY));
        System.out.println("枚举类型也是一个class：" + EnumTest.MONDAY.getDeclaringClass());
        System.out.println("枚举类型name是枚举的名称：" + EnumTest.MONDAY.name());
        System.out.println("枚举类型ordinal是顺序：" + EnumTest.MONDAY.ordinal());
        System.out.println("枚举类型默认toString是name：" + EnumTest.MONDAY.toString());
        System.out.println("枚举类型valueOf返回枚举实例：" + EnumTest.valueOf(EnumTest.class,"MONDAY").getDesc());

    }
}
