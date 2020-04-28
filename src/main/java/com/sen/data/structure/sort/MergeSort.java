package com.sen.data.structure.sort;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;

/**
 * @Author: Sen
 * @Date: 2020/1/14 19:53
 * @Description: 归并排序，时间复杂度 O(n log n)
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2,5};
        // int[] arr = new int[100_000_000];
        // Random random = new Random(System.currentTimeMillis());
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] = random.nextInt(1_000_000_000);
        // }
        // Instant start = Instant.now();
        int[] temp = new int[arr.length];
        // 归并排序测试:100_000_000条数据花费时间：14S
        mergeSort(arr, 0, arr.length - 1, temp);
        // Instant end = Instant.now();
        // System.out.println("归并排序测试:100_000_000条数据花费时间：" + Duration.between(start, end).get(ChronoUnit.SECONDS) + "S");
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int start, int end, int[] temp) {
        if (start < end) {
            int mid = (start + end) / 2;
            //左边递归分解
            mergeSort(arr, start, mid, temp);
            //右边递归分解
            mergeSort(arr, mid + 1, end, temp);
            merge(arr, start, mid, end, temp);
        }
    }

    /**
     * 归并排序中用户合并结果的方法
     *
     * @param arr   原数组
     * @param start 开始位置
     * @param mid   中间位置
     * @param end   结束位置
     * @param temp  中间数组
     */
    private static void merge(int[] arr, int start, int mid, int end, int[] temp) {
        //左边有序部分的起始下标
        int left = start;
        //右边有序部分的起始下标
        int right = mid + 1;
        //记录中间数组的下标
        int index = 0;
        //1.把左右两部分的数组按照从小到大的顺序放入中转数组
        while (left <= mid && right <= end) {
            //左边有序的当前元素小于或等于右边有序部分的当前元素
            if (arr[left] <= arr[right]) {
                //把元素放入临时数组
                temp[index] = arr[left];
                index++;
                left++;
            }
            //右边有序部分的当前元素小于或等于左边有序部分的当前元素
            if (arr[right] < arr[left]) {
                temp[index] = arr[right];
                index++;
                right++;
            }
        }
        //2.把剩余的元素按照顺序添加到中转数组
        // 左边剩余元素
        while (left <= mid) {
            temp[index] = arr[left];
            index++;
            left++;
        }
        //右边剩余元素
        while (right <= end) {
            temp[index] = arr[right];
            index++;
            right++;
        }

        //3.临时数组拷贝到原数组
        //重置中转数组指针
        index = 0;
        int tempLeft = start;
        while (tempLeft <= end) {
            arr[tempLeft] = temp[index];
            index++;
            tempLeft++;
        }
    }
}
