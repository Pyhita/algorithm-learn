package com.yangtao.BinaryTree;

import java.util.Comparator;

/**
 * @Author: pyhita
 * @Date: 2022/3/5
 * @Descrption: com.yangtao.BinaryTree
 * @Version: 1.0
 */
public class Person implements Comparable<Person> {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person e) {
//		if (age > e.age) return 1;
//		if (age < e.age) return -1;
//		return 0;
        return age - e.age;
    }

    @Override
    public String toString() {
        return age + "_" + name;
    }

    // 也可以用比较器，重写比较逻辑
    private class cmp implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return 0;
        }
    }

}


