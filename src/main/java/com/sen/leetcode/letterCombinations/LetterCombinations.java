package com.sen.leetcode.letterCombinations;

import java.util.*;

/**
 * @author LingSen
 * @date 2023/6/11 15:47
 * 电话号码的字母组合
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LetterCombinations {
    public static void main(String[] args) {
//        String digits = "23";
//        String digits = "";
        String digits = "2";
        System.out.println(letterCombinations(digits));
    }

    private static final Map<Character, List<String>> numLetterMap = new HashMap<>();

    static {
        numLetterMap.put('2', Arrays.asList("a", "b", "c"));
        numLetterMap.put('3', Arrays.asList("d", "e", "f"));
        numLetterMap.put('4', Arrays.asList("g", "h", "i"));
        numLetterMap.put('5', Arrays.asList("j", "k", "l"));
        numLetterMap.put('6', Arrays.asList("m", "n", "o"));
        numLetterMap.put('7', Arrays.asList("p", "q", "r", "s"));
        numLetterMap.put('8', Arrays.asList("t", "u", "v"));
        numLetterMap.put('9', Arrays.asList("w", "x", "y", "z"));
    }

    public static List<String> letterCombinations(String digits) {
        List<List<String>> lettersList = new LinkedList<>();
        for (int i = 0; i < digits.length(); i++) {
            char num = digits.charAt(i);
            lettersList.add(numLetterMap.get(num));
        }
        List<String> ans = new ArrayList<>();
        if (!lettersList.isEmpty()) {
            appendLetter(lettersList, lettersList.get(0), new StringBuilder(), ans, 0);
        }
        return ans;
    }

    public static void appendLetter(List<List<String>> lettersList, List<String> list, StringBuilder stringBuilder, List<String> ans,int index) {
        for (String str : list) {
            StringBuilder subBuilder = new StringBuilder(stringBuilder);
            subBuilder.append(str);
            if (index + 1 < lettersList.size()) {
                appendLetter(lettersList, lettersList.get(index + 1), subBuilder, ans, index + 1);
            } else {
                ans.add(subBuilder.toString());
            }
        }
    }
}
