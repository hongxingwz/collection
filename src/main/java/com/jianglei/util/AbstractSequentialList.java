package com.jianglei.util;

import java.util.NoSuchElementException;

/**
 * 此类提供了List接口的骨干实现，从而最大限度地减少了实现受
 * "连续访问"数据存储（如链接列表）支持的此接口所需的工作。
 * 对于随机访问数据（例如 数组），应该优先使用AbstractList
 * 而不是此类
 *
 * 从某种意义上说，此类与在列表的列表迭代器上实现"随机访问"方法（get(int index)、
 * set(int index, E element)，add(int index, E element)和 remove(int index)
 * 的AbstractList类相对立，而不是其他关系。
 *
 * 要实现一个列表，编程人员只需要继承这个类并且提供listIterator 和 size 方法的实现即可。
 * 对于一个不可变的的列表，编程人员只需要实现列表迭代器的hasNext next hasPrevious 和
 * previous 和 index 方法
 *
 * 对于可修改的列表，程序员应该再另外实现列表迭代器的set方法。对于可变大小的列表，
 * 程序员应该再另外实现列表迭代器的remove和add方法
 * Created by jianglei on 2017/4/5.
 */
public  abstract class AbstractSequentialList<E> extends AbstractList<E> {
    protected AbstractSequentialList(){

    }

    /**
     * 返回这个列表指定位置的元素
     * 此实现首先获得一个指向索引元素的列表迭代器（通过listIterator(index)方法）。
     * 然后它使用ListIterator.next方法获取并返回元素
     * @param index
     * @return
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index){
        try{
            return listIterator(index).next();
        }catch (NoSuchElementException exc){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    /**
     * 用指定元素替换此列表中指定位置的元素（可选的操作）
     * 此实现首先获得一个指向索引元素的列表迭代器（通过listIterator(index)方法）。
     * 然后它使用ListIterator.next方法获取当前元素并用ListIterator.set替换他
     *
     * 注意此实现的列表迭代器没有实现set操作将会抛出UnsupprotedOperationException
     * @param index
     * @param element
     * @return
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public E set(int index, E element){
        try{
            ListIterator<E> e = listIterator(index);
            E oldVal = e.next();
            e.set(element);
            return oldVal;
        } catch (NoSuchElementException exc){
            throw new ArrayIndexOutOfBoundsException("Index: " + index);
        }
    }

    /**
     * 在此列表中的指定位置上插入指定的元素（可选操作）。
     * 向右移动当前位于该位置上的元素（如果有）以级所有后续元素（将其索引加1）
     *
     * 此实现首先获得一个指向索引元素的列表迭代器（通过 listIterator(index)）。然后它使用 ListIterator.add 插入指定的元素。
     * 注意，如果列表迭代器没有实现 add 操作，则此实现将抛出 UnsupportedOperationException。
     *
     * @param index
     * @param element
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     *
     */
    public void add(int index, E element){
        try {
            listIterator(index).add(element);
        } catch (NoSuchElementException exc){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    /**
     * 返回在此列表中的元素上进行迭代的列表迭代器（近适当顺序）
     * @param index 从列表迭代器返回（通过调用next方法）的第一个元素的索引
     * @return 在此列表中的元素上进行迭代的列表迭代器（按适当顺序）
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public abstract ListIterator<E> listIterator(int index);
}
