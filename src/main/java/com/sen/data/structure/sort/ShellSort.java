package com.sen.data.structure.sort;

import java.util.Arrays;

/**
 * @Auther: Sen
 * @Date: 2020/1/14 02:58
 * @Description: 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // shellSortExchange(arr);
        // shellSortSwap(arr);
        // System.out.println(Arrays.toString(arr));
        // 希尔排序交换法150000条数据花费时间：18S
        // BubbleSort.testPerformance("希尔排序交换法", ShellSort::shellSortExchange);
        // 希尔排序移动法150000条数据花费时间：0S
        // 希尔排序移动法100000000条数据花费时间：32S
        BubbleSort.testPerformance("希尔排序移动法", ShellSort::shellSortMove,100_000_000,false);
    }

    /**
     * 希尔排序：交换法实现
     * 缺点：效率低
     *
     * @param arr 原数组
     */
    private static void shellSortExchange(int[] arr) {
        //每次分组在原来基础上再分成两组
        for (int gro = arr.length / 2; gro > 0; gro /= 2) {
            //生成组
            for (int i = gro; i < arr.length; i++) {
                //给每组的元素排序
                for (int j = i - gro; j >= 0; j -= gro) {
                    if (arr[j] > arr[j + gro]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gro];
                        arr[j + gro] = temp;
                    }
                }
            }
        }
        
/*
        //第一轮分成分成5组,每组两个元素8, 9, 1, 7, 2, 3, 5, 4, 6, 0->3,5,1,6,0,8,9,4,7,2
        for (int i = 5; i < arr.length ; i++) {
            for (int j = i - 5; j >= 0; j -= 5) {
                //对五组元素分别进行排序
                if (arr[j] > arr[j + 5]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("第一轮结果"+ Arrays.toString(arr));

        //第二轮分成分成2组,每组5个元素3,5,1,6,0,8,9,4,7,2->0,2,1,4,3,5,7,6,9,8
        for (int i = 2; i < arr.length ; i++) {
            //这个循环回会对每组都进行排序
            for (int j = i - 2; j >= 0; j -= 2) {
                //对五组元素分别进行排序
                if (arr[j] > arr[j +2]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("第二轮结果"+ Arrays.toString(arr));

        //第三轮分成分成1组,每组10个元素3,5,1,6,0,8,9,4,7,2->有序
        for (int i = 1; i < arr.length ; i++) {
            //这个循环回会对每组都进行排序
            for (int j = i - 1; j >= 0; j -= 1) {
                //对五组元素分别进行排序
                if (arr[j] > arr[j +1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("第三轮结果"+ Arrays.toString(arr));*/
    }

    /**
     * 希尔排序：移动法
     * 优点：比交换法实现快的多
     *
     * @param arr 原数组
     */
    private static void shellSortMove(int[] arr) {
        //给原数组缩小增量，分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //遍历每组的元素
            for (int i = gap; i < arr.length; i++) {
                //目标位置的下标
                int index = i;
                //带插入的值
                int insertValue = arr[i];
                //同组的前一个元素比后一个元素大时才移动插入
                if (arr[index] < arr[index - gap]) {
                    //index - gap >= 0 确保下标不越界，并且可以把最小的值移到最前面
                    //insertValue < arr[i] 因为从小到打排序，所以次条件满足时说明待插入元素还没找到合适的位置
                    while (index - gap >= 0 && insertValue < arr[index-gap]) {
                        //把同组的前一个元素往后移动一个步长
                        arr[index] = arr[index - gap];
                        //把目标下标前移
                        index -= gap;
                    }
                    //把待插入元素插入
                    arr[index] = insertValue;
                }
            }
        }
    }
}
