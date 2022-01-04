package com.yangtao.Sort.cmp;

import com.sun.prism.sw.SWPipeline;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import com.yangtao.Sort.Sort;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 22:45
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */
// 选择排序：每次找出最大的，放到末尾
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {


    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int max = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(max, begin) < 0) max = begin;
            }
            swap(max, end);
        }
    }
}
