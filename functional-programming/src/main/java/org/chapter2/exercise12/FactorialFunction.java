package org.chapter2.exercise12;

public class FactorialFunction implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer arg) {
        if (arg <= 1) {
            return 1;
        }
        return arg * apply(arg - 1);
    }
}
