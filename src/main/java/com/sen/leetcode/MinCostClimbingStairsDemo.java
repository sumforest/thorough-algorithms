package com.sen.leetcode;

/**
 * @author LingSen
 * @date 2023/6/11 20:20
 * 746. 使用最小花费爬楼梯 (动态规划-简单)
 * <p>
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * 示例 1：
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * <p>
 * 示例 2：
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 * <p>
 * <p>
 * 提示：
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/min-cost-climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinCostClimbingStairsDemo {
    public static void main(String[] args) {
//        int[] cost = new int[]{10, 15, 20};
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));
    }

    public static int minCostClimbingStairs(int[] cost) {
        /*
        cost:10,15,20,top
        dp:  0 ,0 ,10,15
        cost:1, 100, 1, 1, 1, 100, 1, 1, 100, 1, top
        dp:  0, 0  , 1, 2, 2, 3  , 3, 4,  4 , 5, 6
        */
        //记录第i点到楼梯顶部的最小费用
        int[] dp = new int[cost.length + 1];
        // 可以从下标0，1开始所以
        dp[0]=0;
        dp[1]=0;
        for (int i = 2; i < cost.length + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1]/*走1个台阶*/, dp[i - 2] + cost[i - 2]/*走2个台阶*/);
        }
        return dp[cost.length];
    }
}
