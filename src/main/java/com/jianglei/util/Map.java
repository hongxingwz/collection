package com.jianglei.util;

/**
 * 键映射到值的对象。
 * 一个map不能包含多个重复的key;
 * 每个key至多指能映射一个value
 *
 * 此接口取代Dictionary类，后者完全是一个抽象类，
 * 而不是一个接口。
 *
 * Map接口提供了三种集合视图，允许以键集，值集或键-值映射关系
 * 查看map的内容。映射顺序定义为迭代器在映射的collection视图上返回
 * 其元素的顺序。一些map实现，像TreeMap,明确保证他们的顺序，其他的，像
 * HashMap没有保证其顺序。
 *
 * 注意：使用可变对象作为map的key时要格外小心。当对象是映射中某个键时，
 * 如果以影响equals比较的方式更改了对象的值，则映射的行为将是不确定的。
 *
 * 此项禁止的一种特殊情况是不允许某个映射将自身作为一个键包含。
 * 虽然允许一个map包含自身作为一个值，但请格小心：在这样的映射上
 * equals和hashCode方法的定义将不再是明确的。
 *
 * 所有通用的映射实现类应该提供两个标准的构造器：一个无参构造器--创建一个空map,
 * 和一个只有一个Map类型参数的构造器--创建一个新的map,其key-value的映射与指定的
 * 参数一样。事实上，后一个构造器允许用户复制任意一个map,生产一个等价的，期望类型的映射
 * 尽管无法强制执行此建议（因为接口不能包含构造器），但是JDK中所有的通用的映射实现类者遵守
 * 了这个建议。
 *
 * 一些map实现对他所能包含的key 和 value 有限制。例如，一些实现不允许null key 和 value
 * 一些实现对他们所包含的key的类型有限制。试图插入一个不合格的键或值将会抛出一个未检查异常，
 * 通常来说是NullPointerException 或 ClassCastException.试图查询是否存在不合格的键或
 * 值可能抛出异常，或者仅返回false；一些实现将采用第一种的形式，一些将采用后一种的表现形式。
 * 通常来说，试图对不合格的键或值执行操作且该操作的完成不会导致不合格的元素被插入映射中时，
 * 将可能抛出一个异常，也可能操作成功，这取决于实现本身。这样的异常在些接口的规范中标记为"可选"
 *
 * 集合接口的许多方法是在equals方法的基础上定义的。例如，特定containsKey(Object)方法， containsKey
 * (Object key)的介绍：“如果此map包含一个含有该key (key == null ? k == null : key.equals(k)) 的映射则返回true
 * 不应该将此规范解释为：调用具有非空参数key的Map.containsKey 将导致对任意的键调用key.equals(key)。实现可以随意进行优化，
 * 以避免调用equals,例如，可首先比较两个键的哈希码（Object.hashCode()规范保证哈希码不相等的两个对象不会相等）。一般来说，
 * 只要实现者认为合适，各种Collections Framework 接口实现可随意利用底层Object 方法的指定行为。
 *
 * 一些map操作--递归的遍历map可参失败并抛出一个异常，这个的map可参直接或间接的包含自身的引用
 * 这些方法包话 clone(), equals(), hashCode(), toString(),
 * 实现可以可选的处理这些自身引用的方案，然而，大多数目前的实现没有这样做。
 *
 * 这个接口是 Java Collections Framework中的一员
 *
 * Created by jianglei on 2017/4/7.
 */
public interface Map<K, V> {

    /**
     * 返回map 映射的数量。
     * 如果map 包含元素的数量大于Integer.MAX_VALUE,
     * 返回 Integer.MAX_VALUE;
     * @return map 映射的数量。
     */
    int size();

    /**
     * 如果map不包含任何映射，返回true
     * @return true v如果map不包含任何映射
     */
    boolean isEmpty();

