package com.jianglei.util;

/**
 * 集合层次结构的根接口。一个集合代表一组对象，这些对象也被称作集合的元素。
 * 一些集合允许重复的元素，另外的不允许。
 * 一些集合是有序的，另外是无序的。
 * JDK对该接口没有提供任何直接实现：它提供更具体的子接口（如Set 和 List)实现。
 * 这个接口通常用来传递collection, 并在需要最在普遍性的地方操作这些collection。
 *
 * 包 或  多集合（无序集合可能包含重复的元素）应该直接实现这个接口。
 *
 * 所有通用的Colletion实现类（通常通过它的一个子接口间接实现Collection)应该提供两个标准的构造方法：
 * 一个无参构造方法，创造一个空集合，
 * 一个具有一个Collection 类型的构造器，创建和参数具有相同元素的新集合。
 * 在事实上，后一个构造器允许使用者复制任何集合，生产所需实现类型的一个等效collection。
 * 尽管无法强制执行些约定（因为接口不能包含构造方法），但是Java平台库中所有能用的Collection实现都遵从它。
 *
 * 此接口中包含的"破坏性"方法，是指可修改其所操作的collection的那些方法，如果此collection不支持该操作，则指定
 * 这些方法抛出UnsupportedOperationException。如果是这样，那么在调用对该collection无效时，这些方法可能，但并不一
 * 定抛出UnsupportedOperationException。例如，如果要添加的collection为空且不可修改，则对该collection调用addAll(Collection)
 * 方法时，可能但并不一定抛出异常。
 *
 * 一些collection实现对它们可能包含的元素有所限制。例如，某些实现禁止null元素，而某些实现则对元素的类型有限制。
 * 试图添加不合格的元素将抛出一个未经检查的异常，通常是NullPointerException或ClassCastException。试图查询是否
 * 存在不合格的元素可能抛出一个异常，或者只是简单地返回false；某些实现将表出出前一种行为，而某些实现则表现后一种。
 * 较为常见的是，试图对某个不合格的元素执行操作且该操作的完成不会导致将不合格的元素插入collection中，将可能抛出一个异常，
 * 也可能操作成功，这取决于实现本身。这样的异常在些接口的规范中标记为"可先"。
 *
 * 由每个collection来确定其自身的同步策略。在没有实现的强烈保证的情况下，调用由另一进程正在更改的collection的方法
 * 可能会出现不确定行为；这包括直接调用，将collection传递给可能执行调用的方法，以及使用现有迭代器检查collection.
 *
 * Collections Framework接口中的很多方法是根据equals方法定义的。例如，constains(Object o)方法的规范声明："当且仅当此
 * collectioin包含至少一个满足（o == null ? e == null : o.equals(e))元素e时，返回true。"不应将此规范理解为它暗指调用具有
 * 非空参数o的Collection.contains方法会导致为任意的e元素调用o.equals(e)方法。可随意对各种实现执行优化，只要避免调用equals即可，
 * 例如，通过首先比较两个元素的哈希码。（Object.hashCode()规范保证哈希码不相等的两个对象不会相等）。较为常见的是，各种Collections
 * Framework接口的实现可随意利用底层Object方法的指定行为，而不管实现程序认为它是否合适。
 *
 * Created by jianglei on 2017/4/3.
 */
public interface Collection<E> extends Iterable<E> {
    /**
     * 返回集合元素的数量。如果这个集合包含的元素超过Integer.MAX_VALUE,
     * 返回Integer.MAX_VALUE
     *
     * @return 集合元素的数量
     */
    int size();

    /**
     * 如果集合不含有任何元素返回true
     *
     * @return  如果集合不含有任何元素返回 true
     */
    boolean isEmpty();

    /**
     * 返回true如果这个集合含有指定的元素。
     * 更确切地讲，当且仅当此collection至少包含一个满足（o == null ? e == null : o.equals(e))
     * 的元素e时，返回true.
     *
     * @param o 测试在些collection中是否存在的元素。
     * @return true 如果这个集合包含这个指定的元素
     *
     * @throws ClassNotFoundException 如果指定元素的类型不适合这个集合（可选）
     * @throws NullPointerException 如果这个指定的元素是null并且这个集合不允许null元素（可选）
     */
    boolean contains(Object o);

    /**
     * 返回在此collection的元素上进行迭代的迭代器。关于元素返回的顺序没有任何保证
     * （除非此collection是某个能提供保证顺序的类实例）。
     *
     * @return 在些collection的元素上进行迭代的Iterator
     */
    Iterator<E> iterator();

