package com.sen.leetcode.uniquePaths;

/**
 * @author LingSen
 * @date 2023/6/12 15:36
 * 62. 不同路径(动态规划-中等)
 * <p>
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniquePathsDemo {
    public static void main(String[] args) {
        // int m = 3, n = 7;
        // int m = 3, n = 2;
        // int m = 7, n = 3;
        int m = 3, n = 3;
        System.out.println(uniquePaths(m, n));
    }

    public static int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];
        // 起点
        matrix[0][0] = 0;
        for (int i = 0; i < n; i++) {
            matrix[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 观测规律知道到达第m，n个格子的走法=到[m-1,n]格子走法 + 到[m,n-1]格子走法，左边界和上边界都为1
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m - 1][n - 1];
    }
}
