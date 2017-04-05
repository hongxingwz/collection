package com.jianglei.util;

/**
 * 一个线性集合支持从两端插入和删除元素。
 * deque 是 double ended queue的简写
 * 并且通常的发音是"deck"。
 * 大多数的Deque实现对于它们能够包含的元素数没有固定限制，
 * 但是此接口支持有容量限制的双端队列，也支持没有回定大小限制的双端队列。
 *
 * 此接口定义了从deque两端访问元素的方法。
 * 这些方法提供了insert, remove 和 examine 元素的方法。
 * 这些方法中的每个方法存在两种形式：
 * 一种：如果操作失败抛出一个异常，
 * 另外一种：返回一个特殊值（null 或 false)
 * 这依赖于哪种操作。
 * 后一种的插入操作设计用来使用容量限制的Deque的；
 * 在大多数的实现中，插入操作不能失败。
 *
 * 上面描述的12个方法被总结在下面的表格中
 *-------------------------------------------------------
 *      |第一个元素（头部）	            |最后一个元素（尾部）
 * ------------------------------------------------------
 *      |抛出异常       |	特殊值	        |抛出异常	    |特殊值
 * ------------------------------------------------------
 * 插入	| addFirst(e)	|offerFirst(e)	|addLast(e)	|offerLast(e)
 * --------------------------------------------------------
 * 移除	| removeFirst()	|pollFirst()	|removeLast()	|pollLast()
 * ---------------------------------------------------------
 * 检查	| getFirst()	|peekFirst()	|getLast()	|peekLast()
 * -----------------------------------------------------------
 *
 * 此接口扩展了Queue接口。在将双端队列用作队列时，将得到FIFO(先进先出）行为。
 * 将元素添加到双端队列的末尾，从双端队列的开头移除元素。
 * 从Queue接口继承的方法完全等效于Deque方法，如下表所示
 *
 * ------------------------------
 * Queue 方法	| 等效 Deque 方法
 * ------------------------------
 * add(e)	    | addLast(e)
 * ------------------------------
 * offer(e)	    | offerLast(e)
 * ------------------------------
 * remove()	    | removeFirst()
 * ------------------------------
 * poll()	    | pollFirst()
 * ------------------------------
 * element()	| getFirst()
 * ------------------------------
 * peek()	    | peekFirst()
 *-------------------------------
 *
 * 双向队列也可以用作后进先出的栈。
 * 这个接口应该被优先使用而不是遗留Stack类。
 * 在将双端队列用作堆栈时，元素被推入双端队列
 * 的开头并从双端队列开头弹出。堆栈方法完全等效于
 * Deque方法，如下表所示：
 * --------------------------
 * 堆栈方法|	等效 Deque 方法
 * --------------------------
 * push(e)|	addFirst(e)
 * --------------------------
 * pop()  |	removeFirst()
 * --------------------------
 * peek() |	peekFirst()
 * --------------------------
 *
 * 注意，在将双端队列用作队列或堆栈时，peek方法同样正常工作；
 * 无论哪种情况下，都从双端队列的开头抽取元素
 *
 * 此接口提供了两种移除内部元素的方法：removeFirstOccurrence和 removeLastOccurrence.
 *
 * 不像List接口，这个接口不提供索引访问元素
 *
 * 虽然Deque实现没有严格要求不允许插入一个null元素，但建议最好这样作。
 * 建议任何事实上允许null元素的Deque实现用户最好不要利用插入null的功能。
 * 这是因为各种方法会将null用作特殊的返回值来指示双端队列为空。
 *
 * Deque实现通常不定义基于元素的equals和 hashCode方法，而是从Object类继承基于身份的
 * equals和hashCode方法
 * Created by jianglei on 2017/4/4.
 */
public interface Deque<E> extends Queue<E> {

    /**
     * 在deque的前面插入指定的元素如果立即实现。
     * 抛出IllegalStateException如果目前没有空间可用，
     * 当使用一个具有容量限制的deque时，建议最好使用
     * offerFirst
     *
     * @param e 要被添加的元素
     *
     * @throws IllegalStateException 如果因为容量限制，此时不能添加元素
     *
     * @throws ClassCastException 如果指定元素的类不允许将其添加到此队列
     *
     * @throws NullPointerException 如果指定的元素是null 并且这个队列不允许null元素
     *
     * @throws IllegalArgumentException 如果此元素的某些属性不允许将其添加到此队列
     */
    void addFirst(E e);

    /**
     * 在deque的后面插入指定的元素如果立即实现。
     * 抛出IllegalStateException如果目前没有空间可用，
     * 当使用一个具有容量限制的deque时，建议最好使用
     * offerFirst
     *
     * @param e 要被添加的元素
     *
     * @throws IllegalStateException 如果因为容量限制，此时不能添加元素
     *
     * @throws ClassCastException 如果指定元素的类不允许将其添加到此队列
     *
     * @throws NullPointerException 如果指定的元素是null 并且这个队列不允许null元素
     *
     * @throws IllegalArgumentException 如果此元素的某些属性不允许将其添加到此队列
     */
    void addLast(E e);

    /**
     * 在deque的前面插入指定的元素除非受到容量的限制。
     * 当使用一个容量限制的deque时，此方法通常优于addFirst方法，
     * 后者插入元素失败时仅能抛出一个异常
     * @param e
     * @return 如果元素被添加到此队列中返回true 否则返回false
     *
     * @throws ClassCastException 如果指定元素的类不允许将其添加到此队列
     *
     * @throws NullPointerException 如果指定的元素是null 并且这个队列不允许null元素
     *
     * @throws IllegalArgumentException 如果此元素的某些属性不允许将其添加到此队列
     */
    boolean offerFirst(E e);

    /**
     * 在deque的后面插入指定的元素除非受到容量的限制。
     * 当使用一个容量限制的deque时，此方法通常优于addLast方法，
     * 后者插入元素失败时仅能抛出一个异常
     * @param e
     * @return 如果元素被添加到此队列中返回true 否则返回false
     *
     * @throws ClassCastException 如果指定元素的类不允许将其添加到此队列
     *
     * @throws NullPointerException 如果指定的元素是null 并且这个队列不允许null元素
     *
     * @throws IllegalArgumentException 如果此元素的某些属性不允许将其添加到此队列
     */
    boolean offerLast(E e);

    E removeFirst();

    E removeLast();

    E pollFirst();

    E pollLast();

    E getFirst();

    E getLast();

    E peekFirst();

    E peekLast();


    boolean removeFirstOccurrence(Object o);

    boolean removeLastOccurrence(Object o);

    // ****  队列的方法 ****
    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E element();

    E peek();

    // **** Stack 方法 ****

    void push(E e);

    E pop();


    // **** 集合的方法 ****
    boolean remove(Object o);

    boolean contains(Object o);

    public int size();

    Iterator<E> iterator();

    Iterator<E> descendingIterator();
}
