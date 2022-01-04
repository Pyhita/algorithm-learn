package com.yangtao.Sort.cmp;

import com.sun.corba.se.spi.transport.SocketOrChannelAcceptor;
import com.yangtao.Sort.Sort;
import jdk.nashorn.internal.ir.debug.PrintVisitor;
import sun.awt.image.PixelConverter;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/4 - 01 - 04 - 9:35
 * @Description: com.yangtao.Sort.cmp
 * @Version: 1.0
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    // 额外的数组空间，用来排序
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对begin 到 end范围内的数据进行归并排序
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;

        int mid = begin + (end - begin) / 2;
        sort(begin, mid);
        sort(mid, end);

        merge(begin, mid, end);
    }

    // 将[begin, mid) [mid, end)数据合并有序序列
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;

        // 备份左边的的数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        // 如果左边没有结束
        while (li < le) {
            if (ri < re && cmp(ri, li) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }

}