    /**
     * 反回true， 如果此map包含指定key的映射，
     * 更确切的说，返回true，如果此map包含指定key的映射--
     * (key == null ? k == null : key.equals(k))
     * 此map至多包含一个这样的映射
     * @param key 测试此map中是否存在指定的key
     * @return 返回true， 如果此map包含指定key的映射
     *
     * @throws ClassCastException 如果key的类型与此map允许的类型不相符
     *
     * @throws NullPointerException 如果指定的key为null, 而此map不允许null作为key
     */
    boolean containsKey(Object key);

    /**
     * 返回true， 如果此map 对对应value映射到了一个或多个键。
     * 通常来讲，返回true，如果此map包含至少一个映射 v
     * (value == null ? v== null : value.equals(v))
     * 对于大多数map实现来说，此操作的需要的时间与map的大小呈线性关系
     * @param value 测试此map中存不存在此value
     * @return true 如果此map 映射到了一个 或 多个对应的key
     *
     * @throws ClassCastException 如果值的类型与map需要值的类型不相符
     *
     * @throws NullPointerException 如果指定的值为null并且此map的value值不允许null值
     */
    boolean containsValue(Object value);

    /**
     * 返回映射到指定key的映射，如果没有映射到返回null,
     * 通常来讲，如果此map包含一个映射（k ,v ) 例如：
     * (key == null ? k == null ? key.equals(k))，然后
     * 此方法返回v,否则返回null(此map中至多包含一个这样的map)
     *
     * 如果此map允许null值，返回的null值并不一定代表此map没有相应的映射，
     * 也有可能是此map映射到了值为null的映射。可以使用containsKey方法来区分
     * 这两种情况
     * @param key 与此key相关联的value将会被返回
     *
     * @return 此key映射到的value，或者null如果此map不包含相应的映射
     *
     * @throws ClassCastException 如果此key的类型与map允许的key类型不相符
     *
     * @throws NullPointerException 如果此key为null, 并且此map不允许null作为key
     */
    V get(Object key);

    /**
     *  把指定key与指定value 相关联
     * @param key 与指定的value 相关联
     * @param value 与指定的key 相关联
     * @return 上一个与此key相关系的value,
     *          或者是null,如果此前没有与此key相关系的value
     *          (返回的null，也可以表示此key上个关系的value是null,
     *          如果实现支持null值)
     *
     * @throws UnsupportedOperationException 如果此map不支持put方法
     * @throws ClassCastException 如果指定的key 或 value 的类型与 不支持存储到map里
     * @throws NullPointerException 如果指定的key 或 value 为null 并且 map不允许null 作为key 或者 value
     * @throws IllegalArgumentException 如果此key 或 value 的一些属性阻止其被存储在此map里
     */
    V put(K key, V value);

    /**
     * 用key把此map中的相应的映射删除如果它存在的话（可选的操作）。
     * 更直白的讲，如果此map中包含一个这样的映射（k v),并且：
     * （key == null ? k ==  null : key.equals(k)) 这样的映射会被删除
     * （map至多会包含一个这样的映射）
     *
     * 如果此map 允许null 值， 返回的null 并不一定代表此map不包含与此key相对应的value;
     * 这也有可能是 此key对应的value 值为null
     * @param key 此key对应的映射将被删除
     * @return 与次key关联的上一个值，或者null如果这没有与key与关系的value
     *
     * @throws  UnsupportedOperationException 如果此map不支持该操作
     *
     * @throws ClassCastException 如果key的类型与map支持的类型不相符
     *
     * @throws NullPointerException 如果指定的key为null 并且此map不允许
     *          null值
     */
    V remove(Object key);

    /**
     * 拷贝所有指定map的映射到此map。
     * 此调用的影响与在指定map中的每个映射依次调用put(k,v)是等价的
     * 如果在调用此操作时，指定map被改变了，那么调用的行为是不确定的
     * @param m 要放进此map的映射
     *
     * @throws UnsupportedOperationException 如果此map不支putAll操作
     *
     * @throws ClassCastException 如果指定集合的key 或 value 被存储进该
     *         map中被阻止
     * @throws NullPointerException 如果指定的map是null，或者如果此map不允许
     *         null 键或值，并且指定的map包含null键或值
     *
     * @throws IllegalArgumentException 如果指定映射的一些属性阻止其被存储进此map
     */
    void putAll(Map<? extends K, ? extends  V> m);

