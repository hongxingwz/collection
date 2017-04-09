//package com.jianglei.util;
//
//import javax.swing.tree.TreeNode;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.Objects;
//
///**
// * 基于哈希表的Map接口实现。
// * 此实现提供所有可选的map操作，允许null作为key和value.
// * (HashMap和Hashtable大至相同，除了他不是同步的并且允许null)
// * 此类不保证映射的顺序，特别是它不保证该顺序恒久不变。
// * Created by jianglei on 2017/4/9.
// */
//public class HashMap<K, V> extends AbstractMap<K,V>
//    implements Map<K, V>, Cloneable, Serializable{
//    static final int DEFAULT_INITIAL_CAPACIY = 1 << 4;
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//    static final float DEFAULT_LOAD_FACTOR = 0.75F;
//    static final int TREEIFY_THRESHOLD = 8;
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//    static class Node<K, V> implements Map.Entry<K, V> {
//        final int hash;
//        final K key;
//        V value;
//        Node<K, V> next;
//
//        Node(int hash, K key, V value, Node<K, V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        @Override
//        public K getKey() {
//            return key;
//        }
//
//        @Override
//        public V getValue() {
//            return value;
//        }
//
//        @Override
//        public V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//    static Class<?> comparableClassFor(Object x) {
//        if (x instanceof Comparable) {
//            Class<?> c;
//            Type[] ts, as;
//            Type t;
//            ParameterizedType p;
//            if ((c = x.getClass()) == String.class) {
//                return c;
//            }
//            if ((ts = c.getGenericInterfaces()) != null) {
//                for (int i = 0; i < ts.length; ++i) {
//                    if (((t = ts[i]) instanceof ParameterizedType) &&
//                            ((p = (ParameterizedType) t).getRawType() ==
//                                    Comparable.class) &&
//                            (as = p.getActualTypeArguments()) != null &&
//                            as.length == 1 && as[0] == c) // type arg is c
//                        return c;
//                }
//            }
//        }
//        return null;
//    }
//
//    static int compareComparables(Class<?> kc, Object k, Object x) {
//        return (x == null || x.getClass() != kc ? 0 :
//                ((Comparable) x).compareTo(x));
//    }
//
//    static final int tableSizeFor(int cap) {
//        int n = cap - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }
//
//    transient Node<K, V>[] table;
//    transient Set<Map.Entry<K, V>> entrySet;
//    transient int size;
//    transient int modCount;
//    int threshold;
//    final float loadFactor;
//
//    public HashMap(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal initial capacity: " +
//                    initialCapacity);
//        if (initialCapacity > MAXIMUM_CAPACITY)
//            initialCapacity = MAXIMUM_CAPACITY;
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal load factor: " +
//                    loadFactor);
//        this.loadFactor = loadFactor;
//        this.threshold = tableSizeFor(initialCapacity);
//    }
//
//    public HashMap(int initialCapacity) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//    public HashMap(Map<? extends K, ? extends V> m) {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);
//    }
//
//    private void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
//        int s = m.size();
//        if (s > 0) {
//            if (table == null) {
//                float ft = ((float) s / loadFactor) + 1.0F;
//                int t = ((ft < (float) MAXIMUM_CAPACITY) ?
//                        (int) ft : MAXIMUM_CAPACITY);
//                if (t > threshold) {
//                    threshold = tableSizeFor(t);
//                }
//            } else if (s > threshold) {
//                resize();
//            }
//            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
//                K key = e.getKey();
//                V value = e.getValue();
//                putVal(hash(key), key, value, false, evict);
//            }
//        }
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    public V get(Object key) {
//        Node<K, V> e;
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }
//
//    final Node<K, V> getNode(int hash, Object key) {
//        Node<K, V>[] tab;
//        Node<K, V> first, e;
//        int n;
//        K k;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (first = tab[(n - 1) & hash]) != null) {
//            if (first.hash == hash &&
//                    ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//
//        }
//        if ((e = first.next) != null) {
//            if (first instanceof TreeNode)
//                return ((TreeNode < K,V > first).getTreeNode(hash, key);
//            do {
//                if (e.hash == hash &&
//                        ((k = e.key) == key || (key != null && key.equals(k)))) {
//                    return e;
//                }
//            } while ((e = e.next) != null);
//        }
//
//        return null;
//    }
//
//    public boolean containsKey(Object key) {
//        return getNode(hash(key), key) != null;
//    }
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K, V>[] tab;
//        Node<K, V> p;
//        int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0) {
//            n = (tab = resize()).length;
//        }
//        if ((p = tab[i = (n - 1) & hash]) == null) {
//            tab[i] = newNode(hash, key, value, null);
//        } else {
//            Node<K, V> e;
//            K k;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k)))) {
//                e = p;
//            } else if (p instanceof TreeNode) {
//                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
//            } else {
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1)
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        break;
//                    }
//                    p = e;
//                }
//            }
//            if (e != null) {
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold) {
//            resize();
//        }
//        afterNodeInsertion(evict);
//        return null;
//
//
//    }
//
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    final Node<K, V>[] resize(){
//        Node<K, V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        if(oldCap > 0){
//            if(oldCap > MAXIMUM_CAPACITY){
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            }else if((newCap = oldCap << 1) < MAXIMUM_CAPACITY
//                    && oldCap >= DEFAULT_INITIAL_CAPACIY){
//                newThr = oldThr << 1;
//            }
//        }else if(oldThr > 0){
//            newCap = oldThr;
//        }else {
//            newCap = DEFAULT_INITIAL_CAPACIY;
//            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACIY);
//        }
//        if(newThr == 0){
//            float ft = (float)newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
//                    (int)ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        Node<K, V>[] newTab = (Node<K, V>[])new Node[newCap];
//        table = newTab;
//        if(oldTab != null){
//            for(int j = 0; j < oldCap; ++j){
//                Node<K, V> e;
//                if((e = oldTab[j]) != null){
//                    oldTab[j] = null;
//                    if(e.next == null){
//                        newTab[e.hash & (newCap - 1)] = e;
//                    }else if(e instanceof TreeNode){
//                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
//                    }else {
//                        Node<K, V> loHead = null, loTail = null;
//                        Node<K, V> hiHead = null, hiTail = null;
//                        Node<K, V> next;
//
//                        do{
//                            next = e.next;
//                            if((e.hash & oldCap) == 0){
//                                if(loTail == null){
//                                    loHead = e;
//                                }else{
//                                    loTail.next = e;
//                                }
//                                loTail = e;
//                            }else {
//                                if(hiTail == null){
//                                    hiHead = e;
//                                }else {
//                                    hiTail.next = e;
//                                }
//                                hiTail = e;
//                            }
//                        }while ((e = next) != null);
//                        if(loTail != null){
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if(hiTail != null){
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//    final void treeifyBin(Node<K, V>[] tab, int hash){
//        int n, index;
//        Node<K, V> e;
//        if(tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY){
//            resize();
//        }else if((e = tab[index = (n - 1) & hash]) != null){
//            TreeNode<K, V> hd = null, tl = null;
//            do{
//                TreeNode<K, V> p = replacementTreeNode(e, null);
//                if(tl == null){
//                    hd = p;
//                }else {
//                    p.prev = tl;
//                    tl.next = p;
//                }
//
//                tl = p;
//            }while ((e = e.next) != null);
//            if((tab[index] = hd) != null){
//                hd.treeify(tab);
//            }
//        }
//    }
//
//    public void putAll(Map<? extends K, ? extends V> m){
//        putMapEntries(m, true);
//    }
//
//    public V remove(Object key){
//        Node<K, V> e;
//        return (e = removeNode(hash(key), key, null, false, true)) == null ?
//                null : e.value;
//    }
//
//    final Node<K, V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable){
//        Node<K, V>[] tab;
//        Node<K, V> p;
//        int n, index;
//        if((tab = table) != null && (n = tab.length) > 0 &&
//                (p = tab[index = (n - 1) & hash]) != null){
//            Node<K, V> node = null, e;
//            K k;
//            V v;
//            if(p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k)))){
//                node = p;
//            }else if((e = p.next) != null){
//                if(p instanceof TreeNode){
//                    node = ((TreeNode<K, V>)p).getTreeNode(hash, key);
//                }else {
//                    do{
//                        if(e.hash == hash &&
//                                ((k = e.key) == key || (key != null && key.equals(k)))){
//                            node = e;
//                            break;
//                        }
//                        p = e;
//                    }while ((e = e.next) != null);
//                }
//            }
//            if(node != null && (!matchValue || (v = node.value) == value ||
//                    (value != null && value.equals(v)))){
//                if(node instanceof TreeNode){
//                    ((TreeNode<K, V>)node).removeTreeNode(this, tab, movable);
//                }else if(node == p){
//                    tab[index] = node.next;
//                }else {
//                    p.next = node.next;
//                }
//                ++modCount;
//                --size;
//                afterNodeRemoval(node);
//                return node;
//            }
//        }
//        return null;
//    }
//
//    public void clear(){
//        Node<K, V>[] tab;
//        modCount++;
//        if((tab = table) != null && size > 0){
//            size = 0;
//            for(int i = 0; i < tab.length; ++i){
//                tab[i] = null;
//            }
//        }
//    }
//
//    public boolean containsValue(Object value){
//        Node<K, V>[] tab;
//        V v;
//        if((tab = table) != null && size > 0){
//            for(int i = 0; i < tab.length; ++i){
//                for(Node<K, V> e = tab[i]; e != null; e = e.next){
//                    if((v = e.value) == value || (value != null && value.equals(v))){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public Set<K> keySet(){
//        Set<K> ks = keySet;
//        if(ks == null){
//            ks = new KeySet();
//            keySet = ks;
//        }
//        return ks;
//    }
//
//    final class KeySet extends AbstractSet<K>{
//        public final int size(){
//            return size;
//        }
//        public final void clear(){
//            HashMap.this.clear();
//        }
//
//        @Override
//        public Iterator<K> iterator() {
//            return new KeyIterator();
//        }
//
//        public final boolean contains(Object o){
//            return containsKey(o);
//        }
//        public final boolean remove(Object key){
//            return removeNode(hash(key), key, null, false, true) != null;
//        }
//
//
//    }
//
//    public Collection<V> values(){
//        Collection<V> vs = values;
//        if(vs == null){
//            vs = new Values();
//            values = vs;
//        }
//        return vs;
//    }
//
//    final class Values extends AbstractCollection<V>{
//
//        @Override
//        public Iterator<V> iterator() {
//            return new ValueIterator();
//        }
//
//        @Override
//        public int size() {
//            return size;
//        }
//
//        public final void clear(){
//            HashMap.this.clear();
//        }
//
//        public final boolean contains(Object o){
//            return containsValue(o);
//        }
//
//    }
//
//}
