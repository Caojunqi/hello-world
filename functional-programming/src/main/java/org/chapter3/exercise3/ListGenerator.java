package org.chapter3.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 列表生成器
 */
public class ListGenerator {

    public static <E> List<E> generateList() {
        return Collections.unmodifiableList(Collections.emptyList());
    }

    public static <E> List<E> generateList(E element) {
        return List.of(element);
    }

    public static <E> List<E> generateList(Collection<E> elements) {
        return List.copyOf(elements);
    }

    public static <E> List<E> generateList(E... elements) {
        List<E> list = new ArrayList<>();
        Collections.addAll(list, elements);
        return Collections.unmodifiableList(list);
    }
}
