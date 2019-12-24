package org.chapter3.exercise7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFolder {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(listFoldRight(list, "0", x -> y -> addSI(x, y)));
    }

    public static String addSI(Integer i, String s) {
        return "(" + i + "+" + s + ")";
    }

    public static <T, U> U listFoldRight(List<T> list, U identity, Function<T, Function<U, U>> foldFunc) {
        if (list == null || list.isEmpty()) {
            return identity;
        }
        U result = identity;
        List<T> newList = new ArrayList<>(list);
        Collections.reverse(newList);
        for (T element : newList) {
            result = foldFunc.apply(element).apply(result);
        }
        return result;
    }

    public static <T, U> U foldRight(List<T> ts, U identity, Function<T, Function<U, U>> f) {
        U result = identity;
        for (int i = ts.size(); i > 0; i--) {
            result = f.apply(ts.get(i - 1)).apply(result);
        }
        return result;
    }
}
