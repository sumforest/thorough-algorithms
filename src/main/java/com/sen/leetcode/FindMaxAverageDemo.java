package com.sen.leetcode;

/**
 * @author LingSen
 * @date 2023/6/8 10:32
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * <p>
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 * 示例 1：
 * <p>
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-average-subarray-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMaxAverageDemo {

    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        System.out.println(findMaxAverage(nums, 4));
    }

    public static double findMaxAverage(int[] nums, int k) {
        // 计算第一个子数组
        int count = 0;
        for (int j = 0; j < k; j++) {
            count += nums[j];
        }
        int maxCount = count;
        for (int i = k; i < nums.length; i++) {
            // 往右移动，左边移出一个数，右边移入一个数
            count -= nums[i - k];
            count += nums[i];
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return (double) maxCount / k;
    }
}
