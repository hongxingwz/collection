package com.jianglei.util;

/**
 * list的迭代器
 * Created by jianglei on 2017/4/3.
 */
public interface ListIterator<E> extends Iterator<E> {
    boolean hasNext();

    E next();

    boolean hasPrevious();

    E privious();

    int nextIndex();

    int previousIndex();

    //Modification Operations
    void remove();

    void set(E e);

    void add(E e);
}
