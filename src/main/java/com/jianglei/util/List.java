package com.jianglei.util;

import java.util.Objects;

/**
 * 一个有序的集合（也叫序列）。此接口的用户可以对列表中每个元素的插入位置进行精确地控制。用户可以通过整数索引
 * （列表中的位置）访问元素，并且在列表中搜索元素。
 * 不像sets，列表传统允许重复的元素。更确切地讲，列表通常允许一对元素e1 和 e2, e1.equals(e2)，并且他们
 * 通常允许重复的null元素只要他们允许null元素。难免有人希望通过在用户尝试插入重复元素时抛出运行时异常的方法
 * 来禁止重复的列表，但我们希望这种用法越少越好。
 *
 * List接口在iterator, add, remove, equals 和 hashCode方法的协定上加了一些其他约定，超过了Collection
 * 接口中指定的约定。为方便起见，这里也包括了其他继承方法的声明。
 *
 * List 接口提供四个方法用来定位访问列表的元素。List像（Java 数组）是基于0的。注意，这些操作可能在和某些实现
 * （例如LinkedList类）的索引值成比例的时间内执行。因此，如果调用者不知道实现，那么在列表元素上迭代通常优于用
 * 索引遍历列表。
 *
 * List接口提供了特殊的迭代器，称为ListIterator,允许元素插入和替换，以级双向访问。还提供了一个方法来获取
 * 从列表中指定位置开始的列表迭代器。
 *
 * List接口提供了两种搜索指定对象的方法。从性能的观点来看，应该小心使用这些方法。在很多实现中，它们将执行高
 * 开销的线性搜索
 *
 * List接口提供了两种在列表的任意位置高效插入和移除多个元素的方法。
 *
 * 注意：尽管允许列表包含自身作为元素，但建议要特别小心：equals 和 hashCode方法不再是定义良好的。
 *
 * 一些列表实现对列表可能包含的元素有限制。例如，一些实现禁止null元素，一些对他们的元素类型有限抽。
 * 添加一个不合格的元素抛出一个未检查异常，通常是NullPointerException 或者 ClassCastException。
 * 尝试查询一个不合格的元素可能抛出一个异常，也可能仅返回false;某些实现可能会采用前者的行为，一些会采用
 * 后者。概括地说，试图对不合格元素执行操作时，如果完成该操作后不会导致在列表中插入不合格的元素，则该操作可能
 * 抛出一个异常，也可能成功，这取决于实现的选择。此接口的规范中将这新的异常标记为"可选"
 *
 *
 *
 *
 * Created by jianglei on 2017/4/3.
 */
public interface List<E> extends Collection<E>{
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    boolean add(E e);

    boolean remove(Object o);

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends  E> c);

    boolean addAll(int index, Collection<? extends  E> c);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    void clear();

    boolean equals(Object o);

    int hashCode();

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int index);

    List<E> subList(int fromIndex, int toIndex);
}
