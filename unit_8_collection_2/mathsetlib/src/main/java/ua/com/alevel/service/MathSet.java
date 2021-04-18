package ua.com.alevel.service;

public interface MathSet<E extends Number> {
    int size();
    void add(E n);
    void add(E ... n);
    void join(MathSet<E> ms);
    void join(MathSet<E> ... ms);
    void sortDesc();
    void sortDesc(int firstIndex, int lastIndex);
    void sortDesc(E value);
    void sortAsc();
    void sortAsc(int firstIndex, int lastIndex);
    void sortAsc(E value);
    E get(int index);
    E getMax();
    E getMin();
    E getAverage();
    E getMedian();
    E[] toArray();
    E[] toArray(int firstIndex, int lastIndex);
    MathSet<E> squash(int firstIndex, int lastIndex);
    void clear();
    void clear(E[] numbers);


}
