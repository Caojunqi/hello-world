package org.chapter2.exercise3;

public class Main {
    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> fun1 = x -> (arg -> (x + arg));
        System.out.println("结果：" + fun1.apply(3).apply(4));
    }
}
