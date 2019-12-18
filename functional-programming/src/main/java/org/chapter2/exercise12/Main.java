package org.chapter2.exercise12;

public class Main {

    // 1
    private Function<Integer, Integer> factorialFun1;

    {
        factorialFun1 = n -> n <= 1 ? 1 : n * factorialFun1.apply(n - 1);
    }

    // 2
    private static Function<Integer, Integer> factorialFun2;

    {
        factorialFun2 = n -> n <= 1 ? 1 : n * factorialFun2.apply(n - 1);
    }

    // 3
    private final Function<Integer,Integer> factorialFun3 = n -> n <= 1 ? 1 : n * this.factorialFun3.apply(n - 1);

    // 4
    private final static Function<Integer,Integer> factorialFun4 = n -> n <= 1 ? 1 : n * Main.factorialFun4.apply(n - 1);


    public static void main(String[] args) {
        System.out.println(factorial(2));

        Function<Integer, Integer> factorialFunction = new FactorialFunction();
        System.out.println(factorialFunction.apply(4));

        Main main = new Main();
        System.out.println(main.factorialFun1.apply(5));

        System.out.println(factorialFun2.apply(6));

        System.out.println(main.factorialFun3.apply(7));

        System.out.println(factorialFun4.apply(8));
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
