package com.yangtao.Sort;

/**
 * @Autuor: innthehell
 * @Date: 2022/1/3 - 01 - 03 - 21:53
 * @Description: com.yangtao.Sort
 * @Version: 1.0
 */

import java.text.DecimalFormat;

/**
 * 排序基类，定义公共的操作
 */
public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>> {
    protected T[] array;
    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(T[] array) {
        if (array == null || array.length < 2) return;

        this.array = array;
        long start = System.currentTimeMillis();
        sort();
        long end = System.currentTimeMillis();
        time = end - start;
    }

    // 模板设计模式，父类已经写好了总体的逻辑，
    // 子类只需要实现特定的几个方法
    protected abstract void sort();


    // 进行排序算法之间性能的比较
    @Override
    public int compareTo(Sort<T> o) {
        int result = (int) (time - o.time);
        if (result != 0) return result;

        result = cmpCount - o.cmpCount;
        if (result != 0) return result;

        return swapCount - o.swapCount;
    }


    protected int cmp(int v1, int v2) {
        cmpCount++;
        return array[v1].compareTo(array[v2]);
    }

    protected int cmp(T t1, T t2) {
        cmpCount++;
        return t1.compareTo(t2);
    }

    protected void swap(int i1, int i2) {
        swapCount++;

        T tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
//        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
//                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }

//    private boolean isStable() {
//        if (this instanceof RadixSort) return true;
//        if (this instanceof CountingSort) return true;
//        if (this instanceof ShellSort) return false;
//        if (this instanceof SelectionSort) return false;
//        Student[] students = new Student[20];
//        for (int i = 0; i < students.length; i++) {
//            students[i] = new Student(i * 10, 10);
//        }
//        sort((T[]) students);
//        for (int i = 1; i < students.length; i++) {
//            int score = students[i].score;
//            int prevScore = students[i - 1].score;
//            if (score != prevScore + 10) return false;
//        }
//        return true;
//    }

}
