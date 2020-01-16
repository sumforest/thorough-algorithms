package com.sen.data.structure.tree;

import com.sen.data.structure.sort.BubbleSort;

import java.util.Arrays;

/**
 * @Auther: Sen
 * @Date: 2020/1/17 00:10
 * @Description: 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        // int[] arr = {4, 6, 8, 5, 9,-234,345,34,765,2341,4,7};
        // heapSort(arr);
        // System.out.println(Arrays.toString(arr));
        // 堆排序10000000条数据花费时间：2S
        BubbleSort.testPerformance("堆排序", HeapSort::heapSort,10_000_000,false);
    }

    /**
     * 堆排序方法
     *
     * @param arr 需要排序的数组
     */
    public static void heapSort(int[] arr) {
       /* //结果：4，9，8，5，6
        adjustHeap(arr, 1, arr.length);
        System.out.println(Arrays.toString(arr));

        //结果：9，6，8，5，4
        adjustHeap(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));*/

        //先构建大顶堆
        //arr.length / 2 - 1表示最后一个非叶子节点的下标
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //调整大顶堆
            adjustHeap(arr, i, arr.length);
        }

        //重复将缩短长度的二叉树再调整成大顶堆
        for (int i = arr.length - 1; i > 0; i--) {
            //将调整成大顶堆的堆顶父节点与最后一个元素交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //在原来的基础上-1调整大顶堆
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整成大顶堆
     *
     * @param arr    需要调整的数组
     * @param index  调整成大顶堆的堆顶根节点
     * @param length 数组长度
     */
    public static void adjustHeap(int[] arr, int index, int length) {
        //临时变量保存调整前根节点的值
        int temp = arr[index];
        for (int i = 2 * index + 1; i < length; i = 2 * i + 1) {
            //当前节点的左子节点要小于右子节点
            if (i + 1 < length && arr[i] < arr[i + 1]) {
                //把当前的指针指向右子节点
                i++;
            }
            //当前节点的值比它的子节点的值要小，不符合大顶堆要求，调整
            if (temp < arr[i]) {
                arr[index] = arr[i];
                //把index指向i
                index = i;
            } else {
                //调整的时候是从最后一个大顶堆开始按照从左到右、从下到上调整的，当前调整的就是最后一个节点
                break;
            }
        }
        //把temp赋给给最后和父节点交换的元素
        arr[index] = temp;
    }
}
