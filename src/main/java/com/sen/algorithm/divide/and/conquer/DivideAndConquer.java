package com.sen.algorithm.divide.and.conquer;

/**
 * @Author: Sen
 * @Date: 2020/1/26 23:03
 * @Description: 分治算法
 */
public class DivideAndConquer {

    public static void main(String[] args) {
        Hannock(5, 'A', 'B', 'C');
    }

    /**
     * 分治算法解决汉诺克问题
     *
     * @param num 盘子的总数
     * @param a   第一根柱子
     * @param b   第二根柱子
     * @param c   第三根柱子
     */
    private static void Hannock(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘子：" + a + "->" + c);
        }
        else{
            //把num-1个盘子从a移动到b柱子
            Hannock(num-1, a, c, b);
            //把第num个盘子移动到c柱子
            System.out.println("第" + num + "个盘子：" + a + "->" + c);
            //把num-1个盘子从b移动到c柱子
            Hannock(num-1, b, a, c);
        }
    }
}