    /**
     * 返回一个包含此集合所有元素的数组。
     * 如果这个集合确保其迭代器返回的元素有序，
     * 这个方法返回的必须经相同的顺序返回这些元素。
     *
     * 返回的数组将会是"安全的"，因为此collection并不维护对返回数组的任何引用。
     * （换句话说，即使collection受到数组的支持，此方法也必须分配一个新的数组）。
     * 因此，调用者可以随意修改返回的数组。
     *
     * 此方法充当了基于数组和基于集合API的桥梁。
     *
     * @return 一个包含此集合所有元素的数组
     */
    Object[] toArray();

    /**
     * 返回包含此collection中所有元素的数组；
     * 返回数组的运行时类型与指定数组的运行时类型相同。
     * 如果指定的数组能容纳该collection，则返回包含此collection元素的数组。
     * 否则，将分配一个具有指定数组运行时类型和此collection大小的新数组。
     *
     * 如果指定指定的数组能容纳collection，并有剩余的空间（即数组的元素比collection的元素多），
     * 那么会将数组中紧接collection尾部的元素设置为null。（只有在调用者知道此collection没有包含任何null
     * 元素时才能用此方法确定collection的长度。）
     *
     * 如果此collection对其迭代器返回的元素顺序做出了某些保证，那么此方法必须经相同的顺序返回这些元素。
     * 像toArray()方法一样，此方法充当基于数组的API与基于collection的API之间的桥梁。更进一步说，此方法允许
     * 对输出的数组的运行时类型进行精确控制，并且在某些情况下，可以用来节省分配开销。
     *
     * 假定x是只包含字符串的一个已知collection.以下代码用来将collection转储到一个新分配的String数组：
     *      String[] y = x.toArray(new String[0]);
     * 注意，toArray(new Object[0])和 toArray()在功能上是相同的。
     *
     *
     * @param a 存储此collection元素的数组（如果其足够大）；否则，将为此分配一个具有相同运行时类型的新数组。
     * @param <T> 数组（包含这个集合）的运行时类型
     * @return 包含这个集合所有元素的数组
     *
     * @throws ArrayStoreException 如果指定的数组的运行时类型不是此collection每个元素运行时类型的超类型
     * @throws NullPointerException 如果指定的数组是null
     */
    <T> T[] toArray(T[] a);

    /**
     * 确保此集合包含指定的元素（可先的操作）。如果此collection由于调用而发生更改，
     * 则返回true。（如果些collection不允许有重复元素，并且已经包含了指定的元素，则返回false。)
     * 支持此操作的集合可以限制哪此元素能添加到此collection中来。
     * 需要特别指出的是，一些collection拒绝添加null元素，
     * 其他一些collection将对可以添加的元素类型强加限制。
     * Collection类应该在其文档中清楚地指定能添加哪些元素方面的所有限制。
     *
     * 如果collection由于某些原因拒绝添加特定的元素，那么它必须抛出一个异常（而不是返回false)。
     * 这确保了在些调用返回后，collection总是包含指定的元素。
     *
     * @param e 确定此collection中是否存在的元素。
     * @return 如果collection由于调用而发生更改，则返回true
     *
     * @throws UnsupportedOperationException 如果此collection不支持add操作
     *
     * @throws ClassCastException 如果指定的元素的类不允许它添加到此collection中
     *
     * @throws NullPointerException 如果指定的元素为null并且这个集合不允许null元素
     *
     * @throws IllegalArgumentException 如果元素的某属性不允许它添加到此collection中
     *
     * @throws IllegalStateException 如果由于插入限制，元素不能在此时间添加
     */
    boolean add(E e);

    /**
     * 从此集合中删除指定元素的单个实例，如果存在的话（可选操作）。
     * 更确切地讲，如果此collection包含一个或多个满足（o == null ? e == null : o.equals(e))
     * 的元素e, 则移除这样的元素。如果此collection包含指定的元素（或者此collection由于调用而发生更改），
     * 则返回true
     * @param o 要从此collection中移除的元素（如果存在）
     * @return true 如果一个元素被移除，作为此次调用的返回值
     *
     * @throws ClassCastException 如果指定的元素的类型与此集合不匹配
     *
     * @throws NullPointerException 如果指定的元素是null并且此集合不允许null元素
     *
     * @throws UnsupportedOperationException 如果此集合不支持remove操作
     */
    boolean remove(Object o);

