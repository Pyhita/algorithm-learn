package com.yangtao.Sort.cmp;

import com.yangtao.Sort.Sort;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 22:04
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */

// 冒泡排序优化1：增加一个哨兵，如果提前已经有序了，终止排序
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {

        for (int end = array.length - 1; end > 0; end--) {
            boolean flag = true;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    flag = false;
                    swap(begin, begin - 1);
                }
            }

            if (flag) break;
        }
    }
}
