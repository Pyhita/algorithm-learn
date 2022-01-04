package com.yangtao.Sort;

import jdk.nashorn.internal.ir.debug.PrintVisitor;

import java.util.Arrays;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 22:10
 * @Description: com.yangtao.Sort
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        int[] test = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
        quicksort(test);
        System.out.println(Arrays.toString(test));
    }

    private static int[] array;
    private static int[] leftArray;
    private static int heapSize;

    // 最简单的冒泡排序
    static void bubble1(int[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    swap(array, begin, begin - 1);
                }
            }
        }
    }

    // 冒泡优化1：增加一个哨兵，提前结束排序
    static void bubble2(int[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    sorted = false;
                    swap(array, begin, begin - 1);
                }
            }
            if (sorted) break;
        }
    }

    // 优化2：记录已经有序的尾部位置
    static void bubble3(int[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            int sortIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    sortIndex = begin;
                    swap(array, begin, begin - 1);
                }
            }
            end = sortIndex;
        }
    }


    // 选择排序
    static void selection(int[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            int max = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (array[max] < array[begin]) max = begin;
            }
            swap(array, max, end);
        }
    }

    // 堆排序
    static void heap(int[] a) {
        array = a;
        heapSize = a.length;

        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            swap(a, 0, --heapSize);

            siftDown(0);
        }
    }

    static void siftDown(int index) {
        int half = heapSize >> 1;
        int t = array[index];

        while (index < half) {
            int chiidIndex = (index << 1) + 1;
            int child = array[chiidIndex];

            int rightIndex = chiidIndex + 1;
            if (rightIndex < heapSize &&
                    array[chiidIndex] < array[rightIndex]) {
                child = array[chiidIndex = rightIndex];
            }

            if (array[index] > child) break;

            array[index] = child;
            index = chiidIndex;
        }
        array[index] = t;
    }

    static void insertion(int[] array) {
        for (int begin = 1;begin < array.length;begin++) {
            int cur = begin;
            while (cur > 0 && array[cur] < array[cur - 1]) {
                swap(array, cur, cur - 1);
                cur--;
            }
        }
    }

    static void insertion2(int[] array) {
        for (int begin = 1;begin < array.length;begin++) {
            int cur = begin;
            int v = array[cur];
            while (cur > 0 && v < array[cur - 1]) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = v;
        }
    }

    // use binary search in insetion sort
    static void insertion3(int[] arr) {
        array = arr;
        for (int begin = 1; begin < arr.length; begin++) {
            insert(begin, search(begin));
        }
    }

    private static void insert(int source, int dest) {
        int v = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = v;
    }

    private static int search(int index) {
        int left = 0, right = index;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[index]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static void mergesort(int[] arr) {
        array = arr;
        leftArray = new int[arr.length >> 1];
        sort(0, arr.length);
    }

    private static void sort(int begin, int end) {
        if (end - begin < 2) return;

        int mid = begin + (end - begin) / 2;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private static void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;

        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        while (li < le) {
            if (ri < re && array[ri] < leftArray[li]) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }

    private static void quicksort(int[] arr) {
        array = arr;
        qsort(0, arr.length);
    }

    private static void qsort(int begin, int end) {
        if (end - begin < 2) return;

        int mid = piovtIndex(begin, end);
        qsort(begin, mid);
        qsort(mid + 1, end);
    }

    private static int piovtIndex(int begin, int end) {
        swap(array, begin, begin + (int) (Math.random() * (end - begin)));
        int piovt = array[begin];
        end--;

        while (begin < end) {
            while (begin < end) {
                if (array[end] > piovt) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }

            while (begin < end) {
                if (array[begin] < piovt) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }

        array[begin] = piovt;
        return begin;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }



}
