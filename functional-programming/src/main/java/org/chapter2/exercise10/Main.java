package org.chapter2.exercise10;

public class Main {
    public static void main(String[] args) {

    }

    public static <A, B, C> Function<A, Function<B, C>> curry(Function<Tuple<A, B>, C> function) {
        return a -> b -> function.apply(new Tuple<>(a, b));
    }
}
