package com.yangtao.BloomFilter;

import sun.rmi.runtime.Log;

/**
 * @Author: pyhita
 * @Date: 2022/3/6
 * @Descrption: com.yangtao.BloomFilter
 * @Version: 1.0
 */
public class BloomFilter<T> {

    /**
     * 二进制向量的长度
     */
    private int bitSize;

    /**
     *二进制向量
     */
    private long[] bits;

    /**
     *哈希函数的个数
     */
    private int hashSize;

    /**
     *
     * @param n 数据规模
     * @param p 误判率
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("wrong n or p");
        }
        double ln2 = Math.log(2);
        // 求二进制向量的长度
        // 求出二进制向量的长度
        bitSize = (int) (- (n * Math.log(p)) / (ln2 * ln2));
        // 求出哈希函数的个数
        hashSize = (int) (bitSize * ln2 / n);
        // bits数组的长度
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
    }


    /**
     * 添加元素
     */
    public boolean put(T value) {
        nullCheck(value);

        boolean result = false;
        // 利用value生成两个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 0;i < hashSize;i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }

            int bitIndex = combinedHash % bitSize;
            if (set(bitIndex)) result = true;
        }

        return result;
    }

    private boolean set(int bitIndex) {
        long value = bits[bitIndex / Long.SIZE];
        int bitValue = (1 << bitIndex % Long.SIZE);
        bits[bitIndex / Long.SIZE] = value | bitValue;
        return (value & bitValue) == 0;
    }

    /**
     * 判断T是否存在
     * @param value
     * @return
     */
    public boolean contains(T value) {
        nullCheck(value);
        // 利用value生成2个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 生成一个二进位的索引
            int index = combinedHash % bitSize;
            // 查询index位置的二进位是否为0
            if (!get(index)) return false;
        }
        return true;
    }
    private void nullCheck(T value) {
        if (value == null) {
            throw new IllegalArgumentException("null pointer exception!");
        }
    }

    private boolean get(int index) {
        long value = bits[index / Long.SIZE];
        return (value & (1 << (index % Long.SIZE))) != 0;
    }

}
