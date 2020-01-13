package com.sen.data.structure.sort;

import java.util.Arrays;

/**
 * @Auther: Sen
 * @Date: 2020/1/14 02:58
 * @Description: 希尔排序：交换法，效率比较低
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void shellSort(int[] arr) {
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
}
