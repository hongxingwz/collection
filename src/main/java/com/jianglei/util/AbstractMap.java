package com.jianglei.util;

import java.io.Serializable;

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

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 此实现迭代整个entrySet()寻找具有指定key的entry。
     * 如果这样的entry被找到，其value值被其getValue操作获得，
     * 此entry从此collection中移除（依靠map)通过迭代器的remove
     * 操作，然后被保存的value被返回，如果迭代终止还没找到这样的entry，
     * null值被返回。注意此实现需要线性时间与map的大小相关；许多实现
     * 会覆盖此方法。
     *
     * 注意此实现会抛出一个UnsupportedOperationException，如果entrySet
     * 迭代器不支持remove方法并且此map包含指定的key
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public V remove(Object key){
        Iterator<Entry<K, V>> i = entrySet().iterator();
        Entry<K, V> correctEntry = null;
        if(key == null){
            while (correctEntry == null && i.hasNext()){
                Entry<K, V> e = i.next();
                if(e.getKey() == null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry == null && i.hasNext()){
                Entry<K, V> e = i.next();
                if(key.equals(e.getKey()))
                    correctEntry = e;
            }
        }
        V oldValue = null;
        if(correctEntry != null){
            oldValue = correctEntry.getValue();
            i.remove();
        }

        return oldValue;
    }

    //Bulk Operation

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 此实现迭代整个指定map的entrySet()返回的集合，
     * 并对迭代器返回的entry 调用此map的put操作.
     *
     * 注意此实现抛出一个UnsupprotedOperationException
     * 如果此map不支持put操作，并且指定的map非空
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */

    public void putAll(Map<? extends K, ? extends V> m){
        for(Map.Entry<? extends K, ? extends V> e : m.entrySet()){
            put(e.getKey(), e.getValue());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 此实现调用entrySet.clear()
     *
     * 注意此实现抛出一个UnsupportedOperation 如果
     * entrySet不支持clear操作
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     *
     */
    public void clear(){
        entrySet().clear();
    }

    //Views
    /**
     * 在这些视图第一次需要的时候，视图被创建，包含正确的此map实例
     * 的视图。这些视力是无状态的，因此没有原因要创建多个。
     */
    transient Set<K> keySet;
    transient Collection<V> values;

    public Set<K> keySet(){
        Set<K> ks = keySet;
        if(ks == null){
            ks = new AbstractSet<K>() {
                @Override
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        private Iterator<Entry<K, V>> i = entrySet().iterator();
                        @Override
                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        @Override
                        public K next() {
                            return i.next().getKey();
                        }

                        public void remove(){
                            i.remove();
                        }
                    };
                }

                @Override
                public int size() {
                    return AbstractMap.this.size();
                }

                public boolean isEmpty(){
                    return AbstractMap.this.isEmpty();
                }

                public void clear(){
                    AbstractMap.this.clear();
                }

                public boolean contains(Object k){
                    return AbstractMap.this.containsKey(k);
                }
            };

            keySet = ks;
        }
        return ks;
    }

    public Collection<V> values(){
        Collection<V> vals = values;
        if(vals == null){
            vals = new AbstractCollection<V>() {
                @Override
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        private Iterator<Entry<K, V>> i = entrySet().iterator();


                        @Override
                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        @Override
                        public V next() {
                            return i.next().getValue();
                        }

                        public void remove(){
                            i.remove();
                        }
                    };


                }

                @Override
                public int size() {
                    return AbstractMap.this.size();
                }

                public boolean isEmpty(){
                    return AbstractMap.this.isEmpty();
                }

                public void clear(){
                    AbstractMap.this.clear();
                }

                public boolean contains(Object v){
                    return AbstractMap.this.containsValue(v);
                }
            };
            values = vals;
        }
        return vals;
    }

    public abstract Set<Entry<K, V>> entrySet();

    public boolean equals(Object o){
        if(o == this)
            return true;

        if(!(o instanceof Map)){
            return false;
        }
        Map<?, ?> m = (Map<?, ?>) o;
        if(m.size() != size())
            return false;

        try {
            Iterator<Entry<K, V>> i = entrySet().iterator();
            while (i.hasNext()){
                Entry<K, V> e = i.next();
                V value = e.getValue();
                K key = e.getKey();

                if(value == null){
                    if(!(m.get(key) == null && m.containsKey(key)))
                        return false;
                } else {
                    if(!value.equals(m.get(key)))
                        return false;
                }

            }
        }catch (ClassCastException unused){
            return false;
        }catch (NullPointerException unused){
            return false;
        }

        return true;
    }

    public int hashCode(){
        int h = 0;
        Iterator<Entry<K, V>> i = entrySet().iterator();
        while (i.hasNext())
            h += i.next().hashCode();

        return h;
    }

    public String toString(){
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if(!i.hasNext())
            return "{}";
        StringBuilder s = new StringBuilder();
        s.append('{');
        for(;;){
            Entry<K, V> e = i.next();
            s.append(e.getKey() == this ? "(this map)" : e.getKey());
            s.append(e.getValue() == this ? "(this map)" : e.getValue());
            if(!i.hasNext()){
                return s.append("}").toString();
            }
            s.append(',').append(' ');
        }
    }

    protected Object clone() throws CloneNotSupportedException{
        AbstractMap<?, ?> result = (AbstractMap<?, ?>) super.clone();
        result.keySet = null;
        result.values = null;
        return result;
    }

    /**
     *实用的方法为SimeEntry 和 SimpleImmutableEntry.
     * 测试是否相等，检查是否为null
     */
    private static boolean eq(Object o1, Object o2){
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    public static class SimpleEntry<K, V>
        implements Entry<K, V>, Serializable
    {

        private final K key;
        private  V value;

        public SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public SimpleEntry(Entry<? extends K, ? extends  V> entry){
            this.key = entry.getKey();
            this.value = entry.getValue();
        }
        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return value;
        }

        public boolean equals(Object o){
            if(!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Entry<?, ?>) o;
            return eq(key, e.getKey()) && eq(value, e.getValue());
        }

        public int hashCode(){
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        public String toString(){
            return key + "=" + value;
        }
    }

    public static class  SimpleImmutableEntry<K ,V>
        implements Entry<K, V>, Serializable
    {
        private final K key;
        private final V value;

        public SimpleImmutableEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public SimpleImmutableEntry(Entry<? extends K, ? extends V> e) {
            this.key = e.getKey();
            this.value = e.getValue();
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            throw  new UnsupportedOperationException();
        }

        public boolean equals(Object o){
            if(!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Entry<?, ?>) o;
            return eq(key, e.getKey()) && eq(value, e.getValue());
        }

        public int hashCode(){
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        public String toString(){
            return key + "=" + value;
        }
    }

}
