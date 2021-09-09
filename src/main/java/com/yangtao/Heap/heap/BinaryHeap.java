package com.yangtao.Heap.heap;

import com.yangtao.Heap.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉堆，最大堆的实现
 * 实现BinaryTreeInfo，为了可以打印出树形结构
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            size = elements.length;
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }
    public BinaryHeap(E[] elements)  {
        this(elements, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap() {
        this(null, null);
    }



    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elementNullCheck(element);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        E oldElement = elements[0];
        elements[0] = elements[--size];
        elements[size] = null;

        return oldElement;
    }

    @Override
    public E replace(E element) {
        elementNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    /**
     * 批量建堆
     */
    private void heapify() {
        // 自上而下的上滤
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}

        // 自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }
    /**
     * 让index位置的元素下滤
     * @param index
     */
    private void siftDown(int index) {
        int half = size >> 1;
        E element = elements[index];
        // 第一个叶子节点的索引 == 非叶子节点的数量
        // index < 第一个叶子节点的索引
        // 必须保证index位置是非叶子节点
        while (index < half) {
            int childIndex = index * 2 + 1;
            int rightIndex = childIndex + 1;
            E child = elements[childIndex];

            // 选出左右子节点中大的那一个
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) > 0 ) break;

            // 覆盖index位置的元素
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

    /**
     * 让index位置的元素向上过滤
     * @param index
     */
    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];

            if (compare(parent,  element) > 0) break;
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (capacity <= oldCapacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is empty!");
        }
    }

    private void elementNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null!");
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int)node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int)node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int)node];
    }
}
