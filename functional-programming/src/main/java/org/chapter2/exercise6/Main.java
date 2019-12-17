package org.chapter2.exercise6;

public class Main {
    public static void main(String[] args) {
        Function<Double> f = x -> Math.PI / 2 - x;
        Function<Double> sin = Math::sin;
        // Double cos = compose(f, sin).apply(2.0);
        Double cos = compose(x -> Math.PI / 2 - x, Math::sin).apply(2.0);
        System.out.println("结果：" + cos);
    }

    public static <T> Function<T> compose(Function<T> fun1, Function<T> fun2) {
        return x -> fun2.apply(fun1.apply(x));
    }
}
