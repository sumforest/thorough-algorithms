package com.sen.algorithm.binary.search.norecursive;

/**
 * @Auther: Sen
 * @Date: 2020/1/26 21:53
 * @Description: 二分查找算法非递归实现,前提：数组有序
 */
public class BinarySearchNoRecursive {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearchNoRecursive(arr, 8);
        System.out.println(index);
    }

    /**
     * 二分查找非递归实现
     *
     * @return 找到返回下标，不存在返回-1
     */
    private static int binarySearchNoRecursive(int[] arr, int target) {
        //查找的起点
        int left = 0;
        //查找的终点
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                //找到该值
                return mid;
            }
            if (target > arr[mid]) {
                //目标值比中间值大，向右查找
                left = mid + 1;
            }
            if (target < arr[mid]) {
                //目标值比中间值小，向左查找
                right = mid -1;
            }
        }
        return -1;
    }
}
