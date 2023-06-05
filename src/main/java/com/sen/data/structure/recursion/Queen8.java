package com.sen.data.structure.recursion;

/**
 * @Author: Sen
 * @Date: 2020/1/13 18:20
 * @Description: 递归回溯解决8皇后问题
 */
public class Queen8 {
    /**
     * 放置的皇后数量为8
     */
    private int max = 8;

    /**
     * 用于记录棋盘和皇后位置的数组，理论上应该创建二维数据表示，这里用一维数组解决
     * 一维数组的含义：
     * 1.数组的下标index表示第index+1个皇后
     * 2.数组下标表示当前第index+1个皇后摆放在第index+1行
     * 3.对应下标index的值value表示第index+1个皇后摆放再index+1行value+1列
     */
    private int[] array = new int[max];

    private int count = 0;

    public static void main(String[] args) {
        Queen8 queen = new Queen8();
        queen.put(0);
        System.out.println("一共有" + queen.count + "种解法");
    }

    /**
     * 打印每种解法
     */
    private void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("------------------------");
    }

    /**
     * 检查当前皇后的摆放位置是否和已存在棋盘上的皇后是否再同一行，同一列，同一斜线
     *
     * @param n 表示第n+1个皇后
     * @return 冲突返回false
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // array[i] == array[n]表示两个皇后再同一列位置上
            // Math.abs(n - i) == Math.abs(array[n] - array[i])数学上的意义为：相当于y=x
            // 表示两个皇后在同一斜线上
            /*
                      0    1   2  3   4   5   6   7
                    +---+---+---+---+---+---+---+--+
                0   | * |   |   |   |   |   |   |  |
                    +------------------------------+
                1   |   | * |   |   |   |   |   |  |
                    +------------------------------+
                2   |   |   |   |   |   |   |   |  |
                    +------------------------------+
                3   |   |   |   |   |   |   |   |  |
                    +------------------------------+
                4   |   |   |   |   |   |   |   |  |
                    +------------------------------+
                5   |   |   |   |   |   |   |   |  |
                    +------------------------------+
                6   |   |   |   |   |   |   |   |  |
                    +------------------------------+
                7   |   |   |   |   |   |   |   |  |
                    +---+---+---+---+---+---+---+--+
            * */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
            // 无需判断行，因为摆放棋子时代表行的n会递增
        }
        return true;
    }

    /**
     * 摆放棋子
     *
     * @param n 0为第一个棋子，从第n+1个棋子开始摆
     */
    public void put(int n) {
        //n为8时表示前面的8个棋子已经摆好
        if (n == max) {
            print();
        } else {
            for (int i = 0; i < max; i++) {
                array[n] = i;
                //判断是否可行
                if (judge(n)) {
                    //可行再放下一个棋子,同时放在下一行
                    put(n + 1);
                }
                //不可行时自动回溯
            }
        }
    }
}
