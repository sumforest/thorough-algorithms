package com.sen.data.structure.sort;

/**
 * @Author: Sen
 * @Date: 2020/1/14 01:02
 * @Description: 插入排序，时间复杂度n^2
 */
public class InsertSort {

    public static void main(String[] args) {
        // int[] arr = {101, 34, 119, 1};
        // System.out.println("排序前");
        // System.out.println(Arrays.toString(arr));
        // inertSort(arr);
        // System.out.println("排序后");
        // System.out.println(Arrays.toString(arr));
        // 插入排序150000条数据花费时间：1S
        BubbleSort.testPerformance("插入排序", InsertSort::inertSort);
    }

    private static void inertSort(int[] arr) {
        //一开始把第一个元素看成是有序的101, 34, 119, 1 -> 34, 101, 119, 1
        for (int i = 1; i < arr.length; i++) {
            // 要插入的值
            int insert = arr[i];
            // 已经有序部分的最后一个元素的下标
            int index = i - 1;
            // 寻找插入的位置
            while (index >= 0 && insert < arr[index]) {
                //把index的元素后移
                arr[index + 1] = arr[index];
                //把指针前移
                index--;
            }
            //要插入的位置不是自身位置时才插入
            if (index + 1 != i) {
                //把index往后移动1到目标位置
                arr[index+1] = insert;
            }
        }
        
        /*
        //把第一个元素看作一个有序的数组
        //待插入的值
        int insert = arr[1];
        //待插入的下标
        int index = 1 - 1;

        //index >= 0保证数组下标不越界
        //insert < arr[index]条件满足表示待插入还没找到合适的位置
        while (index >= 0 && insert < arr[index]) {
            //把当前待插入下标的元素后移
            arr[index + 1] = arr[index];
            //把待插入的下标往前移动
            index--;
        }
        //把插入的下标往后移动一并插入元素
        arr[index + 1] = insert;
        System.out.println("第一轮排序后");
        System.out.println(Arrays.toString(arr));

         insert = arr[2];
        //待插入的下标
         index = 2 - 1;

        //index >= 0保证数组下标不越界
        //insert < arr[index]条件满足表示待插入还没找到合适的位置
        while (index >= 0 && insert < arr[index]) {
            //把当前待插入下标的元素后移
            arr[index + 1] = arr[index];
            //把待插入的下标往前移动
            index--;
        }
        //把插入的下标往后移动一并插入元素
        arr[index + 1] = insert;
        System.out.println("第二轮排序后");
        System.out.println(Arrays.toString(arr));

        insert = arr[3];
        //待插入的下标
        index = 3 - 1;

        //index >= 0保证数组下标不越界
        //insert < arr[index]条件满足表示待插入还没找到合适的位置
        while (index >= 0 && insert < arr[index]) {
            //把当前待插入下标的元素后移
            arr[index + 1] = arr[index];
            //把待插入的下标往前移动
            index--;
        }
        //把插入的下标往后移动一并插入元素
        arr[index + 1] = insert;
        System.out.println("第三轮排序后");
        System.out.println(Arrays.toString(arr));*/
    }
}
