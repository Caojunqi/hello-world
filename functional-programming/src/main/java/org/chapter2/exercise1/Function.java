package org.chapter2.exercise1;

public interface Function<T> {
    T apply(T arg);

    default Function<T> compose(Function<T> before) {
        return arg -> apply(before.apply(arg));
    }
}
