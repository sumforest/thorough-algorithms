package com.sen.leetcode;

/**
 * @author LingSen
 * @date 2023/6/11 19:33
 * 1137. 第 N 个泰波那契数(简单)
 * 泰波那契序列Tn定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数n，请返回第 n 个泰波那契数Tn 的值。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * <p>
 * 示例 2：
 * 输入：n = 25
 * 输出：1389537
 * <p>
 * <p>
 * 提示：
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/n-th-tribonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TribonacciDemo {
    public static void main(String[] args) {
        System.out.println(tribonacci(25));
    }

    public static int tribonacci(int n) {
        int[] arr = new int[38];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;
        int index = 3;
        while (index <= n) {
            arr[index] = arr[index - 3] + arr[index - 2] + arr[index - 1];
            index++;
        }
        return arr[n];
    }

    /**
     * 递归效率太低，提交超时
     *
     * @param n
     * @return
     */
    public static int tribonacciRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return tribonacciRecursive(n - 3) + tribonacciRecursive(n - 2) + tribonacciRecursive(n - 1);
    }
}
