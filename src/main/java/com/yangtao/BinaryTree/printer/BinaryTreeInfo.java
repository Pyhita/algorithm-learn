package com.yangtao.BinaryTree.printer;

/**
 * @Author: pyhita
 * @Date: 2022/3/5
 * @Descrption: com.yangtao.BinaryTree.printer
 * @Version: 1.0
 */
public interface BinaryTreeInfo {
    /**
     * who is the root node
     */
    Object root();
    /**
     * how to get the left child of the node
     */
    Object left(Object node);
    /**
     * how to get the right child of the node
     */
    Object right(Object node);
    /**
     * how to print the node
     */
    Object string(Object node);
}
