package com.sen.data.structure.sort;

/**
 * @Auther: Sen
 * @Date: 2020/1/13 23:54
 * @Description: 选择排序，时间复杂度O(n^2)
 */
public class SelectSort {

    public static void main(String[] args) {
        // int[] arr = {101, 34, 119, 1};
        // System.out.println("排序前");
        // selectSort(arr);
        // System.out.println("排序后");
        // System.out.println(Arrays.toString(arr));
        //选择排序150000条数据花费时间：6S
        BubbleSort.testPerformance("选择排序", SelectSort::selectSort);
    }

    private static void selectSort(int[] arr) {
        //比较length - 1轮
        for (int i = 0; i < arr.length - 1; i++) {
            //假定每轮的第一个数是最小
            int index = i;
            int minValue = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (minValue > arr[j]) {
                    index = j;
                    minValue = arr[j];
                }
            }
            //每轮找到最小的数后交换，即index发生变化时交换
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = minValue;
            }
        }
        /*
        //算法推导:
        //第一轮
        //假定第一个数组最小
        int index = 0;
        int minValue = arr[0];
        //从第二个数开始比较
        for (int i = 1; i < arr.length; i++) {
            //如果数组中还有比更小的跟新最小的下标和值
            if (arr[0] > arr[i]) {
                index = i;
                minValue = arr[i];
            }
        }
        //最后把最小的和arr[0]交换，只有当index改变时才交换
        if (index != 0) {
            arr[index] = arr[0];
            arr[0] = minValue;
            System.out.println(Arrays.toString(arr));
        }
        //第二轮
        //假定第一个数组最小
        index = 1;
        minValue = arr[1];
        //从第二个数开始比较
        for (int i = 1 + 1; i < arr.length; i++) {
            //如果数组中还有比更小的跟新最小的下标和值
            if (arr[1] > arr[i]) {
                index = i;
                minValue = arr[i];
            }
        }
        //最后把最小的和arr[0]交换
        if (index != 1) {
            arr[index] = arr[1];
            arr[1] = minValue;
        }
        System.out.println(Arrays.toString(arr));

        //第三轮
        //假定第一个数组最小
        index = 2;
        minValue = arr[2];
        //从第二个数开始比较
        for (int i = 1 + 2; i < arr.length; i++) {
            //如果数组中还有比更小的跟新最小的下标和值
            if (arr[2] > arr[i]) {
                index = i;
                minValue = arr[i];
            }
        }
        //最后把最小的和arr[0]交换
        if (index != 2) {
            arr[index] = arr[2];
            arr[2] = minValue;
        }
        System.out.println(Arrays.toString(arr));
        */
    }
}
