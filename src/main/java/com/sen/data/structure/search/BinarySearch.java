package com.sen.data.structure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sen
 * @Date: 2020/1/15 02:02
 * @Description: 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = { 3, 29, 75, 99, 200, 239, 239, 239};
        // System.out.println(binarySearchOne(arr, 0, arr.length - 1, 21));
        System.out.println(binarySearchMany(arr, 0, arr.length - 1, 239));
    }

    /**
     * 二分查找(只查找一个)
     *
     * @param arr    目标数组
     * @param left   开始查找的起始位置
     * @param right  开始查找的结束位置
     * @param target 目标值
     * @return 不存在返回-1否则返回下标
     */
    private static int binarySearchOne(int[] arr, int left, int right, int target) {
        /*
        如果开始的位置超出了结束的位置结束查找，要查找的目标元素不存在
        |   |       |
        3, 29, 75, 99, 200, 239, 239, 239
        0   1   2   3   4    5    6    7
        */
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (target > midValue) {
            //向右查找
            return binarySearchOne(arr, mid + 1, right, target);
        }
        if (target < midValue) {
            //向左查找
            return binarySearchOne(arr, left, mid - 1, target);
        }
        return mid;
    }

    /**
     * 二分查找（查找多个）
     *
     * @param arr    原数组
     * @param left   开始查找的位置
     * @param right  结束查找的位置
     * @param target 目标值
     * @return 存在返回下标集合，不存在空的集合
     */
    private static List<Integer> binarySearchMany(int[] arr, int left, int right, int target) {
        //如果开始的位置超出了结束的位置结束查找，要查找的目标元素不存在
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (target > midValue) {
            //向右查找
            return binarySearchMany(arr, mid + 1, right, target);
        }
        if (target < midValue) {
            //向左查找
            return binarySearchMany(arr, left, mid - 1, target);
        }
        //找到了
        ArrayList<Integer> indexList = new ArrayList<>();
        int index = mid - 1;
        //以mid为起点向左查看是否还存target值
        while (index >= 0 && arr[index] == target) {
            indexList.add(index);
            //指针向左移动
            index--;
        }
        //把查到的下标加入集合
        indexList.add(mid);
        //以mid为起点向右查看是否还存在target值
        // 重置指针
        index = mid + 1;
        while (index <= arr.length - 1 && arr[index] == target) {
            indexList.add(index);
            //右移指针
            index++;
        }
        return indexList;
    }
}
