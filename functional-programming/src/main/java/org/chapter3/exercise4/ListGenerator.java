package org.chapter3.exercise4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 列表生成器
 */
public class ListGenerator {

    public static <E> E getListHead(List<E> list) {
        return list.get(0);
    }

    public static <E> List<E> getListTail(List<E> list) {
        List<E> dest = new ArrayList<>();
        Collections.copy(dest, list);
        dest.remove(0);
        return dest;
    }
}
