package org.chapter2.exercise11;

import org.chapter2.exercise10.Tuple;

public class Main {
    public static void main(String[] args) {
        Function<String, Function<Integer, Double>> oldCurryFun = x -> y -> 2.0 * y;
        System.out.println(reverseParam(oldCurryFun).apply(1).apply("哈哈"));
    }

    public static <T, U, V> Function<U, Function<T, V>> reverseParam(Function<T, Function<U, V>> oldCurryFun) {
        return y -> x -> oldCurryFun.apply(x).apply(y);
    }

}
