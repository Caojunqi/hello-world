package org.chapter2.exercise5;

public class Main {
    public static void main(String[] args) {
        Function<Double, String> before = x -> String.valueOf(x);
        Function<String, Integer> after = x -> (int) Math.round(Double.valueOf(x));
        Function<Function<String, Integer>, Function<Function<Double, String>, Function<Double, Integer>>> composeFun = composeFun();
        System.out.println("结果：" + composeFun.apply(after).apply(before).apply(2.5));
    }

    public static <T, U, V> Function<Function<V, T>, Function<Function<U, V>, Function<U, T>>> composeFun() {
        return x -> y -> z -> x.apply(y.apply(z));
    }
}
