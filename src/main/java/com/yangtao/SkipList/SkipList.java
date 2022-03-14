package com.yangtao.SkipList;

import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

import java.util.Comparator;

/**
 * @Author: pyhita
 * @Date: 2022/3/14
 * @Descrption: com.yangtao.SkipList
 * @Version: 1.0
 */
public class SkipList<K, V> {

    private static final int MAX_LEVEL = 32;
    private static final double P = 0.25;
    private int size;
    private Comparator<K> comparator;
    /**
     * 有效层数
     */
    private int level;
    /**
     * 不存放任何K-V
     */
    private Node<K, V> first;


    public SkipList(Comparator comparator) {
        this.comparator = comparator;
        first = new Node<>(null, null, MAX_LEVEL);
    }

    public SkipList() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {
        keyCheck(key);

        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null
                    && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) return node.nexts[i].value;
        }

        return null;
    }

    public V put(K key, V value) {
        keyCheck(key);

        // 记录前驱节点
        Node<K, V>[] prev = new Node[level];
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null
                    && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }

            // check 是否有key相等
            if (cmp == 0) {
                V oldV = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldV;
            }
        }

        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, value, newLevel);

        for (int i = 0; i < newLevel; i++) {
            if (i >= level) {
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prev[i].nexts[i];
                prev[i].nexts[i] = newNode;
            }
        }
        size++;
        level = Math.max(level, newLevel);

        return null;
    }

    public V remove(K key, V value) {
        keyCheck(key);

        Node<K, V>[] prev = new Node[level];
        Node<K, V> node = first;
        boolean exist = false;
        for (int i = level - 1; i >= 0; i++) {
            int cmp = -1;
            while (node.nexts[i] != null
                    && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            prev[i] = node;
            if (cmp == 0) exist = true;
        }

        if (!exist) return null;

        size--;
        Node<K, V> removeNode = node.nexts[0];
        for (int i = 0; i < removeNode.nexts.length; i++) {
            prev[i].nexts[i] = removeNode.nexts[i];
        }

        int newLevel = level;
        if (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }

        return removeNode.value;
    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    private void keyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null!");
        }
    }

    public int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) :
                ((Comparable) k1).compareTo(k2);
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nexts = new Node[level];
        }
    }



}