    /**
     * 删除此map中所有的映射（可选的操作）
     * 此方法返回后，此map将会为空
     */
    void clear();

    /**
     * 返回此map包含的key的set视图，此set受map支持，因此改变map会影响到set,反之亦然。
     * 如果对该set进行迭代的同时修改了映射（通过迭代器自己的remove操作除外），则迭代结果
     * 是不确定的。set支持元素移除，可以从map删除相关联的映射，通过Iterator.remove  Set.remove,
     * removeAll, retainAll 和clear 操作。他不支持add 或者 addAll操作
     *
     * @return 此map包含的key的set视图。
     */
    Set<K> keySet();

    /**
     * 返回此map包含的value的视频，此collection受map支持，因此改变map 会影响到此集合，反之亦然。
     * 如果对该collection进行迭代的同时修改了映射（通过迭代器自己的remove除外），则迭代的结果是不确定的，
     * 此集合支持元素删除，从此map中删除相关联的映射，通过Iterator.remove, Collection.remove removeAll
     * retainAll 和 clear 操作。他不支持add 或 addAll操作
     * @return
     */
    Collection<V> values();

    /**
     * 返回此map包含的映射的Set视频。
     * 此set受map支持，因此改变map受影响到set,反之亦然。
     * 如果对该set进行迭代的同时修改了映射（通过迭代器自己的remove操作除外），则迭代结果
     * 是不确定的。set支持元素移除，可以从map删除相关联的映射，通过Iterator.remove  Set.remove,
     * removeAll, retainAll 和clear 操作。他不支持add 或者 addAll操作
     *
     * @return 返回此map包含映射的set视图
     */
    Set<Map.Entry<K, V>> entrySet();

    /**
     * map的入口（键值对的形式）。Map.entrySet方法返回的集合视图，他的元素就是这个类。
     * 唯一获得map entry引用的方式是从此map的集合视图进行迭代。这些Map.Entry 仅在迭代期间
     * 有效;更直白的说，map entry 的行为是不确定如果依赖的map被改变了在迭代器返回之后，除了
     * map entry 的 setValue操作
     *
     * @param <K>
     * @param <V>
     */
    interface Entry<K, V>{
        /**
         * 返回此entry相关联的key
         * @return 此entry相关联的key
         * @throws IllegalStateException 此实现不是必须的，如果此entry在依赖的map中被移除了
         */
        K getKey();

        /**
         * 返回此entry相关联的value，如果映射被依赖的map移除了，
         * 此调用的结果没有被定义
         * @return
         */
        V getValue();

        /**
         * 用指定的value替换此entry相关系的value.
         * 此调用的行为没有被定义，如果相应的映射被移除
         * @param value
         * @return
         */
        V setValue(V value);

        /**
         * 此entry与指定的对象比较。
         * 返回true 如果指定的对象也是map entry 并且
         * 两个entry 代表相同的映射。通常来说，
         * 两个entry e1和e2代表相同的映射
         * 如果：
         *  (e1.getKey() == null ?
         *  e2.getKey() == null : e1.getKey().equals(e2.getKey())) &&
         *  (e1.getValue() == null ?
         *   e2.getValue() == null : e1.getValue().equals(e2.getValue)))
         * @param o 与entry 作相等性比较的对象
         * @return true 如果指定的ojbect 与此 map entry 相等
         */
        boolean equals(Object o);

        /**
         * 返回此map entry 的hash值。
         * map entry的哈希值定义如下：
         *  (e.getKey() == null ? 0 : e.getKey().hashCode()) ^
         *  (e.getValue() == null ? 0 : e.getValue().hashCode())
         *
         * 对于任何两个entry 来说 e1.equals(e2) 意味着 e1.hashCode() == e2.hashCode()
         *
         *
         * @return
         */
        int hashCode();
    }

    boolean equals(Object o);

    int hashCode();


}
