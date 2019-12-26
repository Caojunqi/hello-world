package org.chapter3.exercise8;

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

        System.out.println(foldRight(list, "0", x -> y -> addSI(x, y)));
    }

    public static String addSI(Integer i, String s) {
        return "(" + i + "+" + s + ")";
    }

    public static <T, U> U foldRight(List<T> ts, U identity, Function<T, Function<U, U>> f) {
        List<T> tail = ListGenerator.getListTail(ts);
        if (tail.isEmpty()) {
            return f.apply(ListGenerator.getListHead(ts)).apply(identity);
        } else {
            return f.apply(ListGenerator.getListHead(ts)).apply(foldRight(tail, identity, f));
        }
    }

    public static <T, U> U foldRightAnswer(List<T> ts, U identity, Function<T, Function<U, U>> f) {
        return ts.isEmpty()
                ? identity
                : f.apply(ListGenerator.getListHead(ts)).apply(foldRightAnswer(ListGenerator.getListTail(ts), identity, f));
    }
}
