package com.yangtao.Sort.cmp;

import com.yangtao.Sort.Sort;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 23:31
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */
// 插入排序优化：减少交换次数
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            T v = array[cur];
            while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = v;
        }
    }
}
