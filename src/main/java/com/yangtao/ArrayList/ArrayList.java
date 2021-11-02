package com.yangtao.ArrayList;

public class ArrayList <E> {
    private int size;

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }


    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }


    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }


    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ;
    }

    public void add(E element) {
        add(element, size);
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    public void add(E element, int index) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        E oldELe = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldELe;
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) return;
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal!");
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is illegal!");
        }
    }
}
