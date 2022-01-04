package com.yangtao.Sort.cmp;

import com.sun.prism.sw.SWPipeline;
import com.yangtao.Sort.Sort;
import sun.awt.image.PixelConverter;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 22:04
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */

// 冒泡排序
public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {

        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }
}
