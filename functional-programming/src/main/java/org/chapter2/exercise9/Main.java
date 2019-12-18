package org.chapter2.exercise9;

public class Main {
    public static void main(String[] args) {

    }

    public static <A, B, C, D> String func(A a, B b, C c, D d) {
        return curryFun().apply(a).apply(b).apply(c).apply(d);
        // return String.format("%s,%s,%s,%s", a, b, c, d);
    }

    public static <A, B, C, D> Function<A, Function<B, Function<C, Function<D, String>>>> curryFun() {
        return a -> b -> c -> d -> String.format("%s,%s,%s,%s", a, b, c, d);
    }
}
