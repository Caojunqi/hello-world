package org.chapter2.exercise8;

public class Main {
    public static void main(String[] args) {
        Function<Double, Function<Double, String>> curryFun = x -> y -> "das";
    }

    public static <T, U, V> Function<T, V> partial(U param, Function<T, Function<U, V>> fun) {
        return (T arg)->fun.apply(arg).apply(param);
    }
}
