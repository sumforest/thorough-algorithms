package com.sen.leetcode;

import java.util.Stack;

/**
 * @author LingSen
 * @date 2023/6/8 15:03
 * 从字符串中移除星号
 * <p>
 * 给你一个包含若干星号 * 的字符串 s 。
 * <p>
 * 在一步操作中，你可以：
 * <p>
 * 选中 s 中的一个星号。
 * 移除星号 左侧 最近的那个 非星号 字符，并移除该星号自身。
 * 返回移除 所有 星号之后的字符串。
 * <p>
 * 示例 1：
 * 输入：s = "leet**cod*e"
 * 输出："lecoe"
 * 解释：从左到右执行移除操作：
 * - 距离第 1 个星号最近的字符是 "leet**cod*e" 中的 't' ，s 变为 "lee*cod*e" 。
 * - 距离第 2 个星号最近的字符是 "lee*cod*e" 中的 'e' ，s 变为 "lecod*e" 。
 * - 距离第 3 个星号最近的字符是 "lecod*e" 中的 'd' ，s 变为 "lecoe" 。
 * 不存在其他星号，返回 "lecoe" 。
 * <p>
 * 示例 2：
 * 输入：s = "erase*****"
 * 输出：""
 * 解释：整个字符串都会被移除，所以返回空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/removing-stars-from-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveStarsDemo {

    public static void main(String[] args) {
        System.out.println(removeStars("erase*****"));
    }

    public static String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('*' == c) {
                // 弹出 * 左边靠的最近的非 * 字符
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        char[] ch = new char[stack.size()];
        for (int i = ch.length - 1; i >= 0; i--) {
            ch[i] = stack.pop();
        }
        return new String(ch);
    }
}
