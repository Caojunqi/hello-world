package org.chapter2.exercise1;

public class Main {
    public static void main(String[] args) {
        Function<Integer> triple = new Function<Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg * 3;
            }
        };

        Function<Integer> square = new Function<Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg * arg;
            }
        };

         System.out.println("结果1：" + triple.compose(square).apply(3));

        // 测试Java自带Function
        java.util.function.Function<Integer,Integer> javaFun1 = new java.util.function.Function<>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 3;
            }
        };

        java.util.function.Function<Integer,Integer> javaFun2 = new java.util.function.Function<>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        };

        javaFun1.compose(javaFun2).apply(3);

        System.out.println("结果2：" + compose(triple, square, 3));
    }

    public static <T> T compose(Function<T> fun1, Function<T> fun2, T arg) {
        return fun1.apply(fun2.apply(arg));
    }
}
