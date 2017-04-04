package com.jianglei.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.RandomAccess;

/**
 * Created by jianglei on 2017/4/4.
 */
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, Serializable{

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

    public ArrayList(int initialCapacity){
        if (initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
           throw new IllegalArgumentException("Illegal Capacity: " +
                                                initialCapacity);
        }
    }

    public ArrayList(Collection<? extends E> c){
        elementData = c.toArray();
        if((size = elementData.length) != 0){
            if(elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    public void trimToSize(){
        modCount++;
        if(size < elementData.length){
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    public void ensureCapacity(int minCapacity){
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                ? 0
                : DEFAULT_CAPACITY;
        if(minCapacity > minExpand){
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity){
        if(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        if(minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if(newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if(newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public static int hugeCapacity(int minCapacity){
        if(minCapacity < 0)
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    public  int indexOf(Object o){
        if(o == null){
            for(int i = 0; i < size; i++)
                if(elementData[i] == null)
                    return i;
        } else {
            for(int i = 0; i < size; i++){
                if(o.equals(elementData[i]))
                    return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o){
        if(o == null){
            for(int i = size - 1; i >= 0; i--)
                if(elementData[i] == null)
                    return i;
        } else {
            for(int i = size - 1; i >= 0; i--)
                if(o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public Object clone(){
        try{
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public Object[] toArray(){
        return Arrays.copyOf(elementData, size);
    }
    public <T> T[] toArray(T[] a){
        if(a.length < size)
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if(a.length > size)
            a[size] = null;
        return a;
    }

    E elementData(int index){
        return (E) elementData[index];
    }


    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    public E set(int index, E element){
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    public boolean add(E e){
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    public void add(int index, E element){
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                        size - index);
        elementData[index] = element;
        size++;
    }

    public E remove(int index){
        rangeCheck(index);
        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if(numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                        numMoved);
        elementData[--size] = null;
        return oldValue;
    }

    public boolean remove(Object o){
        if(o == null){
            for(int index = 0; index < size; index++)
                if(elementData[index] == null){
                    fastRemove(index);
                    return true;
                }
        } else {
            for(int index = 0; index < size; index++){
                if(o.equals(elementData[index])){
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index){
        modCount++;
        int numMoved = size - index - 1;
        if(numMoved > 0){
            System.arraycopy(elementData, index + 1, elementData, index,
                        numMoved);
        }
        elementData[--size] = null;
    }

    public void clear(){
        modCount++;

        for(int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    public boolean addAll(Collection<? extends E> c){
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    public boolean addAll(int index, Collection<? extends E> c){
        rangeCheckForAdd(index);
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);

        int numMoved = size - index;
        if(numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew
                            , numMoved);
        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    ...

}
