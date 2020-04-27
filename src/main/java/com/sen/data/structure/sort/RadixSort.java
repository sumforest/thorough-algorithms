package com.sen.data.structure.sort;

import java.util.Arrays;

/**
 * @Author: Sen
 * @Date: 2020/1/14 23:04
 * @Description: 基数排序（桶排序）,典型的一种空间换时间的算法。
 * 特点：
 * 1.排序速度非常快
 * 2.稳定
 * 不足：
 * 1.当需要排序海量数据时会耗费大量的内存，会造成OutOfMemoryError: Java heap space
 * 2.当前实现不支持负数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        // int[] arr = {53, 3, 542, 748, 14, 214};
        // radixSort(arr);
        // System.out.println(Arrays.toString(arr));
        // 基数排序（桶排序）10000000条数据花费时间：0S
        BubbleSort.testPerformance("基数排序（桶排序）", RadixSort::radixSort, 10_000_000, false);
    }

    private static void radixSort(int[] arr) {
        //定义一个二维数组表示桶，其中每个一维数组表示一个桶
        //特别地：
        // 1.因为在十进制下只有0-9个数字所以有十个桶
        // 2.考虑最坏的情况会有arr.length个元素在一个桶中
        int[][] bucket = new int[10][arr.length];
        //创建一个一维数组用于记录每个桶存放了多少个元素，每个下标对应的每个桶
        int[] record = new int[10];
        //找到数组中最大的元素,假定第一个元素是最小
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        //获取最大的位数
        int maxBit = (maxValue + "").length();
        //位数决定轮数
        for (int i = 0, n = 1; i < maxBit; i++, n *= 10) {
            for (int value : arr) {
                //取出每个元素的个、十、百、千等位
                int mod = value / n % 10;
                //取模的的结果决定元素放入的桶,用于记录的以为数组的值决定放入桶中的位置
                bucket[mod][record[mod]] = value;
                //用于记录的一维数组对应的元素+1
                record[mod]++;
            }
            //每轮结束后按照桶的顺序和放入桶的顺序从每个桶取出
            // 用于记录原数组取值的指针
            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                //当桶中有元素时才取
                if (record[j] > 0) {
                    for (int k = 0; k < record[j]; k++) {
                        arr[index] = bucket[j][k];
                        index++;
                    }
                    //最记录清空用于下一轮
                    record[j] = 0;
                }
            }
            // System.out.println("第" + (i + 1) + "轮" + Arrays.toString(arr));
        }

        /*
        //定义一个二维数组表示桶，其中每个一维数组表示一个桶
        //特别地：
        // 1.因为在十进制下只有0-9个数字所以有十个桶
        // 2.考虑最坏的情况会有arr.length个元素在一个桶中
        int[][] bucket = new int[10][arr.length];
        //创建一个一维数组用于记录每个桶存放了多少个元素，每个下标对应的每个桶
        int[] record = new int[10];

        //第1轮
        //遍历数组
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的个位
            int mod = arr[i] % 10;
            //把元素放进对应的桶
            bucket[mod][record[mod]] = arr[i];
            //把基于桶的数组对应的元素加1
            record[mod]++;
        }
        //按照放进去的顺序从桶中取出元素
        // 记录元素下标指针
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            //如果桶中有元素才取出
            if (record[i] > 0) {
                for (int j = 0; j < record[i]; j++) {
                    arr[index] = bucket[i][j];
                    //指针后移
                    index++;
                }
                //清零用于记录的数组
                record[i] = 0;
            }
        }

        //第2轮
        //遍历数组
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的十位
            int mod = arr[i] / 10 % 10;
            //把元素放进对应的桶
            bucket[mod][record[mod]] = arr[i];
            //把基于桶的数组对应的元素加1
            record[mod]++;
        }
        //按照放进去的顺序从桶中取出元素
        // 记录元素下标指针
        index = 0;
        for (int i = 0; i < bucket.length; i++) {
            //如果桶中有元素才取出
            if (record[i] > 0) {
                for (int j = 0; j < record[i]; j++) {
                    arr[index] = bucket[i][j];
                    //指针后移
                    index++;
                }
                //清零用于记录的数组
                record[i] = 0;
            }
        }

        //第3轮
        //遍历数组
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的百位
            int mod = arr[i] / 10 / 10 % 10;
            //把元素放进对应的桶
            bucket[mod][record[mod]] = arr[i];
            //把基于桶的数组对应的元素加1
            record[mod]++;
        }
        //按照放进去的顺序从桶中取出元素
        // 记录元素下标指针
        index = 0;
        for (int i = 0; i < bucket.length; i++) {
            //如果桶中有元素才取出
            if (record[i] > 0) {
                for (int j = 0; j < record[i]; j++) {
                    arr[index] = bucket[i][j];
                    //指针后移
                    index++;
                }
                //清零用于记录的数组
                record[i] = 0;
            }
        }
         */
    }
}
