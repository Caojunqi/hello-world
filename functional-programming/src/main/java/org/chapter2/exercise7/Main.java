package org.chapter2.exercise7;

public class Main {
    public static void main(String[] args) {
        Function<Double, Function<Double, String>> curryFun = x -> y -> "das";
    }

    public static <T, U, V> Function<U, V> partial(T param, Function<T, Function<U, V>> fun) {
        return fun.apply(param);
    }
}
