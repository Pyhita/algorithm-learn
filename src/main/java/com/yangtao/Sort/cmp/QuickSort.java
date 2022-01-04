package com.yangtao.Sort.cmp;

import com.yangtao.Sort.Sort;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/4 - 01 - 04 - 10:25
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {


    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对[begin, end)范围的元素进行快排
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;

        // 确定轴点位置
        int mid = piovtIndex(begin, end);
        // 对子序列进行快排
        sort(begin, mid);
        sort(mid, end);
    }

    private int piovtIndex(int begin, int end) {
        // 随机选择一个元素与begin位置元素进行交换
        swap(begin, begin + (int) (Math.random() * (end - begin)));

        // 备份begin位置元素
        T v = array[begin];
        // end指向最后一个元素
        end--;

        while (begin < end) {
            while (begin < end) {
                // 右边元素 > 轴点元素
                if (cmp(v, array[end]) < 0) {
                    end--;
                } else {
                    // 右边元素 <= 轴点元素
                    array[begin++] = array[end];
                    break;
                }
            }

            while (begin < end) {
                if (cmp(array[begin], v) < 0) {
                    // 左边元素 < 轴点元素
                    begin++;
                } else {
                    // 左边元素 >= 轴点元素
                    array[end--] = array[begin];
                    break;
                }
            }

        }

        // 将轴点放入最终的位置
        array[begin] = v;
        // 返回轴点位置的元素
        return begin;
    }



}
