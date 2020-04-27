package com.sen.data.structure.search;

import java.util.Arrays;

/**
 * @Author: Sen
 * @Date: 2020/1/15 18:14
 * @Description: 斐波那契查找算法（黄金分割查找算法），前提：数组有序
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("查找的数组下标为：" + fibonacciSearch(arr, 1234));
    }

    /**
     * 生成斐波那契数组用于分割数组
     * @return 斐波那契数列
     */
    private static int[] createFibonacci() {
        int[] fibonacci = new int[20];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    /**
     * @param arr    目标数组
     * @param target 目标值
     * @return 存在返回下标，else -1
     */
    private static int fibonacciSearch(int[] arr, int target) {
        //开始查找的位置
        int left = 0;
        //结束查找的位置
        int right = arr.length - 1;
        //分割的指针
        int mid = 0;
        //斐波那契数列使用它的特性来分割数组
        int[] fibonacci = createFibonacci();
        //记录斐波那契数组指针
        int k = 0;
        //产生一个符合斐波那契特性的数组 fibonacci[k] - 1 =  (fibonacci[k-1] - 1) + (fibonacci[k-2] - 1) + 1
        //其中fibonacci[k] - 1代表产生后的数组长度，fibonacci[k-1] - 1表示前半部分数组的长度，
        // fibonacci[k-2] - 1后半数组的长度
        // 1 ->mid中间值
        while (right > fibonacci[k] - 1) {
            k++;
        }
        //生成一个符合斐波那契特性的长度为fibonacci[k]的数组
        int[] temp = Arrays.copyOf(arr, fibonacci[k]);
        //由于产生的数组的长度可能比原数组大，所以在right+1位置开始补全arr[right]值
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        //开始循环查超
        while (left <= right) {
            mid = left + fibonacci[k - 1] - 1;
            if (target < temp[mid]) {
                //向左查找
                right = mid - 1;
                //总数组长度 fibonacci[k] - 1  = (fibonacci[k-1] - 1) + (fibonacci[k-2] - 1) + 1
                //fibonacci[k-1] - 1代左半部分所以k--
                k--;
            }
            if (target > temp[mid]) {
                //向右查找
                left = mid + 1;
                //总数组长度 fibonacci[k] - 1  = (fibonacci[k-1] - 1) + (fibonacci[k-2] - 1) + 1
                //fibonacci[k-2] - 1代右半部分所以k-2
                k -=2;
            }
            //找到目标元素
            if (target == temp[mid]) {
                //判断mid是否超原数组的下标
                return Math.min(mid, arr.length - 1);
            }
        }
        //没有找到
        return -1;
    }
}
