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

        //行+1、列+1用于表示背包为空,其值表示当前重量下当前商品策略的价值
        int[][] knapsack = new int[count + 1][limitWeight + 1];
        //每种商品，i=1空出一行表示背包空，行表示商品
        for (int i = 1; i < knapsack.length; i++) {
            //每种商品对应的策略，j=1空出一列表示背包空，列j表示背包的重量
            for (int j = 1; j < knapsack[0].length; j++) {
                //i-1因为i从1开始，而对应的表示商品重量的数组是从0开始
                // weight[i - 1] 当前商品的重量
                if (weight[i - 1] > j) {
                    //当前要添加商品的重量大于当前背包的可用重量,空间不足
                    //采取上一单元格的放置策略
                    knapsack[i][j] = knapsack[i - 1][j];
                } else {
                    //新增商品的重量不大于背包当前的可用重量
                    // knapsack[i][j] = Math.max(knapsack[i - 1][j], val[i-1] + knapsack[i - 1][j - weight[i-1]]);
                   /*背包重量:0   1    2    3   4
                            0   0    0    0    0
                     商品1   0 1500 1500 1500 1500
                     商品2   0 1500 1500 1500 3000
                     商品3   0 1500 1500 2000 3500*/
                    // val[i - 1]当前商品的价值；knapsack[i - 1][j - weight[i - 1]]没放入当前商品的背包价值
                    // 放入当前商品后背包的价值
                    int worth = val[i - 1] + knapsack[i - 1][j - weight[i - 1]];
                    if (knapsack[i - 1][j] < worth) {
                        // 上一种策略的价值小于当前策略价值
                        knapsack[i][j] = worth;
                        path[i][j] = 1;
                    } else {
                        // 上一种单元格的价值>=当前单元格价值
                        knapsack[i][j] = knapsack[i - 1][j];
                    }
                }
            }
        }

        // 输出背包填表
        for (int[] productions : knapsack) {
            for (int j = 0; j < knapsack[0].length; j++) {
                System.out.print(productions[j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------- 策略标记 ----------------");
        for (int[] ints : path) {
            for (int anInt : ints) {
                System.out.print(anInt);
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
                //把j移动到没放入当前商品前的重量位置处
                j -=weight[i-1];
            }
            // 移动上一单元格
            i--;
        }
    }
}
