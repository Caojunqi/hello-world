package org.chapter3.exercise5;

import java.util.ArrayList;
import java.util.List;

public class ListFolder {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(listFold(list, 0, x -> y -> x + y));
    }

    public static int listFold(List<Integer> list, int initValue, Function<Integer, Function<Integer, Integer>> foldFunc) {
        if (list == null || list.isEmpty()) {
            return initValue;
        }
        for (int element : list) {
            initValue = foldFunc.apply(initValue).apply(element);
        }
        return initValue;
    }
}
