package com.jianglei.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * 这个类提供了Collection接口的骨架实现，以最大限度地减少了
 * 实现此接口的工作。
 *
 * 要实现一个不可修改的collection，程序员只需要继承这个类并且提供对
 * iterator 和 size 的实现。（iterator方法返回的迭代器必须要实现
 * hasNext 和 next 方法
 *
 * 要实现一个可以修改的集合，程序员一定要另外实现这个类的add方法
 *（否则，会抛出UnsupportedOperationException),iterator方法返回
 * 的迭代器必须要另外实现其remove方法
 *
 * 按照Collection接口规范中的建议，程序员通常应该提供一个无参的构造器
 * 和一个Collection参数的构造器
 *
 * 此类中每个非抽象方法的文档详细描述了其实现。如果要实现的collection允许
 * 更有效的实现，则可以重写这此方法的每个方法
 *
 * 这个类是Java Collections Framework 中的一员
 * Created by jianglei on 2017/4/3.
 */
public abstract class AbstractCollection<E> implements Collection<E> {
    /**
     * 唯一构造器，（由子类构造方法调用，通常是隐式的）
     */
    protected AbstractCollection(){
    }

    /**
     * 返回在此collection中的元素上进行迭代的迭代器。
     * @return 在此collection中的元素上进行迭代的迭代器。
     */
    public abstract Iterator<E> iterator();

    public abstract int size();

    /**
     * 这个实现返回 size() == 0
     */
    public boolean isEmpty(){
        return  size() == 0;
    }

    /**
     * 如果此collection包含指定的元素，则返回true。更确切地讲，当且
     * 仅当此collection至少包含一个满足（o == null ? e == null :
     * o.equals(e))的元素e时，返回true。
     * @param o 测试在些collection中是否存在的元素。
     *
     * @throws ClassCastException
     * @throws NullPointerException
     */
    public boolean contains(Object o){
        Iterator<E> it = iterator();
        if(o == null){
            while (it.hasNext())
                if(it.next() == null)
                    return true;
        } else {
            while (it.hasNext())
                if(o.equals(it.next()))
                    return true;
        }

        return false;
    }

    /**
     * 返回包含此collection中所有元素的数组。如果collection对其迭代器返回的元素
     * 顺序做出了某些保证，那么此方法必须以相同的顺序返回这些元素。
     *
     * 返回的数组将是"安全的"，因为此collection并不维护对数组的任何引用。（换句话说，
     * 即使collection受到数组的支持，此方法也必须分配一个新的数组）。因此，调用者可以随意
     * 修改返回的数组。
     *
     * 此方法充当了基于数组的API 与 基于 collection 的 API 之间的桥梁。
     *
     * 此实现返回一个数组，它包含此collection的迭代器返回的所有元素，这些元素的排列
     * 顺序与数组的连续元素存储顺序相同，都是从索引0开始。返回数组的长度等于迭代器返回
     * 的元素数，即使此collection的大小发生更改也是如此，这种情况可能发生在collection
     * 允许在迭代期间进行并发修改时。size方法只是作为一个优化提示被调用：即使迭代器返回
     * 不同的元素数，也会返回正确的结果。
     *
     * 此方法等效于：
     *  List<E> list = new ArrayList<E>(size());
     *  for(E e: this)
     *      list.add(e);
     *  return list.toArray();
     * @return list.toArray();
     */
    public Object[] toArray(){
        //Estimate size of array; be prepared to see more or fewer elements
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for(int i = 0; i < r.length; i++){
            if(! it.hasNext())
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    public <T> T[] toArray(T[] a){
        int size = size();
        T[] r = a.length >= size ? a :
                (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = iterator();
        for(int i = 0; i < r.length; i++) {
            if (!it.hasNext()) {
                if (a == r) {
                    r[i] = null;

                } else if (a.length < i) {
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            r[i] = (T) it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }
    /**
     * 数组分配的最大值
     * 一些 VMs 在数组中保留一些头信息
     * 尝试分配更大的数组可能造成OutOfMemoryError:
     * 因为数组的大小超过了 VM的 限制
     */

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    // TODO
    private static <T> T[] finishToArray(T[] r, Iterator<?> it){
        int i = r.length;
        while(it.hasNext()){
            int cap = r.length;
            if(i == cap){
                int newCap = cap + (cap >> 1) + 1;
                if(newCap - MAX_ARRAY_SIZE > 0)
                    newCap = hugeCapacity(cap + 1);
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T)it.next();

        }

        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    private static int hugeCapacity(int minCapcity){
        if(minCapcity < 0)
            throw new OutOfMemoryError
                    ("Required array size too large");
        return (minCapcity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    //Modification Operations

    /**
     * {@inheritDoc}
     *
     * <p>这个实现总是抛出</p>
     * <tt>UnsupoortedOperationException</tt>
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     * @throws IllegalStateException         {@inheritDoc}
     */
    public boolean add(E e){
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * <p>这个实现迭代整个集合寻找指定的元素。
     * 如果找到了指定的元素，使用迭代器的remove方法从
     * 集合中的删除元素</p>
     *
     * <p>注意这个实现抛出一个
     * <tt>UnsupportedOperationException</tt>如果此集合的
     * iterator方法返回的迭代器不支持remove方法并且这个集合包含指定的对象。
     * </p>
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public boolean remove(Object o){
        Iterator<E> it = iterator();
        if(o == null){
            while (it.hasNext()){
                if(it.next() == null){
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()){
                if(o.equals(it.next())){
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * <p>这个实现迭代指定的集合，
     * 检测每个迭代器返回的元素为了决断它是否在此集合中包含。
     * 如果所有的元素都包含返回true，否则返回 false</p>
     *
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     *
     * @see #contains(Object)
     */
    public boolean containsAll(Collection<?> c){
        for(Iterator<?> it = c.iterator(); it.hasNext(); ){
            if(!contains(it.next()))
                return false;
        }
        return true;
    }

    public boolean addAll(Collection<? extends  E> c){
        boolean modified = false;
        Iterator<E> it = (Iterator<E>) c.iterator();
        for(; it.hasNext(); ){
            if(add(it.next()))
                modified = true;
        }
        return modified;
    }

    public boolean removeAll(Collection<?> c){
        Objects.requireNonNull(c);
        boolean modifed = false;
        Iterator<E> it = iterator();
        while (it.hasNext()){
            if(c.contains(it.next())){
                it.remove();
                modifed = true;
            }
        }
        return modifed;
    }

    public boolean retainAll(Collection<?> c){
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()){
            if(!c.contains(it.next())){
                it.remove();
                modified = true;
            }
        }
        return modified;
    }
    public void clear(){
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
    public String toString(){
        Iterator<E> it = iterator();
        if(!it.hasNext())
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(;;){
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if(! it.hasNext())
                return sb.append("]").toString();
            sb.append(',').append(' ');
        }
    }

}
