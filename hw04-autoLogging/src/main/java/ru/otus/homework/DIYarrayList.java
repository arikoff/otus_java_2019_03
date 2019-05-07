package ru.otus.homework;

import java.util.*;

public class DIYarrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private int size;
    transient Object[] elementData;

    public DIYarrayList(int size) {
        if (size >= 0) {
            this.size = size;
            elementData = new Object[size];
        }
        else {
            throw new IllegalArgumentException("Illegal size: "+ size);
        }
    }

    public DIYarrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    ///////////////////////////////////////////////////////////
    // Имплементированные методы
    ///////////////////////////////////////////////////////////

    @Log
    @Override
    public boolean add(T t) {
        add(t, elementData, size);
        return true;
    }

    @Log
    @Override
    public int size() {
        return this.size;
    }

    @Log
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    @Log
    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldValue = (T) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Log
    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Log
    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
    }

    ///////////////////////////////////////////////////////////
    // Приватные методы
    ///////////////////////////////////////////////////////////

    private void add(T t, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = t;
        size = s + 1;
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                newCapacity(minCapacity));
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    ///////////////////////////////////////////////////////////
    // Неимплементированные методы
    ///////////////////////////////////////////////////////////

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("contains");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("toArray_T1");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("containsAll");
    }

     @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("addAll_i");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("remove_i");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("indexOf");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("lastIndexOf");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("listIterator_i");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList");
    }

/////////////////////////////////////////////////////////////////////////////////////

     private class ListItr implements ListIterator<T> {

        int cursor;
        int lastRet = -1;

        public ListItr(int index) {
            this.cursor = index;
        }

         @Override
         public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = DIYarrayList.this.elementData;
            cursor = i + 1;
            return (T) elementData[lastRet = i];
        }

         @Override
         public void set(T t) {
             if (lastRet < 0)
                 throw new IllegalStateException();

             try {
                 DIYarrayList.this.set(lastRet, t);
             } catch (IndexOutOfBoundsException ex) {
                 throw new ConcurrentModificationException();
             }

         }

         @Override
         public boolean hasNext() {
             return cursor != size;
         }

         @Override
         public boolean hasPrevious() {
             throw new UnsupportedOperationException("ListItr_hasPrevious");
         }

         @Override
         public T previous() {
             throw new UnsupportedOperationException("ListItr_previous");
         }

         @Override
         public int nextIndex() {
             throw new UnsupportedOperationException("ListItr_nextIndex");
         }

         @Override
         public int previousIndex() {
             throw new UnsupportedOperationException("ListItr_previousIndex");
         }

         @Override
         public void remove() {
             throw new UnsupportedOperationException("ListItr_remove");
         }

         @Override
         public void add(T t) {
             throw new UnsupportedOperationException("ListItr_add");
         }
     }


}
