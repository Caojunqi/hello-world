package org.chapter3.exercise2;

public class Tuple<T, R> {
    private T t;
    private R r;

    private Tuple() {
        // 私有化构造器
    }

    public Tuple(T t, R r) {
        this.t = t;
        this.r = r;
    }

    public T getT() {
        return t;
    }

    public R getR() {
        return r;
    }
}
