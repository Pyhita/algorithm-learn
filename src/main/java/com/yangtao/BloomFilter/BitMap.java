package com.yangtao.BloomFilter;

/**
 * @Author: pyhita
 * @Date: 2022/3/6
 * @Descrption: com.yangtao.BloomFilter
 * @Version: 1.0
 */

// 位图数据结构：参考一个问题有 1 千万个整数，整数的范围在 1 到 1 亿之间。
// 如何快速查找某个整数是否在这 1 千万个整数中呢？
public class BitMap {

    private char[] bytes;
    private int nbits;

    public BitMap(char[] bytes, int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits / 16 + 1];
    }

    public void set(int k) {
        if (k > nbits) return;

        int byteIndex = k / Character.SIZE;
        int bitIndex = k % Character.SIZE;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean get(int k) {
        if (k > nbits) return false;
        int byteIndex = k / Character.SIZE;
        int bitIndex = k % Character.SIZE;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }

}
