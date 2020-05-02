package com.sen.algorithm.divide.and.conquer;

/**
 * @Author: Sen
 * @Date: 2020/1/26 23:03
 * @Description: 分治算法
 */
public class DivideAndConquer {

    public static void main(String[] args) {
        hancock(3, 'A', 'B', 'C');
    }

    /**
     * 分治算法解决汉诺克问题,从上到下分别为：第一个盘子、第二个盘子。。。
     *
     * @param num 盘子的总数
     * @param a   起始柱子
     * @param b   中间柱子
     * @param c   结束柱子
     */
    private static void hancock(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘子：" + a + "->" + c);
        }
        /*
        第1个盘子：A->C
        第2个盘子：A->B
        第1个盘子：C->B
        第3个盘子：A->C
        第1个盘子：B->A
        第2个盘子：B->C
        第1个盘子：A->C
        * */
        else {
            //把num-1个盘子从a移动到b柱子
            hancock(num - 1, a, c, b);
            //把第num个盘子移动到c柱子
            System.out.println("第" + num + "个盘子：" + a + "->" + c);
            //把num-1个盘子从b移动到c柱子
            hancock(num - 1, b, a, c);
        }
    }
}
