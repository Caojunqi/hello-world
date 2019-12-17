package org;

/**
 * 测试代码
 */
public class Test {
    public static void main(String[] args) {
        Integer arg = 10;
        multi(1, arg);
        System.out.println("arg:" + arg);
    }

    public static int multi(int a, Integer b) {
        a = 2;
        b = 3;
        return a * b;
    }
}
