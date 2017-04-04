package com.jianglei.util;

/**
 * 对collection集合进行迭代的迭代器。Iterator在Java集合框架中取代了Enumeration。
 * Iterators与enmerations有两点不同：
 * <ul>
 *     <li>迭代器允许调用者利用定义良好的语义在迭代期间从迭代器所指向的collection移除元素</li>
 *     <li>方法名得到了改进</li>
 * </ul>
 *
 * <p>这个接口是Java Collection Framework 中的一员</p>
 *
 * @param <E> 这个迭代器返回的元素类型
 * Created by jianglei on 2017/4/3.
 */
public interface Iterator<E> extends java.util.Iterator<E>{
    /**
     * 如果迭代器仍有元素可迭代返回true
     * （另一种说法， 如果next()返回一个元素而不是抛出一个异常
     * 就返回true
     *
     * @Return 如果迭代器还有更多的元素返回true
     */
    boolean hasNext();

    /**
     * 返回迭代的下一个元素
     *
     * @return 迭代器的下一个元素
     * @throws java.util.NoSuchElementException 如果没有元素可迭代
     */
    E next();

    /**
     * 移除正在迭代集合的由这个迭代器返回的最后一个元素（可先的操作）。
     * 每调用一次next()方法只能调用一个这个方法。
     * 如果正在迭代时用调用此方法之外的其他方式修改了该迭代器所指向的collection，
     * 则迭代器的行为是不确定的。
     *
     * @throws UnsupportedOperationException 如果这个迭代器不支持remove操作
     *
     * @throws IllegalStateException 如果next()方法还没有被调用，或者在调用next()
     * 方法后已经调用了remove()方法
     */

    default void remove(){
        throw new UnsupportedOperationException("remove");
    }

}
