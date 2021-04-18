package ua.com.alevel.service.impl;

import ua.com.alevel.service.MathSet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathSetImpl<E extends Number> implements MathSet<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private final E[] EMPTY_ELEMENT_DATA = (E[]) new Number[0];
    private final E[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = (E[]) new Number[DEFAULT_CAPACITY];
    private E[] elementData;
    private int capacity = 0;
    private int actualSize = 0;


    public MathSetImpl(){
        this.capacity = DEFAULT_CAPACITY;
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public MathSetImpl(int capacity){
        this.capacity = capacity;
        this.elementData = (E[]) new Number[capacity];
    }

    public MathSetImpl(E[] numbers){
        this.elementData = EMPTY_ELEMENT_DATA;
        addAll(numbers);
    }

    @SafeVarargs
    public MathSetImpl(E[] ... numbers){
        this.elementData = EMPTY_ELEMENT_DATA;
        for (E[] el :numbers) {
            addAll(el);
        }
    }

    public MathSetImpl(MathSet<E> numbers){
        this.elementData = EMPTY_ELEMENT_DATA;
        addAll(numbers);
    }

    @SafeVarargs
    public MathSetImpl(MathSet<E> ... numbers){
        this.elementData = EMPTY_ELEMENT_DATA;
        addAll(numbers);
    }


    public int size(){
        return this.actualSize;
    }

    @Override
    public void add(E n) {
        if (this.actualSize == this.capacity && checkBeforeAdd(n)) {
            elementData = this.grow();
            elementData[this.actualSize] = n;
            this.actualSize += 1;
        }
        else if(checkBeforeAdd(n)){
            this.elementData[this.actualSize] = n;
            this.actualSize += 1;
        }
    }

    @SafeVarargs
    @Override
    public final void add(E... n) {
        for (E element: n) {
            add(element);
        }
    }


    @Override
    public void join(MathSet<E> ms) {
        addAll(ms);
    }

    @SafeVarargs
    @Override
    public final void join(MathSet<E>... ms) {
        addAll(ms);
    }

    @Override
    public E get(int index){
        checkIndex(index);
        return elementData[index];
    }


    @Override
    public void sortDesc() {
        mergeSort("desc",this.elementData, this.actualSize);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        E[] sorted = toArray(firstIndex, lastIndex);
        mergeSort("desc",sorted, lastIndex - firstIndex);
        setSortedPart(sorted, firstIndex, lastIndex);
    }

    @Override
    public void sortDesc(E value) {
        int indexOfVal = indexOf(value);
        if(indexOfVal < 0) return;
        E[] sorted = toArray(indexOfVal, this.actualSize);
        mergeSort("desc",sorted, this.actualSize - indexOfVal);
        setSortedPart(sorted, indexOfVal, this.actualSize);
    }

    @Override
    public void sortAsc() {
        mergeSort("asc",this.elementData, this.actualSize);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        E[] sorted = toArray(firstIndex, lastIndex);
        mergeSort("asc",sorted, lastIndex - firstIndex);
        setSortedPart(sorted, firstIndex, lastIndex);
    }

    @Override
    public void sortAsc(E value) {
        int indexOfVal = indexOf(value);
        if(indexOfVal < 0) return;
        E[] sorted = toArray(indexOfVal, this.actualSize);
        mergeSort("asc",sorted, this.actualSize - indexOfVal);
        setSortedPart(sorted, indexOfVal, this.actualSize);
    }



    @Override
    public E getMax() {
        E[] sorted = (E[]) new Number[this.actualSize];
        System.arraycopy(this.elementData,0, sorted, 0, this.actualSize);
        mergeSort("asc",sorted, this.actualSize);
        return sorted[this.actualSize - 1];
    }

    @Override
    public E getMin() {
        E[] sorted = (E[]) new Number[this.actualSize];
        System.arraycopy(this.elementData,0, sorted, 0, this.actualSize);
        mergeSort("asc",sorted, this.actualSize);
        return sorted[0];
    }


    @Override
    public E getAverage() {
        return (E) this.sum().divide(new BigDecimal(actualSize), 2, RoundingMode.CEILING);
    }


    @Override
    public E getMedian() {
        E[] sorted = (E[]) new Number[this.actualSize];
        System.arraycopy(this.elementData,0, sorted, 0, this.actualSize);
        mergeSort("asc",sorted, this.actualSize);
        if(this.actualSize%2 == 1) return sorted [this.actualSize/2];
        else {
            BigDecimal m = new BigDecimal(sorted [this.actualSize/2].toString())
                    .add(new BigDecimal(sorted [this.actualSize/2 - 1].toString()))
                    .divide(new BigDecimal(2), 2, RoundingMode.CEILING);

            return (E) m;
        }
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Number[this.capacity];
        for (int i = 0; i < actualSize; i++) {
            array[i] = this.elementData[i];
        }
        return array;
    }

    @Override
    public E[] toArray(int firstIndex, int lastIndex) {
        checkIndex(firstIndex);
        checkIndex(lastIndex);
        int index = 0;
        E[] array = (E[]) new Number[lastIndex - firstIndex];
        for (int i = firstIndex; i < lastIndex; i++) {
            array[index] = this.elementData[i];
            index++;
        }
        return array;
    }

    @Override
    public MathSet<E> squash(int firstIndex, int lastIndex) {
        checkIndex(firstIndex);
        checkIndex(lastIndex);
        MathSet<E> set = new MathSetImpl<>();
        for (int i = 0; i < firstIndex; i++) {
            set.add(elementData[i]);
        }
        for (int i = lastIndex; i < actualSize; i++) {
            set.add(elementData[i]);
        }
        return set;
    }

    @Override
    public void clear() {
        this.capacity = 0;
        this.actualSize = 0;
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    @Override
    public void clear(E[] numbers) {
        int index;
        for (E num : numbers) {
            index = indexOf(num);
            if(index >= 0){
                remove(index);
            }
        }
    }

    private void remove(int index) {
        E[] newSet = (E[]) new Number[this.actualSize - 1];
        for (int i = 0; i < index; i++) {
            newSet[i] = this.elementData[i];
        }
        for (int i = index; i < this.actualSize - 1; i++) {
            newSet[i] = this.elementData[i+1];
        }
        this.elementData = newSet;
    }


    private void addAll(E[] numbers){
        for (E element : numbers) {
            add(element);
        }
    }

    private void addAll(MathSet<E> numbers){
        E[] num = numbers.toArray();
        addAll(num);
    }

    @SafeVarargs
    private final void addAll(MathSet<E>... numbers){
        for (MathSet<E> el :numbers) {
            addAll(el);
        }
    }


    private BigDecimal sum(){
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < this.actualSize; i++) {
            sum = new BigDecimal(String.valueOf(sum)).add(new BigDecimal(this.elementData[i].toString()));
        }
        return sum;
    }


    private int indexOf(E o) {
        for (int i = 0; i < actualSize; i++) {
            if (elementData[i].equals(o)) return i;
        }
        return -1;
    }


    private void setSortedPart(E[] sorted, int firstIndex, int lastIndex){
        int index = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            this.elementData[i] = sorted[index];
            index++;
        }
    }

    private void checkIndex(int i){
        if(i<0 || i >= this.actualSize) throw new IndexOutOfBoundsException("index " + i + " is out of bounds for length " + actualSize);
    }


    private E[] grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        if (oldCapacity <= 0 && this.elementData == EMPTY_ELEMENT_DATA) {
            this.capacity = minCapacity;
            return this.elementData = (E[]) new Number[minCapacity];
        } else {
            this.capacity = minCapacity;
            E[] bigger = (E[]) new Number[minCapacity];
            for (int i = 0; i < this.actualSize; i++) {
                bigger[i] = this.elementData[i];
            }
            return this.elementData = bigger;
        }
    }


    private E[] grow() {
        return this.grow(this.actualSize + 1);
    }


    private boolean checkBeforeAdd(E num){
        return !contains(num);
    }


    private boolean contains(E num) {
        for (int i = 0; i < actualSize; i++) {
            if (elementData[i].equals(num)) return true;
        }
        return false;
    }

    private int compare(E n, E m) {
        return new BigDecimal(n.toString()).compareTo(new BigDecimal(m.toString()));
    }


    private void mergeSort(String sort, E[] a, int actualSize) {
        if (actualSize < 2) {
            return;
        }
        int mid = actualSize / 2;
        E[] l = (E[]) new Number[mid];
        E[] r = (E[]) new Number[actualSize - mid];

        System.arraycopy(a, 0, l, 0, mid);
        if (actualSize - mid >= 0) System.arraycopy(a, mid, r, 0, actualSize - mid);
        mergeSort(sort, l, mid);
        mergeSort(sort, r, actualSize - mid);

       merge(sort, a, l, r, mid, actualSize - mid);
    }

    private void merge(String sort, E[] a, E[] l, E[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if( sort.equals("asc")) {
                if (compare(l[i], r[j]) <= 0) {
                    a[k++] = l[i++];
                } else {
                    a[k++] = r[j++];
                }
            }
            else if(sort.equals("desc")){
                if (compare(l[i], r[j]) >= 0) {
                    a[k++] = l[i++];
                } else {
                    a[k++] = r[j++];
                }
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
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
