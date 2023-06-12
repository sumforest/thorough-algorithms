package com.sen.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LingSen
 * @date 2023/6/5 18:28
 * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
 * 返回 合并后的字符串 。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/merge-strings-alternately">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeAlternatelyDemo {

    public static void main(String[] args) {
//        String word1 = "abc", word2 = "pqr";
//        String word1 = "ab", word2 = "pqrs";
        String word1 = "abcd", word2 = "pq";
        String res = mergeAlternately(word1, word2);
        System.out.println(res);
    }

    public static String mergeAlternately(String word1, String word2) {
        int index1 = 0, index2 = 0;
        int minLen = Math.min(word1.length(), word2.length());
        boolean w1 = word1.length() > word2.length();
        List<Character> res = new ArrayList<>(100);
        int realIndex = 0;
        for (int i = 0; i < minLen * 2; i++) {
            if (i % 2 == 0) {
                res.add(i, word1.charAt(index1++));
            } else {
                res.add(i, word2.charAt(index2++));
            }
            realIndex++;
        }
        if (w1) {
            for (int i = index1; i < word1.length(); i++) {
                res.add(realIndex++, word1.charAt(i));
            }
        }else{
            for (int i = index2; i < word2.length(); i++) {
                res.add(realIndex++, word2.charAt(i));
            }
        }

        char[] ch = new char[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ch[i] = res.get(i);
        }
        return new String(ch);
    }
}
