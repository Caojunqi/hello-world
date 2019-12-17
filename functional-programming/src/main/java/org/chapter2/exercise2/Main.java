package org.chapter2.exercise2;

public class Main {

    public static void main(String[] args) {
        Function<Integer> triple = arg -> arg * 3;
        Function<Integer> square = arg -> arg * arg;

        System.out.println("结果：" + compose(triple, square).apply(2));
    }

    public static <T> Function<T> compose(Function<T> fun1, Function<T> fun2) {
        return arg -> fun1.apply(fun2.apply(arg));
    }
}
