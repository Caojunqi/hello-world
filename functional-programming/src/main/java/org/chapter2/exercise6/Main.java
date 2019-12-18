package org.chapter2.exercise6;

import java.util.List;
import java.util.function.DoubleUnaryOperator;

public class Main {
    public static void main(String[] args) {
        Function<Double> f = x -> Math.PI / 2 - x;
        Function<Double> sin = Math::sin;
        // Double cos = compose(f, sin).apply(2.0);
        // Double cos = compose(x -> Math.PI / 2 - x, Math::sin).apply(2.0);
        Double cos = compose(x -> Math.PI / 2 - x, (Function<Double>) y -> Math.sin(y)).apply(2.0);
        System.out.println("结果：" + cos);

        // double taxRate = 0.09;
        // Function<Double> addTax = price -> price + price * taxRate;
    }

    public static <T> Function<T> compose(Function<T> fun1, Function<T> fun2) {
        return x -> fun2.apply(fun1.apply(x));
    }

}
