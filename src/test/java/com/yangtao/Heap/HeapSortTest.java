package com.yangtao.Heap;


import java.util.PriorityQueue;

public class HeapSortTest {



    public static void main(String[] args) {
        /**
         * Heap 解决Top K问题
         */
        PriorityQueue<Integer> min = new PriorityQueue<>(3, (a, b)->(a-b));

        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
                90, 6, 65, 49, 3, 26, 61, 21, 48};
        for (int i = 0;i < data.length;i++) {
            if (min.size() < 3) {
                min.offer(data[i]);
            } else if (data[i] > min.peek()) {
                min.poll();
                min.offer(data[i]);
            }
        }
    }




}
