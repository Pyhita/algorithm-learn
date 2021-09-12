package com.yangtao.Trie;

/**
 * 节点内部采用数组存储的Trie树
 */
public class Trie_Array {

    private TrieNode root;

    public Trie_Array() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        TrieNode node = root;
        char[] chs = word.toCharArray();
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.path++;
        }
        node.end++;
    }

    public void delete(String word) {
        if (!search(word)) return;
        int index = 0;
        TrieNode node = root;
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (--node.nexts[index].path == 0) {
                node.nexts[index] = null;
                return;
            }
            node = node.nexts[index];
        }
        node.end--;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        int index = 0;
        TrieNode node = root;
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) return false;
            node = node.nexts[index];
        }

        return node.end > 0;
    }

    public boolean prefix(String prefix) {
        if (prefix == null) {
            return false;
        }
        int index = 0;
        TrieNode node = root;
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (node.nexts[index] == null) return false;
            node = node.nexts[index];
        }

        return true;
    }


    static class TrieNode {
        int path;
        int end;
        TrieNode[] nexts;

        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        Trie_Array trie = new Trie_Array();
        System.out.println(trie.search("zuo")); // false
        trie.insert("zuo");
        System.out.println(trie.search("zuo")); // true
        trie.delete("zuo");
        System.out.println(trie.search("zuo")); // false
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo")); // true
        trie.delete("zuo");
        System.out.println(trie.search("zuo")); // false
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa")); // false
        System.out.println(trie.prefix("zuo"));        // true

    }








}
