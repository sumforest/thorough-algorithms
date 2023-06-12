package com.sen.leetcode;

import java.util.Arrays;

/**
 * @author LingSen
 * @date 2023/6/8 0:06
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * 示例 1：
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class countBitsDemo {

    public static void main(String[] args) {
        int[] ans = countBits(5);
        System.out.println(Arrays.toString(ans));
    }

    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        char one = '1';
        for (int i = 1; i <= n; i++) {
            String binaryString = Integer.toBinaryString(i);
            int count = 0;
            for (int j = 0; j < binaryString.length(); j++) {
                if (one == (binaryString.charAt(j))) {
                    count++;
                }
            }
            ans[i] = count;
        }

        return ans;
    }
}
