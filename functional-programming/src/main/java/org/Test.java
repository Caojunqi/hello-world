package org;

/**
 * 测试代码
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(((""+'a') + 'b') + 'c');
        System.out.println('a' + ('b' + ('c' + "")));
    }

    public static int multi(int a, Integer b) {
        a = 2;
        b = 3;
        return a * b;
    }
}