    /**
     * 返回true如果这个集合指定集合所有的元素
     * @param c 将检查是否包含在此collection中的collectiono
     * @return 返回true 如果这个集合包含指定集合所有的元素
     *
     * @throws ClassCastException 如果指定集合中一个或多个元素与此集合的类型不匹配
     *
     * @throws NullPointerException 如果指定集合包含一个或多个null元素，并且这个集合不允许null元素
     *         或者指定集合是null
     */
    boolean containsAll(Collection<?> c);


    /**
     * 把指定集合的甩有元素添加到此集合（可先的操作）。如果在进行此操作的同时修改指定的collection,那么此操作行为是不确定的。
     * （这竟味着如果指定的collection是此collection，并且此collection为非空，那么此调用的行为是不确定的。）
     *
     * @param c 包含要添加到此collection的元素的collection
     * @return true 如果此集合由于调用而发生更改，则返回true
     *
     * @throws UnsupportedOperationException 如果此集合不支持addAll操作
     *
     * @throws ClassCastException 如果指定collection中某个元素的类不允许它添加到此collection中
     *
     * @throws NullPointerException 如果指定collection包含null元素，并且此collection不支持null元素，或者指定
     * 的collection为null
     *
     * @throws IllegalArgumentException 如果指定collection的元素的某属性不允许它添加到此collection中
     *
     * @throws IllegalStateException 如果由于插入限制，不是所有的元素都能在此时间添加
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * 删除此collection中那此也包含在指定collection中的所有元素（可选操作）。此调用返回后，collection中将不包含任何与指定
     * collection相同的元素。
     * @param c 准备要从此collection移除的元素的collection
     *
     * @return 如果此集合因此调用而改变则返回true
     *
     * @throws UnsupportedOperationException 如果这个元素不支持removeAll方法
     *
     * @throws ClassCastException 如果此collection中一个或多个
     */
    boolean removeAll(Collection<?> c);

    /**
     * 仅保留此collection中那些也包含在指定collection的元素（可先操作）。换句话说，移除此collection中未包含在指定collection中的
     * 所有元素。
     * @param c 包含保留在此collection中的元素的collection
     * @return 如果此collection由于调用而发生更改，则返回true
     *
     * @throws UnsupportedOperationException 如果此集合不支持retainAll操作
     *
     * @throws ClassCastException 如果此集合中的一个或多个元素类型与指定的集合不匹配
     *
     * @throws NullPointerException 如果这个集合包含一个或多个null元素并且指定的集合不允许null元素
     *         或者指定的集合不支持null
     */
    boolean retainAll(Collection<?> c);

    /**
     * 此方法返回后，除非抛出一个异常，移除此集合中的所有元素（可选的操作）。
     *
     * @throws UnsupportedOperationException 如果此集合不支持clear操作
     */
    void clear();

    /**
     * 用指定的对象与此集合比较相等性
     *
     * 当Collection接口没有对Object.equals的常规协定添加任何约定时，
     * "直接"实现该Collection接口（换句话说，创建一个Collection，但它不是Set或List的类）的程序员选择重写Object.equals
     * 方法时必须小心。没必要这样做，最简单的方案是依靠Object的实现，然而实现者可能希望实现"值比较"，而不是默认的"引用比较"。
     * （List 和 Set 接口要求进行这样的值比较。）
     *
     * Object.equals方法的常规协定相等必须是对称的（换句话说，当且仅当存在b.equals(a)时，才存在a.equals(b)。
     * List.equals和Set.equals的协定声称列表只能与列表相等，set只能与set相等。因此，对于一个既不实现List又不实现Set接口
     * 的collection类，当将此collection与任何列表或set进行比较时，常规的equals方法必须返回false。
     *
     * (按照相同的逻辑，不可能编写一个同时正确实现set和List接口的类。）
     * @param o 用来与此集合比较相等性的对象
     * @return
     */
    boolean equals(Object o);

    /**
     * 返回此collection的哈希码值。当Collection接口没有为Object.hasCode方法的常规协定添加任何约束时，
     * 为了满足Object.hasCode方法的常规协定，程序员应该注意任何重写Object.equals方法的类必须重写Object.hasCode方法。
     * 需要特别指出的是，c1.equals(c2)暗示着c1.hashCode() == c2.hashCode()。
     * @return
     */
    int hashCode();
}
