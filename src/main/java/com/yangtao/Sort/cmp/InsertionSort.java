package com.yangtao.Sort.cmp;

import com.yangtao.Sort.Sort;

import java.net.Socket;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 23:31
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */
// 插入排序
public class InsertionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur - 1);
                cur--;
            }
        }
    }
}
