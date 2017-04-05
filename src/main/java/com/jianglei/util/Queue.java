package com.jianglei.util;

/**
 * 这个集合被设计用来保存代处理的元素，
 * 除了基本的集合操作，queus提供了额外的插入，
 * 提取和检查操作。这些方法中的每个方法都以两种形式存在：
 * 一种：如果操作失败，抛出一个异常
 * 另一种：返回特别值（null 或者 false, 依赖于具体的操作)。
 * 插入操作的后一种形式是专门设计用来使用具有容量限制的queue实现的；
 * 在大多数的实现中，插入操作不会失败
 *
 * ----------------------------------
 *        Queue 元素方法梗概
 * ----------------------------------
 *              抛出异常   | 返回特殊值
 * ----------------------------------
 * 插入       | add(e)    | offer(e)
 * ----------------------------------
 * 删除       | remove()  | poll()
 * ----------------------------------
 * 检测       | element() | peek()
 * ----------------------------------
 *
 * 队列通常（但并不一定）以 FIFO（先进先出）的方式排序元素。
 * 这其中的例外有：
 * 优先级队列，依据提供的比较器，或者元素自然排序
 * LIFO队列（或者叫栈）把元素以LIFO（后进先出）的顺序排序。
 * 无哪使用哪种排序方式，队列的头--当调用remove()或poll()
 * 会被删除。
 * 在先进先出的队列中，所有的新元素被插入在队列的尾部。
 * 另外几种队列可能使用不同的元素放置规则。
 * 每个实现都必须指定其排序属性
 *
 * offer方法插入一个元素如果可以的话，否则返回false。
 * 这个方法与Collection 的 add 方法不同的是，
 * 他添加元素可能失败仅抛出一个未检查异常。
 * offer方法被设计用来当插入失败是正常现象，而不是
 * 引发异常，例如在固定容量的队列中。
 *
 * remove() 和 poll()方法删除并返回队列的头。到底从队列
 * 中移除哪个元素是队列排序策略的功能，而该策略在各种实现中是不同的。
 * remove()和poll()方法仅在队列为空时其行为有所不同：
 * remove()方法抛出一个异常，而poll方法则返回null。
 *
 * element() 和 peek()方法返回，但不删除队列的头
 *
 * 队列接口没有定义阻塞队列的方法，而这在并发编程中是很常见的。
 * 这些方法等待元素出现或空出变为可用，在BlockingQueue接口中定义了，
 * 该接口继承了此接口
 *
 * Queue实现通常不允许插入null元素，尽管某些实现如LinkedList并不禁止插入null。
 * 即使在允许插入null元素的实现中，null也不应该被插入进一个序列，因为null被poll方法
 * 用来作为一个特殊的返回值表明这个队列没有任何元素。
 *
 *
 * Queue实现通常未定义equals和hashCode方法的基于元素的版本，
 * 而是从Object类继承了基于身份的版本，为因对于具有相同元素但
 * 有不同排序属性的队列而言，基于元素的相等性并非总是定义良好的。
 *
 *
 * Created by jianglei on 2017/4/4.
 */
public interface Queue<E> extends Collection<E>  {

    /**
     * 将指定的元素插入到此队列（如果立即可行且不会违反容量限制），
     * 在成功时返回true,如果当前没有可用的空间，则抛出一个IllegalStateException
     *
     * @param e 确定此collection中是否存在的元素。
     * @return true （根据Collection.add(E)的规定）
     *
     * @throws IllegalStateException 如果因为容量限制，此时不能添加元素
     *
     * @throws ClassCastException 如果指定元素的类不允许将其添加到此队列
     *
     * @throws NullPointerException 如果指定的元素是null 并且这个队列不允许null元素
     *
     * @throws IllegalArgumentException 如果此元素的某些属性不允许将其添加到此队列
     *
     */
    boolean add(E e);

    /**
     * 将指定的元素插入此队列（如果立即可行且不会违反容量限制），
     * 当使用一个具有容量限制的队列时，这个方法通常优先于add，因为后一个
     * 方法当插入原始失败时仅会抛出一个异常
     * @param e
     * @return 如果元素被添加到queue返回true, 否则返回false
     *
     * @throws ClassCastException 如果指定元素的类不允许将其添加到此队列
     *
     * @throws NullPointerException 如果指定的元素是null 并且这个队列不允许null元素
     *
     * @throws IllegalArgumentException 如果此元素的某些属性不允许将其添加到此队列
     */
    boolean offer(E e);

    /**
     * 提取并且返回此队列的头部。此方法与poll方法仅有的不同是，
     * 当元素为空时此方法会抛出一个异常
     * @return 此队列的头
     *
     * @throws java.util.NoSuchElementException 如果队列是空
     */
    E remove();

    /**
     * 提取并且返回此队列的头部。
     * 如果此队列是空，返回null
     * @return 队列的头部，如果队列是空返回null
     */
    E poll();

    /**
     * 提取，但不删除队列的头部。
     * 这个方法与peek仅有的不同是
     * 当队列为空时会抛出一个异常
     *
     * @return 此队列的头
     *
     * @throws java.util.NoSuchElementException 如果队列是空
     */
    E element();

    /**
     * 提取，但不删除队列的头部，
     * 如果队列为空返回null
     * @return
     */
    E peek();


























}
