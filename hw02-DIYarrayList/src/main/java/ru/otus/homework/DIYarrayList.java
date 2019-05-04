package ru.otus.homework;

import java.util.*;

public class DIYarrayList<T> implements List<T> {

    private int size;
    transient Object[] elementData;

    public DIYarrayList(int size) {
        if (size >= 0) {
            this.size = size;
            elementData = new Object[size];
        }
        else {
            throw new IllegalArgumentException("Illegal size: "+
                    size);
        }

    }

    public DIYarrayList() {
        this.size = 0;
        elementData = new Object[0];
    }

    ///////////////////////////////////////////////////////////
    // Имплементированные методы
    ///////////////////////////////////////////////////////////

    @Override
    public boolean add(T t) {
        size++;
        elementData = Arrays.copyOf(elementData, size);
        elementData[size-1] = t;
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T get(int index) {
        return (T) elementData[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = (T) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
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
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("iterator");
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
    public void add(int index, T element) {
        throw new UnsupportedOperationException("add_iT");
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
             return false;
         }

         @Override
         public boolean hasPrevious() {
             return false;
         }

         @Override
         public T previous() {
             return null;
         }

         @Override
         public int nextIndex() {
             return 0;
         }

         @Override
         public int previousIndex() {
             return 0;
         }

         @Override
         public void remove() {

         }

         @Override
         public void add(T t) {

         }
     }


}
