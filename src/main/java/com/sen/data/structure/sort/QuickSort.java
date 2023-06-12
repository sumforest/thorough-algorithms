package com.sen.data.structure.sort;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;

/**
 * @Author: Sen
 * @Date: 2020/1/14 16:54
 * @Description: 快速排序,平均时间复杂度 O(n log n),最坏时间复杂度 O(n^2)
 */
public class QuickSort {

    public static void main(String[] args) {
        // int[] arr = {-9, 78, 0, 23, -567, 70};
        // int[] arr = {-9, 78, 0, 23, 0, 70};
        int[] arr = {-9, 70, 0, 23, -576, 0};
        // int[] arr = {-9, 0, 0, 23, -576, 70};
        // int[] arr = new int[100_000_000];
        // Random random = new Random(System.currentTimeMillis());
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] = random.nextInt(1_000_000_000);
        // }
        // Instant start = Instant.now();
        // 快速排序实现1测试:100_000_000条数据花费时间：13S
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        // 快速排序实现2测试:100_000_000条数据花费时间：10S
        // sort(arr, 0, arr.length - 1);
        // Instant end = Instant.now();
        // System.out.println("快速排序实现2测试:100_000_000条数据花费时间：" + Duration.between(start, end).get(ChronoUnit.SECONDS) + "S");
    }

    /**
     * 快速排序实现一
     *
     * @param arr   目标数组
     * @param start 开始位置
     * @param end   结束位置
     */
    private static void quickSort(int[] arr, int start, int end) {
        //第一趟排序 -9, 78, 0, 23, -576, 70
        //左边起始位置
        int left = start;
        //右边起始位置
        int right = end;
        //基准值
        int pivot = arr[(start + end) / 2];
        while (left < right) {
            //在基准值的左边寻找比基准值大的元素
            while (arr[left] < pivot) {
                //当前元素比基准值小往右移动下标
                left++;
            }
            //在基准值的右边寻找比基准值小的元素
            while (arr[right] > pivot) {
                //当前元素比基准值大往左移动下标
                right--;
            }
            //如果左右下标重合表示第一轮结束
            if (left >= right) {
                break;
            }
            //左边比基准值大的元素和右边比基准值小的元素交换
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            //交换后如果左边遇到跟基准值相等的元素，right左移
            // 交换前 -9, 70, 0, 23, -576, 0
            //           |           |
            // 交换后 -9, 0, 0, 23, -576, 70；打破两个相同的数字在左右两边重复交换
            if (arr[left] == pivot) {
                right--;
            }
            // 交换前 -9, 70, 0, 23, -576, 0
            // 退出情况：       ||
            // 交换后 -9, -576, 0, 23, 0, 70
            //交换后如果右边遇到跟基准值相等的元素，left右移；打破两个相同的数字在左右两边重复交换
            if (arr[right] == pivot) {
                left++;
            }
        }
        // 交换前 -9, 70, 0, 23, -576, 0
        // 退出情况：       ||
        // 交换后 -9, -576, 0, 23, 0, 70；不推进指针会导致一直在[0,1]区间递归
        if (left == right) {
            left++;
            right--;
        }
        //开始的下标小于结束的下标才递归
        if (start < right) {
            //基准值左边递归
            quickSort(arr, start, right);
        }
        //开始的下标小于结束的下标才递归
        if (end > left) {
            //基准值右边递归
            quickSort(arr, left, end);
        }
    }

    /**
     * 快速排序实现二
     *
     * @param arr   被排序的数组
     * @param start 开始位置
     * @param end   结束位置
     */
    private static void sort(int[] arr, int start, int end) {
        if (start < end) {
            //低位
            int low = start;
            //高位
            int high = end;
            //标准元素
            int stander = arr[start];

            while (low < high) {
                //高位的元素不小于标准元素
                while (low < high && stander <= arr[high]) {
                    high--;
                }
                //当高位的元素比标准的元素小的时候高位和低位的元素交换
                arr[low] = arr[high];
                //低位元素不大于标准元素
                while (low < high && stander >= arr[low]) {
                    low++;
                }
                //当低位元素大于标准元素时低位元素和高位元素交换
                arr[high] = arr[low];
            }
            arr[low] = stander;
            //排序小的数据
            sort(arr, start, low);
            //排序大的数据
            sort(arr, low + 1, end);
        }
    }
}
