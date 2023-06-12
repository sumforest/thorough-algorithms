package com.sen.leetcode.findKthLargest;

import java.util.Random;

/**
 * @author LingSen
 * @date 2023/6/10 17:15
 * 215. 数组中的第K个最大元素(快速选择，快排的变种)
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindKthLargestDemo {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
//        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
//        int k = 4;

        System.out.println(findKthLargest(nums, k));
//        for (int i = 0; i < 1000000; i++) {
//            int res = findKthLargest(nums, k);
//            if (res != 5) {
//                System.out.println(res);
//            }
//        }
//        int[] nums = new int[]{1, 2, 3, 4, 6, 5};
//        System.out.println(nums[partition(nums, 4, 5)]);
    }

    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public static int quickSelect(int[] arr, int left, int right, int target) {
        int q = partition(arr, left, right);
        if (q == target) {
            return arr[target];
        }
        if (q < target) {
            // 向右递归
            return quickSelect(arr, q + 1, right, target);
        }
        // 向左递归
        return quickSelect(arr, left, q - 1, target);
    }

    /**
     * 对数组更具基点pivot进行分割，达到[0,pivotIndex-1] <=pivot  [pivotIndex-1,arr.length-1]>=pivot
     *
     * @param arr   数组
     * @param left  左下标
     * @param right 右下标
     * @return
     */
    public static int partition(int arr[], int left, int right) {
        // 随机选择一个基点
        int pivotIndex = random.nextInt(right - left + 1) + left;
        // 把基点移动到最后保存
        swap(arr, pivotIndex, right);
        int pivot = arr[right];

        // 根据基点对 [left,right] 范围内的数组进行划分
        // 慢指针，记录当前的分界点
        int slow = left - 1;
        //            | |
        // 交换前1,2,3,4,5,6
        for (int i = left; i < right; i++) {
            if (arr[i] <= pivot) {
                // 交换
                swap(arr, ++slow, i);
            }
        }
        // 最后把基点交互回分界点的下一个位置
        swap(arr, ++slow, right);
        return slow;
    }


    public static void swap(int[] arr, int pre, int post) {
        int temp = arr[pre];
        arr[pre] = arr[post];
        arr[post] = temp;
    }
}
