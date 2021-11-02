package com.yangtao.LinkedList;

public class DuLinkedList <E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;


    @Override
    public void clear() {
        first = null;
        last = null;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        return node(index).val;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.val;
        node.val = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(last, null, element);
            // 添加是第一个元素
            if (oldLast == null) {
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev, next, element);
            next.prev = newNode;

            if (prev == null) {
                first = newNode;
            } else {
                prev.next = newNode;
            }
        }

        size++;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> target = null;
        if (index > (size >> 1)) {
            target = last;
            for (int i = size - 1; i > index; i--) {
                target = target.prev;
            }
            return target;
        } else {
            target = first;
            for (int i = 0; i < index; i++) {
                target = target.next;
            }
            return target;
        }
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
        size--;
        return node.val;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.val == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.val)) return i;
                node = node.next;
            }
        }

        return -1;
    }

    static class Node<E> {
        Node prev;
        Node next;
        E val;

        public Node(Node prev, Node next, E val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }
}
