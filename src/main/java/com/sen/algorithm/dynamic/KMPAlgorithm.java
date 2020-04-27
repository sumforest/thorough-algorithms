package com.sen.algorithm.dynamic;

/**
 * @Author: Sen
 * @Date: 2020/1/28 13:53
 * @Description: KMP算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] kmpNext = kmpNext("ABCDABD");
        int index = kmpSearch(str1, str2, kmpNext);
        System.out.println(index);
    }

    /**
     * KMP字符串匹配算法
     *
     * @param str1    目标字符串
     * @param str2    子字符串
     * @param kmpNext 子字符串的部分匹配值
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] kmpNext) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            if (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = j - kmpNext[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 生成部分匹配表
     *
     * @param target 子字符串
     * @return 子字符串对应的每个字符对应的部分匹配表
     */
    private static int[] kmpNext(String target) {
        int[] next = new int[target.length()];
        //小标为0的字符部分匹配值为零
        next[0] = 0;
        for (int i = 1, j = 0; i < target.length(); i++) {
            while (j > 0 && target.charAt(i) != target.charAt(j)) {
                j = next[j - 1];
            }
            if (target.charAt(i) == target.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
