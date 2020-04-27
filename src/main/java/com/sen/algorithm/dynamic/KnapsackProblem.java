package com.sen.algorithm.dynamic;

/**
 * @Author: Sen
 * @Date: 2020/1/27 23:19
 * @Description: 动态规划解决背包问题（01背包）
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        //每种商品对应的重量
        int[] weight = {1, 4, 3};
        //每种商品对应的价格
        int[] val = {1500, 3000, 2000};
        //商品的总数量
        int count = val.length;
        //背包的所容纳的总重量
        int limitWeight = 4;

        //用于记录放置策略
        int[][] path = new int[count + 1][limitWeight + 1];

        //行+1、列+1用于表示背包为空
        int[][] knapsack = new int[count + 1][limitWeight + 1];
        //每种商品
        for (int i = 1; i < knapsack.length; i++) {
            //每种商品对应的策略
            for (int j = 1; j < knapsack[0].length; j++) {
                //i-1因为i从1开始，而对应的表示商品重量的数组是从0开始
                if (weight[i - 1] > j) {
                    //当前要添加商品的重量大于当前背包的可用重量
                    //采取上一单元格的放置策略
                    knapsack[i][j] = knapsack[i - 1][j];
                } else {
                    //新增商品的重量不大于背包当前的可用重量
                    // knapsack[i][j] = Math.max(knapsack[i - 1][j], val[i-1] + knapsack[i - 1][j - weight[i-1]]);
                    if (knapsack[i - 1][j] < val[i - 1] + knapsack[i - 1][j - weight[i - 1]]) {
                        knapsack[i][j] = val[i - 1] + knapsack[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    } else {
                        knapsack[i][j] = knapsack[i - 1][j];
                    }
                }
            }
        }

        //遍历策略表格
        for (int[] productions : knapsack) {
            for (int j = 0; j < knapsack[0].length; j++) {
                System.out.print(productions[j] + " ");
            }
            System.out.println();
        }

        //输出最优策略
        //商品种类
        int i = knapsack.length - 1;
        //背包可用重量
        int j = knapsack[0].length - 1;
        while (i >= 0 && j >= 0) {
            int flag = path[i][j];
            if (flag > 0) {
                System.out.printf("把第%d种商品放入背包\n", i);
                //把j移动到上一策略
                j -=weight[i-1];
            }
            i--;
        }
    }
}
