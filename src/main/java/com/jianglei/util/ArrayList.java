package com.jianglei.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

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

    public ArrayList(){
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
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

    protected void removeRange(int fromIndex, int toIndex){
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                        numMoved);
        //clear to let GC do its work
        int newSize = size - (toIndex - fromIndex);
        for(int i = newSize; i < size; i++){
            elementData[i] = null;
        }
        size = newSize;
    }
    private  void rangeCheck(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }
    private void rangeCheckForAdd(int index){
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index){
        return "Index: " + index + ", Size: " + size;
    }

    public boolean removeAll(Collection<?> c){
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    public boolean retainAll(Collection<?> c){
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    private boolean batchRemove(Collection<?> c, boolean complement){
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for(; r < size; r++){
                if(c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
            }
        } finally {
            if(r != size){
                System.arraycopy(elementData, r,
                                elementData, w,
                                size - r);
            }
            if(w != size){
                for(int i = w; i < size; i++){
                    elementData[i] = null;
                }
                modCount += size - w;
                size = w;
                modified = true;
            }
        }

        return modified;
    }

    private void writeObject(ObjectOutputStream s) throws IOException{
        int expectedModCount = modCount;
        s.defaultWriteObject();

        s.writeInt(size);

        for (int i = 0; i < size; i++) {
            s.writeObject(elementData[i]);
        }

        if(modCount != expectedModCount){
            throw new ConcurrentModificationException();
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;
        s.defaultReadObject();

        s.readInt();

        if(size > 0){
            ensureCapacityInternal(size);

            Object[] a = elementData;
            for(int i = 0; i < size; i++){
                a[i] = s.readObject();
            }
        }
    }

    public ListIterator<E> listIterator(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return new ListItr(index);
    }

    public ListIterator<E> listIterator(){
        return new ListItr(0);
    }

    public Iterator<E> iterator(){
        return new Itr();
    }

    private class Itr implements Iterator<E>{
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;


        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            checkForComodification();
            int i = cursor;
            if(i >= size){
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayList.this.elementData;
            if(i >= elementData.length){
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;

            return (E) elementData[lastRet = i];
        }

        public void remove(){
            if(lastRet < 0){
                throw new IllegalStateException();
            }
            checkForComodification();
            try{
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification(){
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<E>{
        ListItr(int index){
            super();
            cursor = index;
        }
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }
        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public E privious() {
            checkForComodification();
            int i = cursor - 1;
            if(i < 0)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if(i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) elementData[lastRet - 1];

        }


        @Override
        public void set(E e) {
            if(lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();
            try {
                ArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                ArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }

        }
    }

    public List<E> subList(int fromIndex, int toIndex){
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubList(this, 0, fromIndex, toIndex);
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size){
        if(fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if(toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if(fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                                                ") > toIndex(" + toIndex + ")");
    }

    private class SubList extends AbstractList<E> implements RandomAccess{
        private final AbstractList<E> parent;
        private final int parentOffset;
        private final int offset;
        int size;

        SubList(AbstractList<E> parent,
                int offset, int fromIndex, int toIndex){
            this.parent = parent;
            this.parentOffset = fromIndex;
            this.offset = offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = ArrayList.this.modCount;
        }

        public E set(int index, E e){
            rangeCheck(index);
            checkForComodification();
            E oldValue = ArrayList.this.elementData(offset + index);
            ArrayList.this.elementData[offset + index] = e;
            return oldValue;
        }

        public E get(int index){
            rangeCheck(index);
            checkForComodification();
            return ArrayList.this.elementData(offset + index);
        }

        public int size(){
            checkForComodification();
            return this.size;
        }

        public void add(int index, E e){
            rangeCheckForAdd(index);
            checkForComodification();
            parent.add(parentOffset + index, e);
            this.modCount = parent.modCount;
            this.size++;
        }

        public E remove(int index){
            rangeCheck(index);
            checkForComodification();
            E result = parent.remove(parentOffset + index);
            this.modCount = parent.modCount;
            this.size--;
            return result;
        }

        protected void removeRange(int fromIndex, int toIndex){
            checkForComodification();
            parent.removeRange(parentOffset + fromIndex,
                                parentOffset + toIndex);
            this.modCount = parent.modCount;
            this.size -= toIndex - fromIndex;
        }

        public boolean addAll(Collection<? extends E> c){
            return addAll(this.size, c);
        }

        public boolean addAll(int index, Collection<? extends E> c){
            rangeCheckForAdd(index);
            int cSize = c.size();
            if(cSize == 0){
                return false;
            }
            checkForComodification();
            parent.addAll(parentOffset + index, c);
            this.modCount = parent.modCount;
            this.size += cSize;
            return true;
        }

        public Iterator<E> iterator(){
            return listIterator();
        }

        public ListIterator<E> listIterator(final int index){
            checkForComodification();
            rangeCheckForAdd(index);
            final int offset = this.offset;

            return new ListIterator<E>() {
                int cursor = index;
                int lastRet = -1;
                int expectedModCount = ArrayList.this.modCount;

                @Override
                public boolean hasNext() {
                    return cursor != SubList.this.size;
                }

                @Override
                public E next() {
                    checkForComodification();
                    int i = cursor;
                    if(i >= SubList.this.size)
                        throw new NoSuchElementException();
                    Object[] elementData = ArrayList.this.elementData;
                    if(offset + i >= elementData.length)
                        throw new ConcurrentModificationException();
                    cursor = i + 1;
                    return (E) elementData[offset + (lastRet = i)];
                }

                @Override
                public boolean hasPrevious() {
                    return cursor != 0;
                }

                @Override
                public E privious() {
                    checkForComodification();
                    int i = cursor - 1;
                    if(i < 0)
                        throw new NoSuchElementException();
                    Object[] elementData = ArrayList.this.elementData;
                    if(offset + i >= elementData.length)
                        throw new ConcurrentModificationException();
                    cursor = i;
                    return (E) elementData[offset + (lastRet = i)];
                }



                @Override
                public int nextIndex() {
                    return cursor;
                }

                @Override
                public int previousIndex() {
                    return cursor - 1;
                }

                @Override
                public void remove() {
                    if(lastRet < 0)
                        throw new IllegalStateException();
                    checkForComodification();

                    try{
                        SubList.this.remove(lastRet);
                        cursor = lastRet;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    }catch (IndexOutOfBoundsException ex){
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public void set(E e) {
                    if(lastRet < 0)
                        throw new IllegalStateException();
                    checkForComodification();

                    try{
                        ArrayList.this.set(offset + lastRet, e);
                    }catch (IndexOutOfBoundsException ex){
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public void add(E e) {
                    checkForComodification();

                    try {
                        int i = cursor;
                        SubList.this.add(i, e);
                        cursor = i + 1;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex){
                        throw new ConcurrentModificationException();
                    }
                }

                final void checkForComodification(){
                    if(expectedModCount != ArrayList.this.modCount)
                        throw new ConcurrentModificationException();
                }
            };
        }

        public List<E> subList(int fromIndex, int toIndex){
            subListRangeCheck(fromIndex, toIndex, size);
            return new SubList(this, offset, fromIndex, toIndex);
        }

        private void rangeCheck(int index){
            if(index < 0 || index >= this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private void rangeCheckForAdd(int index){
            if(index < 0 || index > this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private String outOfBoundsMsg(int index){
            return "Index: " + index + ", Size: " + this.size;
        }

        private void checkForComodification(){
            if(ArrayList.this.modCount != this.modCount)
                throw new ConcurrentModificationException();
        }
    }

}
