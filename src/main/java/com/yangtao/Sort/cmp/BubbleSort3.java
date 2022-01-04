package com.yangtao.Sort.cmp;

import com.yangtao.Sort.Sort;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 22:04
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */

// 冒泡排序 优化2：如果序列的尾部已经提前有序了，
// 可以记录这个位置，直接将end提前到这个位置
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {

        for (int end = array.length - 1; end > 0; end--) {
            int sortIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sortIndex = begin;
                }
            }
            end = sortIndex;
        }

    }
}
