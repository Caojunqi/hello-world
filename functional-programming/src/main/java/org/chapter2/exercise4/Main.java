package org.chapter2.exercise4;

public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;
        Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>> composeFun = x -> y -> z -> x.apply(y.apply(z));
        System.out.println("结果：" + composeFun.apply(triple).apply(square).apply(2));
    }
}
