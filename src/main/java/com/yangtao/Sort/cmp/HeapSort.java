package com.yangtao.Sort.cmp;

import com.sun.prism.sw.SWPipeline;
import com.yangtao.Sort.Sort;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 22:51
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */

// 堆排序
public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    private int heapSize;

    @Override
    protected void sort() {
        heapSize = array.length;
        // heapify
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            swap(0, heapSize--);
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        int half = heapSize >> 1;
        T t = array[index];

        while (index < half) {
            int childIndex = (index << 1) + 1;
            T child = array[childIndex];

            int rightIndex = childIndex + 1;
            if (rightIndex < heapSize &&
                    cmp(child, array[rightIndex]) < 0) {
                child = array[childIndex = rightIndex];
            }

            if (cmp(t, child) > 0) break;
            array[index] = child;
            index = childIndex;
        }
        array[index] = t;
    }
}






