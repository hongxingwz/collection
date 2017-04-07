package com.jianglei.util;

/**
 * 一个不包含重复元素的集合。更确切的讲，set
 * 不包含成对的元素：e1 和 e2 (e1.equals(e2)),
 * 至多包含一个null元素。像他名字描述的一样，
 * 此接口模仿了数学上的set抽象。
 * 在所有构造方法以及add、equals和 hashCode方法的协定上，Set接口还
 * 加入了其他规定，这些规定超出了从Collection接口所继承的内容。出于方便，
 * 他还包括了其他继承方法的声明（这些声明的规范已经专门针对Set接口进行了修改，
 * 但是没有包含任何其他的规定）。
 *
 * 对于构造方法的额外规定，不要奇怪，所有的构造方法一定要创建一个不包含任何重复元素
 * 的set。
 *
 * 一些set的实现对它们能包含的元素有限制。例如，一些实现不允许null元素，
 * 一些对他们自身的元素类型有限制。试图加入一个不允许的元素会抛出一个未检查
 * 异常，通常上是NullPointerException 或 ClassCastException。
 * 尝试查询一个不允许的元素可能抛出一个异常，或者可能紧返回false;
 * 一些实现会采用前者的形式，一些实现会采用后者的形式。通常来说，试图对不合格
 * 元素执行操作时，如果完成该操作后不会导致在set中插入不合格的元素，则该操作可能
 * 抛出一个异常，也可能成功，这取决于实现的选择。此接口的规范中将这个的异常标记为
 * "可选"
 *
 * @param <E> 此set包含元素的类型
 * Created by jianglei on 2017/4/6.
 */
public interface Set<E> extends Collection<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean remove(Object o);

    /**
     * 返回的迭代器不是特别有序的（除非这个set是某些确保有序的类的实例）
     * @return
     */
    Iterator<E> iterator();

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    boolean addAll(Collection<? extends E> c);

    boolean containsAll(Collection<?> c);

    boolean add(E e);

    Object[] toArray();

    <T> T[] toArray(T[] t);

    void clear();

    boolean equals(Object o);

    int hashCode();


}
