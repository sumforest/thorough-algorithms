package com.sen.data.structure.search;

/**
 * @Auther: Sen
 * @Date: 2020/1/15 17:04
 * @Description: 插值查找(前提数组必须有序)
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 9000));
    }

    /**
     * 插值查找
     *
     * @param arr    目标数组
     * @param left   开始查找起始位置
     * @param right  查找结束位置
     * @param target 目标值
     * @return 找到返回下标，else return -1；
     */
    private static int insertValueSearch(int[] arr, int left, int right, int target) {
        //target < arr[0] || target > arr[arr.length - 1]保证了取midValue不越界，同时又起到优化的作用
        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }
        //与二分查找的主要区别
        int mid = left + (target - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (target > midValue) {
            //向右查找
          return    insertValueSearch(arr, mid + 1, right, target);
        }
        if (target < midValue) {
            //向左查找
            return insertValueSearch(arr, left, mid - 1, target);
        }
        //找到了
        return mid;
    }
}
