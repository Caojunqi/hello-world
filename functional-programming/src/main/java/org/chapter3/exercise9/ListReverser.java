package org.chapter3.exercise9;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表反转类
 */
public class ListReverser {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(prepend(head(list),list));

    }

    public static <T> List<T> prepend(T t, List<T> list) {
        return foldLeft(list, list(t), a -> b -> append(a, b));
    }

    public static <T> List<T> list(T t) {
        List<T> list = new ArrayList<>();
        list.add(t);
        return list;
    }

    public static <T> List<T> append(List<T> list, T t) {
        List<T> newList = new ArrayList<>();
        newList.add(t);
        newList.addAll(list);
        return newList;
    }

    public static <T> List<T> foldLeft(List<T> list, List<T> identity, Function<List<T>, Function<T, List<T>>> function) {
        for (T element : list) {
           identity = function.apply(identity).apply(element);
        }
        return identity;
    }

    public static <T> T head(List<T> list){
        return list.get(0);
    }
}
