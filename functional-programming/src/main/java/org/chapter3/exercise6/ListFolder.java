package org.chapter3.exercise6;

import java.util.ArrayList;
import java.util.List;

public class ListFolder {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(listFold(list, "0", x -> y -> addSI(x, y)));
    }

    public static String listFold(List<Integer> list, String identity, Function<String, Function<Integer, String>> foldFunc) {
        if (list == null || list.isEmpty()) {
            return identity;
        }
        String result = identity;
        for (int element : list) {
            result = foldFunc.apply(result).apply(element);
        }
        return result;
    }

    public static String addSI(String s, Integer i) {
        return "(" + s + "+" + i + ")";
    }

    public static <T, U> U listFoldLeft(List<T> list, U identity, Function<U, Function<T, U>> foldFunc) {
        if (list == null || list.isEmpty()) {
            return identity;
        }
        U result = identity;
        for (T element : list) {
            result = foldFunc.apply(result).apply(element);
        }
        return result;
    }
}
