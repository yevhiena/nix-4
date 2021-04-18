package ua.com.alevel.service.impl;



import java.util.*;

public class OrderedList<E extends Comparable<E>> implements List<E>{
    private static final int DEFAULT_CAPACITY = 10;
    private final E[] EMPTY_ELEMENT_DATA = (E[]) new Comparable[0];
    private final E[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = (E[]) new Comparable[DEFAULT_CAPACITY];
    E[] elementData;
    private int capacity = 0;
    private int actualSize = 0;


    public OrderedList(){
        this.capacity = DEFAULT_CAPACITY;
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }


    public OrderedList(Collection<? extends E> collection) {
        this.capacity = DEFAULT_CAPACITY;
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        addAll(collection);
    }



    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(this.elementData, this.actualSize);
    }


    @Override
    public <T> T[] toArray(T[] a) {
        if(a == null) throw new NullPointerException();
        if (a.length < this.actualSize) {
            return (T[]) Arrays.copyOf(this.elementData, this.actualSize, a.getClass());
        } else {
            System.arraycopy(this.elementData, 0, a, 0, this.actualSize);
            if (a.length > this.actualSize) {
                a[this.actualSize] = null;
            }
            return a;
        }
    }

    @Override
    public boolean add(E e) {
        E[]  elementData= this.elementData;
        if (this.actualSize == elementData.length) {
            elementData = this.grow();
        }
        elementData[this.actualSize] = e;
        this.actualSize += 1;
        this.mergeSort(elementData, this.actualSize);
        this.elementData = elementData;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if(collection.size() == 0) return false;
        else {
            E[] a = (E[]) new Comparable[collection.size()];
            System.arraycopy(collection.toArray(), 0, a, 0, collection.size());
            E[]  elementData= this.elementData;
            if((this.actualSize + collection.size()) > capacity){
                elementData = this.grow();
            }
            System.arraycopy(a, 0, elementData, this.actualSize, collection.size());
            this.actualSize += collection.size();
            this.mergeSort(elementData, this.actualSize);
            this.elementData = elementData;
            return true;
        }
    }

    @Override
    public E remove(int i) {
        checkIndex(i);
        E removeElement = elementData[i];
        int oldSize = actualSize;
        E[] oldElementData = (E[]) new Comparable[capacity];
        System.arraycopy(elementData, 0, oldElementData, 0, oldSize);
        elementData = (E[]) new Comparable[capacity];
        System.arraycopy(oldElementData, 0 , elementData, 0, i);
        System.arraycopy(oldElementData, i + 1, elementData, i, oldSize - i - 1);
        actualSize -=1;

        return removeElement;
    }

    @Override
    public boolean remove(Object o) {
        int index = this.indexOf(o);
        if (index >= 0 ){
            remove(index);
            return true;
        }
        return false;

    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o: collection) {
           if(!this.contains(o)) return false;
        }
        return true;
    }


    @Deprecated
    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        checkIndex(i);
        return this.addAll(collection);
    }


    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean changed = false;
        for (Object o : collection) {
            int index = this.indexOf(o);
            while (index >=0){
                changed = true;
                this.remove(index);
                index = this.indexOf(o);
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean changed = false;
        for (E o: elementData) {
            if(!collection.contains(o)){
                this.remove(o);
                changed = true;
            }
        }
        return changed;
    }


    @Override
    public void clear() {
        this.capacity = 0;
        this.actualSize = 0;
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    @Override
    public E get(int i) {
        checkIndex(i);
        return elementData[i];
    }


    @Deprecated
    @Override
    public E set(int i, E e) {
        checkIndex(i);
        E prev = this.elementData[i];
        this.elementData[i] = e;
        mergeSort(this.elementData, this.actualSize);
        return prev;
    }

    @Deprecated
    @Override
    public void add(int i, E e) {
        checkIndex(i);
        this.add(e);
    }


    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < actualSize; i++) {
            if (elementData[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = actualSize - 1 ; i >= 0; i--) {
            if(elementData[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        checkIndex(i);
        return new ListItr(i);
    }

    @Override
    public List<E> subList(int i, int i1) {
        checkIndex(i);
        checkIndex(i1);
        List<E> subList = new OrderedList<>();
        for (int j = i; j < i1 ; j++) {
            subList.add(elementData[j]);
        }
        return subList;
    }


    private E[] grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        if (oldCapacity <= 0 && this.elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            return this.elementData = (E[]) new Object[minCapacity];
        } else {
            int newCapacity = this.actualSize + minCapacity;
            return this.elementData = Arrays.copyOf(this.elementData, newCapacity);
        }
    }

    private E[] grow() {
        return this.grow(this.actualSize + 1);
    }


    private void checkIndex(int i){
        if(i<0 || i >= this.actualSize) throw new IndexOutOfBoundsException("index " + i + " is out of bounds for length " + actualSize);
    }

    private void mergeSort(E[] a, int actualSize) {
        if (actualSize < 2) {
            return;
        }
        int mid = actualSize / 2;
        E[] l = (E[]) new Comparable[mid];
        E[] r = (E[]) new Comparable[actualSize - mid];

        System.arraycopy(a, 0, l, 0, mid);
        if (actualSize - mid >= 0) System.arraycopy(a, mid, r, 0, actualSize - mid);
        mergeSort(l, mid);
        mergeSort(r, actualSize - mid);

        merge(a, l, r, mid, actualSize - mid);
    }

    private void merge(
            E[] a, E[] l, E[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].compareTo(r[j]) <= 0) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    private class Itr implements Iterator<E> {

        int cursor;
        int lastRet;

        public Itr() {
            this.cursor =0;
            this.lastRet = -1;
        }


        @Override
        public boolean hasNext() throws ArrayIndexOutOfBoundsException{
            return cursor < OrderedList.this.actualSize && OrderedList.this.elementData[cursor] != null;
        }

        @Override
        public E next() throws NoSuchElementException{
            if(hasNext()) {
                this.lastRet = cursor;
                int i = cursor;
                cursor++;
                return OrderedList.this.elementData[i];
            }
            else throw new NoSuchElementException("No more elements in the list");
        }

        @Override
        public void remove() {
            if (lastRet<0) throw new IllegalStateException("Remove called before next");
            else {
                OrderedList.this.remove(this.lastRet);
                this.lastRet = -1;
                cursor -=1;

            }
        }
    }

    private class ListItr extends Itr implements ListIterator<E>{

        ListItr(int index) {
            super();
            this.cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override
        public E previous() {
            if (hasPrevious()) {
                throw new NoSuchElementException("No more previous elements");
            } else {
                this.lastRet = cursor;
                int i = cursor;
                cursor--;
                return OrderedList.this.elementData[i];
            }
        }
        @Override
        public int nextIndex() {
            return this.cursor;
        }

        @Override
        public int previousIndex() {
            return this.cursor - 1;
        }


        @Deprecated
        @Override
        public void set(E e) {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else {
                OrderedList.this.set(this.lastRet, e);
            }
        }

        @Deprecated
        @Override
        public void add(E e) {
            int i = this.cursor;
            OrderedList.this.add(e);
            this.cursor = i + 1;
            this.lastRet = -1;
        }
    }



    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append('[');
        for (int i = 0; i < actualSize; i++) {
            str.append(elementData[i].toString());
            if(i != actualSize -1){
                str.append(", ");
            }
        }
        str.append(']');
        return str.toString();
    }


}
