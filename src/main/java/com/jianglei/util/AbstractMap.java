package com.jianglei.util;

/**
 * 此类提供了Map接口实际的骨架，
 * 为了实现此接口复出最小的努力。
 * <p>
 * 要实现一个不可改变的map, 程序员需要继承此类
 * 并且提供对entrySet的实现（返回map 映射的 set 视图）。
 * 传统来说，返回的set 将依次在AbstractSet上实现。此set
 * 不支持add 或 remove方法，其迭代器也不支持remove 方法
 * <p>
 * 要实现一个可变map，编程人员要额外实现此类的put方法（否则将会
 * 抛出UnsupportedOperationException), 并且 entrySet().iterator()返回的迭代器
 * 也要额外实现其remove方法
 * <p>
 * 编程人员通常提供一个无参构造器和一个仅含有map的构造器，按照Map接口规范的建议
 * <p>
 * 此类的所有非抽象方法的文档详细的描述了其实现。
 * 这些方法的每个方法可以被重写，如果要实现的映射允许更有效的实现。
 * <p>
 * 此类是Java Collections Framework 中的一员
 * Created by Administrator on 2017/4/7.
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {
    /**
     * 唯一的构造器。（供子类构造器调用，通常是隐式的）
     */
    protected AbstractMap() {

    }

    //Query Operations

    /**
     * {@inheritDoc}
     *
     * @implSpec 此实现返回entrySet().size()
     */
    public int size() {
        return entrySet().size();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec 此实现返回 size() == 0
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 此实现迭代整个entrySet()来搜索一个具有指定value值的entry。
     * 如果这样的entry被找到，返回true. 如果迭代器终止还没找到这样的
     * entry，返回false. 注意此实现的时间与map的大小成线性相关
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsValue(Object value) {
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if (value == null) {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (e.getValue() == null) {
                    return true;
                }
            }
        } else {
            while (i.hasNext()){
                Entry<K, V> e = i.next();
                if(value.equals(e.getValue())){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 此实现迭代整个entrySet()来搜索一个具有指定key值的entry。
     * 如果这样的entry被找到，返回true. 如果迭代器终止还没找到这样的
     * entry，返回false. 注意此实现的时间与map的大小成线性相关
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsKey(Object key){
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if(key == null){
            while (i.hasNext()){
                Entry<K, V> e = i.next();
                if(null == e.getKey()){
                    return true;
                }
            }
        }else {
            while (i.hasNext()){
                Entry<K, V> e = i.next();
                if(key.equals(e.getKey())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 此实现迭代整个entrySet 寻找一个具有指定key的entry.
     * 如果这样的entry找到了，entry 的值将被返回。如果迭代终止还
     * 没有找到这样的entry，null会被返回.
     * 注意此实现需要的线性时间与map的大小成正比;
     * 许多实现会覆盖此方法
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public V get(Object key){
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if(key == null){
            while (i.hasNext()){
                Entry<K, V> e = i.next();
                if(e.getKey() == null){
                    return e.getValue();
                }
            }
        }else {
            while (i.hasNext()){
                Entry<K, V> e = i.next();
                if(key.equals(e.getKey())){
                    return e.getValue();
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 此实现总是抛出一个UnsupprotedOperationException异常
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    public V put(K key, V value){
        throw new UnsupportedOperationException();
    }

}
